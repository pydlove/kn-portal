package com.aiocloud.kn.portal.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.UrlPathHelper;

@Component
public class PrefixRequestInterceptor implements HandlerInterceptor {

    private final UrlPathHelper urlPathHelper = new UrlPathHelper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = urlPathHelper.getPathWithinApplication(request);

        // 支持多种前缀，如 /rk/, /abc/, /xyz/ 等
        if (requestURI.startsWith("/")) {
            String[] parts = requestURI.split("/");
            if (parts.length >= 2 && !parts[1].isEmpty()) {
                String prefix = parts[1];

                // 检查是否为需要处理的前缀（可以根据需要扩展）
                if (isSupportedPrefix(prefix)) {
                    // 设置标识到 ThreadLocal
                    TableNameContext.setPrefix(prefix);

                    // 修改请求URI，去除前缀
                    String newURI = requestURI.substring(prefix.length() + 1); // 去除 "/prefix" 前缀

                    if (newURI.isEmpty()) {
                        newURI = "/";
                    }

                    try {
                        request.getRequestDispatcher(newURI).forward(request, response);
                    } finally {
                        // 确保ThreadLocal被清理
                        TableNameContext.clear();
                    }
                    return false; // 阻止继续执行原始请求
                }
            }
        }

        return true;
    }

    /**
     * 判断是否为支持的前缀（可以根据实际需求扩展）
     * @param prefix 前缀
     * @return 是否支持
     */
    private boolean isSupportedPrefix(String prefix) {
        // 可以根据需要添加更多支持的前缀
        return "rk".equals(prefix) || "abc".equals(prefix) || "xyz".equals(prefix);
        // 或者简化为返回 true，支持所有前缀
        // return true;
    }
}
