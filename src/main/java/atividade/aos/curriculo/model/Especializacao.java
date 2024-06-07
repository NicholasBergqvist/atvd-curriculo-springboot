package atividade.aos.curriculo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Entity
@NoArgsConstructor
@ToString
@Table
public class Especializacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEspecializao;
    @Setter
    private String nomeEspecializacao;
    @Setter
    private String nomePlataforma;
    @Setter
    private String descricao;
    @Setter
    private String cargaHoraria;

    @Setter
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    public Especializacao(String nomeEspecializacao, String nomePlataforma, String descricao, String cargaHoraria,Pessoa pessoa) {
        this.nomeEspecializacao = nomeEspecializacao;
        this.nomePlataforma = nomePlataforma;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
        this.pessoa = pessoa;
    }
}
