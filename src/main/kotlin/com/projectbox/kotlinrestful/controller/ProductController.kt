package com.projectbox.kotlinrestful.controller

import com.projectbox.kotlinrestful.model.*
import com.projectbox.kotlinrestful.service.ProductService
import org.springframework.data.jpa.repository.Query
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(private val productService: ProductService) {

    @PostMapping(
            value = ["/api/products"],
            produces = ["application/json"],
            consumes = ["application/json"]
    )
    fun createProduct(@RequestBody body: CreateProductRequest): BaseResponse<ProductResponse> {
        val response = productService.create(body)
        return BaseResponse(
                code=200,
                status="OK",
                data=response
        )
    }

    @GetMapping(
            value = ["/api/products/{idProduct}"],
            produces = ["application/json"]
    )
    fun getProduct(@PathVariable("idProduct") id: String): BaseResponse<ProductResponse> {
        val response = productService.get(id)
        return BaseResponse(
                code=200,
                status = "OK",
                data = response
        )
    }

    @PutMapping(
            value = ["/api/products/{idProduct}"],
            produces = ["application/json"],
            consumes = ["application/json"]
    )
    fun updateProduct(@PathVariable("idProduct") id: String, @RequestBody body: UpdateProductRequest): BaseResponse<ProductResponse> {
        val response = productService.update(id, body)
        return BaseResponse(
                code=200,
                status="OK",
                data=response
        )
    }

    @DeleteMapping(
            value = ["/api/products/{idProduct}"],
            produces = ["application/json"]
    )
    fun deleteProduct(@PathVariable("idProduct") id: String): BaseResponse<String> {
        productService.delete(id)
        return BaseResponse(
                code=200,
                status = "OK",
                data = id
        )
    }

    @GetMapping(
            value = ["/api/products"],
            produces = ["application/json"]
    )
    fun listProduct(@RequestParam("page", defaultValue = "0") page: Int, @RequestParam("size", defaultValue = "10") size: Int): BaseResponse<List<ProductResponse>> {
        val request = ListProductRequest(page, size)
        val responses = productService.list(request)
        return BaseResponse(
                code=200,
                status = "OK",
                data = responses
        )
    }
}