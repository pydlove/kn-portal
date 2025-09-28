// WebMvcConfig.java
package com.aiocloud.ruankao.portal.config.auth;

//import com.aiocloud.kn.portal.config.PrefixRequestInterceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 注册权限拦截器
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/error")
                // 排除
                .excludePathPatterns(
                        "/rk_user/login",
                        "/rk_user/register",
                        "/rk_user/get-code",
                        "/rk_user/get-key",
                        "/rk_menus/root",
                        "/api/calendar-date/task/count",
                        "/wechat/handler"
                );
    }
}
