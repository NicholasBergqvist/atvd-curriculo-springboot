package atividade.aos.curriculo.service;

import atividade.aos.curriculo.model.ExpProfissional;
import atividade.aos.curriculo.repository.ExpProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpProfissionalService {
    @Autowired
    private ExpProfissionalRepository expProfissionalRepository;

    public List<ExpProfissional> findAll(){
        return this.expProfissionalRepository.findAll();
    }

    public Optional<ExpProfissional> findById(Long id){
        return this.expProfissionalRepository.findById(id);
    }

    public ExpProfissional save (ExpProfissional expProfissional){
        return this.expProfissionalRepository.save(expProfissional);
    }

    public void deleteById(Long id){
        this.expProfissionalRepository.deleteById(id);
    }
    public boolean existsById(Long id){
        return this.expProfissionalRepository.existsById(id);
    }
}
