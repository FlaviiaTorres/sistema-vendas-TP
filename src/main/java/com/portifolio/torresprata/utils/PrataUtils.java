package com.portifolio.torresprata.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PrataUtils {

    private PrataUtils(){

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}", HttpStatus);
    }

    }
