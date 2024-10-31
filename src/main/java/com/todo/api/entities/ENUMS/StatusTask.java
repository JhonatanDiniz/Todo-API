package com.todo.api.entities.ENUMS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatusTask {
    EM_ANDAMENTO(0, "Em Andamento"), CONCLUIDO(1, "Concluído"), ATRASADO(2, "Atrasado");

    private Integer code;
    private String description;

    public static StatusTask toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for (StatusTask status : StatusTask.values()) {
            if(cod.equals(status.getCode())){
                return status;
            }
        }
        throw new IllegalArgumentException("Status inválido");
    }
}
