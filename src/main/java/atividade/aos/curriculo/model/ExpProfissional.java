package atividade.aos.curriculo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@Getter
public class ExpProfissional {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idExpProf;
    @Setter
    private String nomeEmpresa;
    @Setter
    private String tempoTrab;
    @Setter
    private String descricao;

    @Setter
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    public ExpProfissional(String nomeEmpresa, String tempoTrab, String descricao, Pessoa pessoa) {
        this.nomeEmpresa = nomeEmpresa;
        this.tempoTrab = tempoTrab;
        this.descricao = descricao;
        this.pessoa = pessoa;
    }
}
