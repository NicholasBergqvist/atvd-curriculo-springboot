package atividade.aos.curriculo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@NoArgsConstructor
@Table(name = "graduacoes")
public class Graduacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idGrad;
    @Setter
    private String nomeInstituicao;
    @Setter
    private String cursoGraduacao;
    @Setter
    private Long anoInicio;
    @Setter
    private Long anoConclusao;
    @Setter
    private String descricao;

    @Setter
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    public Graduacao(String nomeInstituicao, String cursoGraduacao, Long anoInicio, Long anoConclusao, String descricao, Pessoa pessoa) {
        this.nomeInstituicao = nomeInstituicao;
        this.cursoGraduacao = cursoGraduacao;
        this.anoInicio = anoInicio;
        this.anoConclusao = anoConclusao;
        this.descricao = descricao;
        this.pessoa = pessoa;
    }
}
