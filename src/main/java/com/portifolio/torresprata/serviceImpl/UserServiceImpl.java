package com.portifolio.torresprata.serviceImpl;

import com.portifolio.torresprata.constantes.PrataConstants;
import com.portifolio.torresprata.dao.UserDao;
import com.portifolio.torresprata.model.User;
import com.portifolio.torresprata.service.UserService;
import com.portifolio.torresprata.utils.PrataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestmap) {
        log.info("Inside signup {}", requestmap);
        try {
        if (validateSignUpMap(requestmap)){
            User user = userDao.findByEmailId(requestmap.get("email"));
            if (Objects.isNull(user)){
                    userDao.save(getUserFromMap(requestmap));
                    return PrataUtils.getResponseEntity("Registrado com sucesso", HttpStatus.OK);
            } else {
                return PrataUtils.getResponseEntity("Email já cadastrado.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return PrataUtils.getResponseEntity(PrataConstants.invalid_data, HttpStatus.BAD_REQUEST);
        }
        } catch (Exception ex){
            ex.printStackTrace();
            }
        return PrataUtils.getResponseEntity(PrataConstants.mensagem_generica, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUpMap(Map<String, String> requestMap){
       if(requestMap.containsKey("nome") && requestMap.containsKey("telefone")
                && requestMap.containsKey("email") && requestMap.containsKey("senha")){
           return true;
       }
       return false;
    }

    private User getUserFromMap(Map<String, String> requestMap){
        User user = new User();
        user.setNome(requestMap.get("nome"));
        user.setTelefone(requestMap.get("telefone"));
        user.setEmail(requestMap.get("email"));
        user.setSenha(requestMap.get("senha"));
        user.setStatus("false");
        user.setRole("user");

        return user;
    }
}
