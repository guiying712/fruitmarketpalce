package com.example.fruitmarketpalce.mapper

import com.example.fruitmarketpalce.model.Fruit
import org.apache.ibatis.annotations.*
import org.springframework.stereotype.Component

/**
 * 使用 Mybatis XML实现基本 CRUD
 */
@Mapper
@Component
interface MarketplaceXmlMapper {

    fun getAllFruit(): List<Fruit>

    fun getFruitById(id: Int): Fruit?

    fun insert(fruit: Fruit): Int

    fun update(fruit: Fruit): Int

    fun delete(id: Int): Int
}