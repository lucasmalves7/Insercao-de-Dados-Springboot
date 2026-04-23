package com.javarocketseat.java_rocketseat.filter;

//servelet é a base para qualquer framework web, incluindo o springboot
import at.favre.lib.crypto.bcrypt.BCrypt;
import com.javarocketseat.java_rocketseat.Users.IUserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component //essa anotation se faz necessária quando que eu quero que o Spring gerencie uma classe, assim como RestController.
//O Componente é a classe mais genérica de gerenciamento possível.
public class FilterTaskAuth extends OncePerRequestFilter {
//foi extendida a classe que realiza o filtro "OncePerRequestFilter"

    @Autowired
    private IUserRepository userRepository; //puxando a interface que valida usuário

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //primeiro é necessário apontar a rota onde será feita a filtragem dos dados:
        var serveletPath = request.getServletPath();
        if(serveletPath.equals("/tasks/")) {
            /* Passos da Filtragem */
            // Pegar a autenticação (usuário e senha)
            var authorization = request.getHeader("Authorization");

            var authEncoded = authorization.substring("Basic".length()).trim();
            // O substring vai calcular quantos caracteres tem essa autenticação de nível básico
            //o trim() vai fazer a remoção dos espaços.

            byte[] authDecode = Base64.getDecoder().decode(authEncoded);

            var authString = new String(authDecode);

            //posição 0 [login], posição 1 [senha]
            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            System.out.println("Authorization");
            System.out.println(authString); //resultado: lucasalves:1234
            System.out.println(username);   //resultado: lucasalves
            System.out.println(password);   //resultado: 1234

            // validar usuário
            var user = this.userRepository.findByUsername(username);
            if(user == null) {
                response.sendError(401);
            } else {
                // validar senha
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {
                    filterChain.doFilter(request,response);
                } else {
                    response.sendError(401);
                }
            }

            // se estiver tudo certo o processo continua */

        } else {
            filterChain.doFilter(request,response);
        }
    }
}
