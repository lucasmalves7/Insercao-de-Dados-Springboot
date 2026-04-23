package com.javarocketseat.java_rocketseat.filter;

//servelet é a base para qualquer framework web, incluindo o springboot
import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component //essa anotation se faz necessária quando que eu quero que o Spring gerencie uma classe, assim como RestController.
//O Componente é a classe mais genérica de gerenciamento possível.
public class FilterTaskAuth implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //aqui no doFilter pediremos para ele executar uma ação
    }

}
