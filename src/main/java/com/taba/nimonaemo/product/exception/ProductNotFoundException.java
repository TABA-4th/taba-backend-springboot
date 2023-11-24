package com.taba.nimonaemo.product.exception;

import com.taba.nimonaemo.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends LocalizedMessageException {
    public ProductNotFoundException() { super(HttpStatus.NOT_FOUND, "notfound.product"); }
}
