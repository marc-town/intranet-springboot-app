package com.graham.services;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graham.common.GrahamHttpStatus;
import com.graham.domain.model.JwtStaffEntity;
import com.graham.domain.repositorys.JwtUserRepository;
import com.graham.exception.GrahamError;
import com.graham.exception.GrahamException;
import com.graham.security.UserPrincipal;

/**
 * Spring SecurityがDBを参照してユーザー情報を取得するサービス。
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private JwtUserRepository jwtUserRepository;
	
	@Autowired
    private MessageSource messageSource;
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtUserDetailsServiceImpl.class);
	
	/**
	 * ログインIDから社員情報を取得する
	 * UserDetailsServiceの実装クラスとなるためメソッド名はUsernameで検索しているように見える
	 * 
	 * @param loginId ログインID
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		LOGGER.info("BEGIN JwtUserDetailsServiceImpl loadUserByUsername");
		
		// ログインIDから社員情報を取得
		JwtStaffEntity staff = jwtUserRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		
		// 社員情報を取得できなかった場合
		if (staff == null) {
			LOGGER.error(messageSource.getMessage(
					"error.GSOL0002", new String[]{String.valueOf(username)}, Locale.JAPANESE));
			GrahamError err = new GrahamError(
					GrahamHttpStatus.NOT_FOUND, "GSOL0002", messageSource.getMessage(
							"error.GSOL0002", new String[]{String.valueOf(username)}, Locale.JAPANESE));
			throw new GrahamException(err);
		}
		
		// 取得した社員情報をSpringSecurityが認証できる形式で返却する
		return UserPrincipal.build(staff);
	}
}
