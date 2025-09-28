package com.aiocloud.ruankao.portal.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeChatConfig {

    @Value("${wechat.token:panyweto}")  // 使用默认值panyweto
    private String token;

    @Value("${wechat.appId:}")  // 您的公众号AppID
    private String appId;

    @Value("${wechat.secret:}")  // 您的公众号AppSecret
    private String secret;

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setToken(token);        // 设置Token
        config.setAppId(appId);       // 设置AppID
        config.setSecret(secret);     // 设置AppSecret
        return config;
    }

    @Bean
    public WxMpService wxMpService() {
        WxMpService service = new WxMpServiceImpl();
        service.setWxMpConfigStorage(wxMpConfigStorage());
        return service;
    }
}