package com.projectbox.kotlinrestful

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinRestfulApplication

fun main(args: Array<String>) {
	runApplication<KotlinRestfulApplication>(*args)
}
