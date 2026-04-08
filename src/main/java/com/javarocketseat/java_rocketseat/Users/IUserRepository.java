package com.javarocketseat.java_rocketseat.Users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

//No JpaRepository se define a entidade e o tipo de ID que será passado através dessa entidade
public interface IUserRepository extends JpaRepository<UserModel, UUID> {
//Feito isso, todos os métodos contidos no JpaRepository poderão ser acessados
}
