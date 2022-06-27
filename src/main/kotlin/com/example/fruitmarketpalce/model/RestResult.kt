package com.example.fruitmarketpalce.model

/**
 * 请求的响应结果
 */
data class RestResult<T>(
        val code: String,
        val msg: String,
        val data: T?
) {
    companion object {

        @JvmStatic
        fun <T> success(data: T): RestResult<T> {
            return RestResult("0", "success", data)
        }

        @JvmStatic
        fun <T> error(code: String, msg: String = "error"): RestResult<T> {
            return RestResult(code, msg, null)
        }
    }
}
