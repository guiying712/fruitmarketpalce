package com.example.fruitmarketpalce

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @ComponentScan 从声明此注解的类的包中进行扫描
 */
@SpringBootApplication
@MapperScan("com.example.fruitmarketpalce")
class FruitmarketpalceApplication

fun main(args: Array<String>) {
	runApplication<FruitmarketpalceApplication>(*args)
}
