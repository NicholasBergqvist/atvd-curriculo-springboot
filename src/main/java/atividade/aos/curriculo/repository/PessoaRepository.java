package atividade.aos.curriculo.repository;

import atividade.aos.curriculo.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {
}
