package com.javarocketseat.java_rocketseat.task;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name="tb_task")
public class TaskModel {
    /*
    * ID
    * Usuário (ID_USUARIO)
    * Descrição
    * Título
    * Data de Início da tarefa
    * Data de Término da tarefa
    * Prioridade */

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID ID;
    private String description;

    //tamanho máximo de 50 caracteres
    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    private UUID iduser;

    @CreationTimestamp
    private LocalDateTime createAdt;

    public void setTitle(String title) throws Exception{
        if(title.length() > 50) {
            throw new Exception("O campo título deve conter no máximo 50 caracteres...");
        }
        this.title = title;

    }

}
