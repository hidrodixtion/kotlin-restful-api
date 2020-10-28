package com.projectbox.kotlinrestful.config

import com.projectbox.kotlinrestful.entity.ApiKey
import com.projectbox.kotlinrestful.repository.ApiKeyRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class ApiKeySeeder(private val apiKeyRepository: ApiKeyRepository) : ApplicationRunner {

    val apiKey = "SECRET"

    /**
     * THIS WILL BE USED TO FILL api_key TABLE WITH "SECRET"
     */
    override fun run(args: ApplicationArguments?) {
        if (!apiKeyRepository.existsById(apiKey)) {
            val apiKey = ApiKey(id = apiKey)
            apiKeyRepository.save(apiKey)
        }
    }
}