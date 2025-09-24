// WebMvcConfig.java
package com.aiocloud.kn.portal.config.auth;

//import com.aiocloud.kn.portal.config.PrefixRequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final PermissionInterceptor permissionInterceptor;
//    private final PrefixRequestInterceptor prefixRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册前缀请求拦截器，优先处理
//        registry.addInterceptor(prefixRequestInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/error");

        // 注册权限拦截器
        registry.addInterceptor(permissionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/rk/**", "/abc/**", "/xyz/**"); // 排除已被前缀拦截器处理的路径
    }
}
