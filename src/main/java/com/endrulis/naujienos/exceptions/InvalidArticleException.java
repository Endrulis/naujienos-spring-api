package com.endrulis.naujienos.exceptions;

import org.springframework.validation.BindingResult;

public class InvalidArticleException extends RuntimeException {
    public InvalidArticleException( String message, BindingResult result ) {
        super(message);
    }
}
