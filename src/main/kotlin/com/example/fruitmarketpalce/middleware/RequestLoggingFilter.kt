package com.example.fruitmarketpalce.middleware

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

/**
 * 要在中间件中自动执行代码，您需要创建一个 Filter 类并使用 @Component 对其进行注释，以便 Spring Boot 可以在中间件中注册您的类。
 */
@Component
class RequestLoggingFilter : Filter{
    private val loggerFactory = LoggerFactory.getLogger("Fruit Logger")

    /**
     * doFilter接受请求、响应和过滤器链。
     * 您从请求对象中获取 UTM 参数。
     * 在这个用例中，您不会更改响应，但如果您愿意，您可以更改。
     */
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        val utmSource = servletRequest.getParameter("utm_source")
        loggerFactory.info("Logging UTM source: $utmSource")
        filterChain.doFilter(servletRequest, servletResponse)
    }
}