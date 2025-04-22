package com.empresa.portfolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "projeto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "gerente")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @Size(max = 200)
    @Column(name = "nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_previsao_fim")
    private LocalDate dataPrevisaoFim;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Size(max = 5000)
    @Column(length = 5000)
    private String descricao;

    @Size(max = 45)
    @Column(length = 45)
    private String status;

    @Column
    private Double orcamento;

    @Size(max = 45)
    @Column(length = 45)
    private String risco;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idgerente", nullable = false,
            foreignKey = @ForeignKey(name = "fk_gerente"))
    private Pessoa gerente;
}