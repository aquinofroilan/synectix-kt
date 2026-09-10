package com.aquinofroilan.tessera

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class DebugContextTest {
    @Autowired lateinit var ctx: ApplicationContext

    @Test
    fun printBeans() {
        println("BEANS: " + ctx.beanDefinitionNames.joinToString(", "))
    }
}
