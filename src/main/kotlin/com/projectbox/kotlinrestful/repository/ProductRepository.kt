package com.projectbox.kotlinrestful.repository

import com.projectbox.kotlinrestful.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, String>{
    
}