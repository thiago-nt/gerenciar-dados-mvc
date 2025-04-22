package com.empresa.portfolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nome;

    @Past(message = "A data de nascimento deve estar no passado")
    @Column(name = "datanascimento", nullable = false)
    private LocalDate dataNascimento;

    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos")
    @Column(length = 14, unique = true)
    private String cpf;

    @Column(nullable = false)
    private boolean funcionario;

    @Column(nullable = false)
    private boolean gerente;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa)) return false;
        Pessoa pessoa = (Pessoa) o;
        return id != null && id.equals(pessoa.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}