package atividade.aos.curriculo.service;

import atividade.aos.curriculo.model.Pessoa;
import atividade.aos.curriculo.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> findAll(){
        return pessoaRepository.findAll();
    }
    public Optional<Pessoa> findById(UUID id){
        return this.pessoaRepository.findById(id);
    }
    public Pessoa save(Pessoa pessoa){
        return this.pessoaRepository.save(pessoa);
    }

    public void deleteById(UUID id){
        this.pessoaRepository.deleteById(id);
    }

    public boolean existsById(UUID id){
        return pessoaRepository.existsById(id);
    }
}
