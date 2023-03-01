package com.me.scarlxrd.service;

import com.me.scarlxrd.model.User;
import com.me.scarlxrd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

   @Autowired
    private PasswordEncoder encoder;
   public void createUser(User user){
       String pass = user.getPassword();
       //critografia antes de salva no banco de dados
       user.setPassword(encoder.encode(pass));
       repository.save(user);
   }
}
