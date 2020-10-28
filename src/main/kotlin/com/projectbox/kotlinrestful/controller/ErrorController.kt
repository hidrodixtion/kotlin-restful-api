package com.projectbox.kotlinrestful.controller

import com.projectbox.kotlinrestful.error.NotFoundException
import com.projectbox.kotlinrestful.error.UnauthorizedException
import com.projectbox.kotlinrestful.model.BaseResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ErrorController {
    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException): BaseResponse<String> {
        return BaseResponse(
                code = 400,
                status = "BAD REQUEST",
                data = constraintViolationException.localizedMessage
        )
    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFoundHandler(exception: NotFoundException): BaseResponse<String> {
        return BaseResponse(
                code = 404,
                status = "NOT FOUND",
                data = "Not Found"
        )
    }

    @ExceptionHandler(value = [UnauthorizedException::class])
    fun unauthorizedHandler(exception: UnauthorizedException): BaseResponse<String> {
        return BaseResponse(
                code = 401,
                status = "UNAUTHORIZED",
                data = "Please put your X-Api-Key"
        )
    }
}