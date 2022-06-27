package com.example.fruitmarketpalce

import com.example.fruitmarketpalce.model.Fruit
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.mockito.internal.matchers.GreaterThan
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class FruitmarketpalceApplicationTests(@Autowired val mockMvc: MockMvc, @Autowired val objectMapper: ObjectMapper) {

	@Test
	fun `Assert Fruits has Apple as the first item`() {
		mockMvc.get("/fruits") // 1
			.andExpect { // 2
				status { isOk() } // 3
				content { contentType(MediaType.APPLICATION_JSON) }
				jsonPath("$[0].id") { value(1) } // 4
				jsonPath("$[0].name") { value("Apple") }
				jsonPath("$[0].floor_price") { value(100) }
				jsonPath("$.length()") { GreaterThan(1) }
			}
	}

	@Test
	fun `Assert that we can create an fruit`() {
		mockMvc.get("/fruits/6")
			.andExpect {
				status { isNotFound() }
			}
		val newNFT = Fruit(0, "Orange", 45.3)
		mockMvc.post("/fruits") {
			contentType = MediaType.APPLICATION_JSON
			content = objectMapper.writeValueAsString(newNFT)
		}
			.andExpect {
				status { isCreated() }
				content { contentType(MediaType.APPLICATION_JSON) }
				jsonPath("$.name") { value("Orange") }
				jsonPath("$.floor_price") { value(45.3) }
				jsonPath("$.id") { value(6) }
			}
		mockMvc.get("/fruits/6")
			.andExpect {
				status { isOk() }
				content { contentType(MediaType.APPLICATION_JSON) }
				jsonPath("$.name") { value("Orange") }
				jsonPath("$.floor_price") { value(45.3) }
				jsonPath("$.id") { value(6) }
			}
	}


}
