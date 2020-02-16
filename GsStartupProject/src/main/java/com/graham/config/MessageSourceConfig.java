package com.graham.config;

import java.io.IOException;
import java.time.Duration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import net.rakugakibox.util.YamlResourceBundle;

/**
 * メッセージ外部ファイルをyaml形式で読み込む
 */
@Configuration
public class MessageSourceConfig {

	// SpringBoot設定ファイル（application.yml）のプロパティ「spring.messages.*」を受け取る
    @Bean
    @ConfigurationProperties(prefix = "spring.messages")
    public MessageSourceProperties messageSourceProperties() {
        return new MessageSourceProperties();
    }

    //Spring BootのMessageSourceAutoConfigurationを無効にし、Auto-ConfigでMessageSourceのセットアップを停止する
    @Bean
    public MessageSource messageSource(MessageSourceProperties properties) throws IOException {

        // YAML形式のメッセージ定義ファイルを読み込み
        YamlMessageSource messageSource = new YamlMessageSource();

        // 生成したYamlMessageSourceにMessageSourcePropertiesのプロパティをすべてセット
        // これにより、SpringBoot標準のResourceBundleMessageSourceと完全に同じように扱うことができる
        if (StringUtils.hasText(properties.getBasename())) {
            messageSource.setBasenames(StringUtils
                    .commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(properties.getBasename())));
        }
        if (properties.getEncoding() != null) {
            messageSource.setDefaultEncoding(properties.getEncoding().name());
        }
        messageSource.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());
        Duration cacheDuration = properties.getCacheDuration();
        if (cacheDuration != null) {
            messageSource.setCacheMillis(cacheDuration.toMillis());
        }
        messageSource.setCommonMessages(yamlProperties());
        messageSource.setAlwaysUseMessageFormat(properties.isAlwaysUseMessageFormat());
        messageSource.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());
        return messageSource;
    }
    
    @Bean(name = "messagesProperties")
    public Properties yamlProperties() throws IOException {
        YamlPropertiesFactoryBean bean = new YamlPropertiesFactoryBean();
        bean.setResources(new ClassPathResource("messages.yml"));
        return bean.getObject();
    }

    private static class YamlMessageSource extends ResourceBundleMessageSource {
        @Override
        protected ResourceBundle doGetBundle(String basename, Locale locale) throws MissingResourceException {
            return ResourceBundle.getBundle(basename, YamlResourceBundle.Control.INSTANCE);
        }
    }
}
