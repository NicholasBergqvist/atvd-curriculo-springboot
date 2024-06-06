package atividade.aos.curriculo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Setter
    private String nome;
    @Setter
    private String email;
    @Setter
    private String telefone;
    @Setter
    private String sobre;

    @Setter
    @JsonManagedReference
    @OneToMany(cascade=CascadeType.ALL,orphanRemoval = true, mappedBy = "pessoa")
    private List<Graduacao> graduacoes = new ArrayList<Graduacao>();

    @Setter
    @JsonManagedReference
    @OneToMany(cascade=CascadeType.ALL,orphanRemoval = true, mappedBy = "pessoa")
    private List<Especializacao> especializacoes = new ArrayList<Especializacao>();

    @Setter
    @JsonManagedReference
    @OneToMany(cascade=CascadeType.ALL,orphanRemoval = true, mappedBy = "pessoa")
    private List<ExpProfissional> trabalhos = new ArrayList<ExpProfissional>();

    public Pessoa(String nome,String email,String telefone,String sobre){
    }
}
