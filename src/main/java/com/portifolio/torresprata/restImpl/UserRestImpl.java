package com.portifolio.torresprata.restImpl;

import com.portifolio.torresprata.constantes.PrataConstants;
import com.portifolio.torresprata.rest.UserRest;
import com.portifolio.torresprata.service.UserService;
import com.portifolio.torresprata.utils.PrataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {
    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return userService.signUp(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
       return PrataUtils.getResponseEntity(PrataConstants.mensagem_generica, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}