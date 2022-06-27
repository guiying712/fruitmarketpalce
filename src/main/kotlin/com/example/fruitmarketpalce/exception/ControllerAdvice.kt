package com.example.fruitmarketpalce.exception

import com.example.fruitmarketpalce.model.RestResult
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

/**
 * 使用 @ControllerAdvice 注释类时，Spring Boot 会扫描并将此类注册为控制器的 ControllerAdvice
 */
@RestControllerAdvice
class FruitErrorHandler {

    /**
     * 使用 @ExceptionHandler 注释该方法，它接受您创建的异常类，让RestControllerAdvice Spring Boot 知道该方法可以处理该异常。
     * ResponseEntity 的第一个参数是您发送给客户端的结果，可以是简单的文本字符串或 JSON 字符串。第二个参数是状态类型。
     */
    @ExceptionHandler(FruitNotFoundException::class)
    fun handleFruitNotFoundException(servletRequest: HttpServletRequest, exception: Exception): RestResult<String> {
        return RestResult.error("404","Not found")
    }
}