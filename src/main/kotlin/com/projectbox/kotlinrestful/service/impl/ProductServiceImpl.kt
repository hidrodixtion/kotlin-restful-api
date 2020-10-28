package com.projectbox.kotlinrestful.service.impl

import com.projectbox.kotlinrestful.entity.Product
import com.projectbox.kotlinrestful.error.NotFoundException
import com.projectbox.kotlinrestful.model.CreateProductRequest
import com.projectbox.kotlinrestful.model.ListProductRequest
import com.projectbox.kotlinrestful.model.ProductResponse
import com.projectbox.kotlinrestful.model.UpdateProductRequest
import com.projectbox.kotlinrestful.repository.ProductRepository
import com.projectbox.kotlinrestful.service.ProductService
import com.projectbox.kotlinrestful.validation.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class ProductServiceImpl(private val productRepository: ProductRepository, private val validationUtil: ValidationUtil) : ProductService {
    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        validationUtil.validate(createProductRequest)

        val product = Product(
                id = createProductRequest.id,
                name = createProductRequest.name,
                price = createProductRequest.price,
                quantity = createProductRequest.quantity,
                createdAt = Date(),
                updatedAt = null
        )

        productRepository.save(product)

        return mapToProductResponse(product)
    }

    override fun get(id: String): ProductResponse {
        val product = findProductOrThrow(id)
        return mapToProductResponse(product)
    }

    override fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse {
        validationUtil.validate(updateProductRequest)
        val product = findProductOrThrow(id)

        product.apply {
            name = updateProductRequest.name!!
            price = updateProductRequest.price!!
            quantity = updateProductRequest.quantity!!
            updatedAt = Date()
        }

        productRepository.save(product)
        return mapToProductResponse(product)
    }

    override fun delete(id: String) {
        val product = findProductOrThrow(id)
        productRepository.delete(product)
    }

    override fun list(listProductRequest: ListProductRequest): List<ProductResponse> {
        val page = productRepository.findAll(PageRequest.of(listProductRequest.page, listProductRequest.size))
        val products = page.get().collect(Collectors.toList())

        return products.map {
            mapToProductResponse(it)
        }
    }

    private fun findProductOrThrow(id: String): Product =
            productRepository.findByIdOrNull(id) ?: throw NotFoundException()

    private fun mapToProductResponse(product: Product): ProductResponse {
        return ProductResponse(
                id = product.id,
                name = product.name,
                price = product.price,
                quantity = product.quantity,
                createdAt = product.createdAt,
                updatedAt = product.updatedAt
        )
    }
}