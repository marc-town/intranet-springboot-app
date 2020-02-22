package com.graham.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.graham.domain.model.JwtStaffEntity;
import com.graham.services.JwtUserDetailsServiceImpl;

import lombok.Data;

@Data
public class UserPrincipal implements UserDetails {

	/** 自動生成値 */
	private static final long serialVersionUID = 1L;

	/** 社員ID */
	private int staffId;

	/** 
	 * ログインID
	 * JWT実装の都合上usernameとしている 
	 */
    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;
    
    @Autowired
    private MessageSource messageSource;
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtUserDetailsServiceImpl.class);

    public UserPrincipal(int staffId, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.staffId = staffId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal build(JwtStaffEntity staff) {
    	
    	LOGGER.info("BEGIN UserPrincipal build");
    	
        List<GrantedAuthority> authorities = staff.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

        return new UserPrincipal(
        		staff.getStaffId(),
        		staff.getUsername(),
        		staff.getEmail(),
        		staff.getPassword(),
                authorities
        );
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(staffId, that.staffId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(staffId);
    }
}
