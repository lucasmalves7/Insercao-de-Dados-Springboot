package com.javarocketseat.java_rocketseat.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired //Aqui estou pedindo para o spring gerenciar o ciclo de vida da aplicação
    private IUserRepository userRepository; //Aqui estou chamando a interface, para que eu possa acessar os métodos do JpaRepository

    @PostMapping("/")
    //Configurei para pegar a Classe UserModel, onde está localizada as tabelas
    public UserModel create(@RequestBody UserModel userModel) {
        var userCreated = this.userRepository.save(userModel);
        return userCreated;
    }
}
