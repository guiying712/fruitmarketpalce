package com.example.fruitmarketpalce.controller

import com.example.fruitmarketpalce.exception.FruitNotFoundException
import com.example.fruitmarketpalce.mapper.MarketplaceAnnotationMapper
import com.example.fruitmarketpalce.mapper.MarketplaceXmlMapper
import com.example.fruitmarketpalce.model.Fruit
import com.example.fruitmarketpalce.model.RestResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


/**
 * 1、使用 @RestController 注释类时，Spring Boot 将对其进行扫描并将其识别为控制器类。
 *  Spring Boot 中的自动配置意味着无需手动编写 XML 文件来注册控制器。
 *  @RestController 注解告诉 Spring 这段代码描述了一个应该在 web 上可用的端点。
 *  此代码使用 Spring @RestController 注释，它将类标记为控制器，其中每个方法都返回域对象而不是视图。
 *  它是同时包含@Controller 和@ResponseBody 的简写。
 *  2、使用@RequestMapping 注释控制器，Spring Boot 会在所有 API 路径前添加 /nfts。
 *
 *  @Component 是一个元注解，意思是可以注解其他类注解，如@Controller @Service @Repository @Aspect。
 *  官方的原话是：带此注解的类看为组件，当使用基于注解的配置和类路径扫描的时候，这些类就会被实例化。
 *  其他类级别的注解也可以被认定为是一种特殊类型的组件，比如@Repository @Aspect。所以，@Component可以注解其他类注解。
 */
@RestController
@RequestMapping("/fruits")
class MarketplaceController {

//    @Autowired
//    private lateinit var marketplaceMapper: MarketplaceAnnotationMapper

    @Autowired
    private lateinit var marketplaceMapper: MarketplaceXmlMapper

    /**
     * @Value 接受在字符串内插值的 company_name。 Spring Boot 会将结果值绑定到名称。
     */
    @Value("\${company_name}")
    private lateinit var name: String

    /**
     *  将 GET 请求映射到方法，请使用 @GetMapping 将"/homepage"路径作为参数传递给方法。
     */
    @GetMapping("/homepage")
    fun getHomePage(): RestResult<String> = RestResult.success("$name : Fruits Marketplace")


    @GetMapping("/hello")
    fun hello(@RequestParam(value = "name", defaultValue = "World") name: String?): RestResult<String> {
        return RestResult.success(String.format("Hello %s!", name))
    }


    @GetMapping("")
    fun getFruits(): RestResult<List<Fruit>> = RestResult.success(marketplaceMapper.getAllFruit())

    /**
     * 1、使用 @PostMapping 注释该方法以指示此方法处理 POST 请求。您使用与前一个 GET 请求相同的路径""。这不是问题，因为一条路径可以服务两个不同的请求。
     * 2、添加 @ResponseStatus 和 HttpStatus.CREATED 参数，因为通常在创建新 NFT 之类的资源后，您会发出 201 CREATED 响应状态。
     * 如果省略此注释，您将获得默认的 200 OK 响应状态。
     * 3、 @RequestBody 的方法参数是您将发送到此路径的 JSON 对象。
     */
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun postFruit(@RequestBody fruit: Fruit): RestResult<Fruit> {
        val fruit1 = marketplaceMapper.getFruitById(fruit.id)
        if (fruit1 == null) {
            marketplaceMapper.insert(fruit)
        } else {
            marketplaceMapper.update(fruit)
        }
        return RestResult.success(fruit)
    }

    /**
     * 因为您还有一个方法参数 id: Int 使用 @PathVariable 注释进行注释，
     * 所以只要服务器收到对 /1、/2 或任何数字的 GET 请求，您的方法就会收到该参数.
     */
    @GetMapping("/{id}")
    fun getFruitById(@PathVariable id: Int): RestResult<Fruit> {
        val fruit = marketplaceMapper.getFruitById(id)

        //现在，不是返回 null，而是抛出异常。
        // 因此，如果您尝试检索一个不存在的 Fruit，您将得到 404 NOT FOUND 结果。
        return if (fruit != null) RestResult.success(fruit) else throw FruitNotFoundException()
    }

    private var Fruits = mutableListOf(
            Fruit(1, "Apple", 100.0),
            Fruit(2, "Banana", 36.9),
            Fruit(3, "Pear", 0.6),
            Fruit(4, "Peach", 1.1),
            Fruit(5, "Strawberry", 2.5),
    )

}
