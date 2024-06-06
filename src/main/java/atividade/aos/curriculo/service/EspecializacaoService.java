package atividade.aos.curriculo.service;

import atividade.aos.curriculo.model.Especializacao;
import atividade.aos.curriculo.repository.EspecializacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecializacaoService {
    @Autowired
    private EspecializacaoRepository especializacaoRepository;

    public List<Especializacao> findAll(){
        return this.especializacaoRepository.findAll();
    }

    public Optional<Especializacao> findById(Long id){
        return this.especializacaoRepository.findById(id);
    }

    public Especializacao save (Especializacao especializacao){
        return this.especializacaoRepository.save(especializacao);
    }

    public void deleteById(Long id){
        this.especializacaoRepository.deleteById(id);
    }
    public boolean existsById(Long id){
        return this.especializacaoRepository.existsById(id);
    }
}
