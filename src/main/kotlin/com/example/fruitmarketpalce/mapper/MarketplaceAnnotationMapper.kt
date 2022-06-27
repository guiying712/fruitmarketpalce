package com.example.fruitmarketpalce.mapper

import com.example.fruitmarketpalce.model.Fruit
import org.apache.ibatis.annotations.*
import org.springframework.stereotype.Component

/**
 * 使用 Mybatis 注解实现基本 CRUD
 */
@Mapper
@Component
interface MarketplaceAnnotationMapper {

    @Select("SELECT * FROM fruit")
    fun getAllFruit(): List<Fruit>

    @Select("SELECT * FROM fruit where id = #{id}")
    fun getFruitById(id: Int): Fruit?

    @Insert("INSERT INTO fruit (id,name,floor_price) VALUES (#{id},#{name},#{floor_price})")
    fun insert(fruit: Fruit): Int

    @Update("UPDATE fruit SET id = #{id}, name = #{name}, floor_price = #{floor_price} where id=#{id}")
    fun update(fruit: Fruit): Int

    @Delete("DELETE FROM fruit WHERE id = #{id}")
    fun delete(id: Int): Int
}