package atividade.aos.curriculo.service;

import atividade.aos.curriculo.model.Graduacao;
import atividade.aos.curriculo.repository.GraduacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GraducaoService {
    @Autowired
    private GraduacaoRepository graduacaoRepository;

    public List<Graduacao> findAll(){
        return this.graduacaoRepository.findAll();
    }

    public Optional<Graduacao> findById(Long id){
        return this.graduacaoRepository.findById(id);
    }

    public Graduacao save (Graduacao graduacao){
        return this.graduacaoRepository.save(graduacao);
    }

    public void deleteById(Long id){
        this.graduacaoRepository.deleteById(id);
    }
    public boolean existsById(Long id){
        return this.graduacaoRepository.existsById(id);
    }
}
