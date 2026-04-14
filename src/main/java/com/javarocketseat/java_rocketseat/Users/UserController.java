package com.javarocketseat.java_rocketseat.Users;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity create(@RequestBody UserModel userModel) {

        var user = this.userRepository.findByUsername(userModel.getUsername());
        if (user != null) {
            //precisa retornar mensagem de erro
            //e Status Code (define se foi um sucesso ou se foi um erro)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

        //Pega a sennha de userModel.getPassword() e criptografa
        var passwordHashred = BCrypt.withDefaults()
                .hashToString(12, userModel.getPassword().toCharArray()); //Vai transformar o getPassword num array de caracteres

        //pega a senha digitada pelo usuário e manda para a variável que declarei acima
        userModel.setPassword(passwordHashred);

        var userCreated = this.userRepository.save(userModel);
        //retorna que o usuário foi criado
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
