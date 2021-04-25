package com.jadehelena.banking.config.validation

import com.jadehelena.banking.config.validation.dto.FormErrorDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ValidationErrorHandler {

    @Autowired
    private MessageSource messageSource

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FormErrorDto> handle(MethodArgumentNotValidException exception) {
        List<FormErrorDto> formErrorDto = new ArrayList<>()
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors()

        fieldErrors.each {e ->
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale())
            FormErrorDto error = new FormErrorDto(e.getField(), message)
            formErrorDto.add(error)
        }

        return formErrorDto
    }

}
