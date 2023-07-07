package com.pedro.depoimento.depoimento;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Depoimento {
    
    @Id
    private String id;

    private String nome;

    private String depoimento;

    private String foto;

    private boolean deleted;

}
