package com.empresa.portfolio.dto;

import java.time.LocalDate;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaDTO {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private boolean funcionario;
    private boolean gerente;

}