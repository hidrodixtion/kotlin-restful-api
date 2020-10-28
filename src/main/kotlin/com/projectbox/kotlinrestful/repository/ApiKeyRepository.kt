package com.projectbox.kotlinrestful.repository

import com.projectbox.kotlinrestful.entity.ApiKey
import org.springframework.data.jpa.repository.JpaRepository

interface ApiKeyRepository : JpaRepository<ApiKey, String> {}