package com.empresa.portfolio.model;
import com.empresa.portfolio.common.enums.RiscoProjeto;
import com.empresa.portfolio.common.enums.StatusProjeto;
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

    @Enumerated(EnumType.STRING)
    private StatusProjeto status;

    @PositiveOrZero
    @Digits(integer = 12, fraction = 2)
    private Double orcamento;

    @Enumerated(EnumType.STRING)
    private RiscoProjeto risco;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "idgerente",
            foreignKey = @ForeignKey(name = "fk_projeto_gerente"))
    private Pessoa gerente;

}