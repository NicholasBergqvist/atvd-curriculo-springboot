package atividade.aos.curriculo.controller;

import atividade.aos.curriculo.model.ExpProfissional;
import atividade.aos.curriculo.model.Graduacao;
import atividade.aos.curriculo.model.Pessoa;
import atividade.aos.curriculo.service.ExpProfissionalService;
import atividade.aos.curriculo.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/curriculo/experiencia")
public class ExpProfissionalServiceController {
    @Autowired
    private ExpProfissionalService expProfissionalService;
    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<ExpProfissional> listarExpProfissional(){
        return this.expProfissionalService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpProfissional> listExpProfById(@PathVariable Long id){
        Optional<ExpProfissional> exp = expProfissionalService.findById(id);
        if(exp.isPresent()){
            return ResponseEntity.ok(exp.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ExpProfissional criarExpProf(@RequestBody ExpProfissional exp){
        return this.expProfissionalService.save(exp);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ExpProfissional> alterarExpProf(@PathVariable Long id, @RequestBody ExpProfissional alteracoes){
        Optional<ExpProfissional> exp = this.expProfissionalService.findById(id);
        if(exp.isPresent()){
            ExpProfissional expAtualizada = exp.get();
            expAtualizada.setNomeEmpresa(alteracoes.getNomeEmpresa());
            expAtualizada.setTempoTrab(alteracoes.getTempoTrab());
            expAtualizada.setDescricao(alteracoes.getDescricao());
            return ResponseEntity.ok(this.expProfissionalService.save(expAtualizada));
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}/{idPessoa}")
    public ResponseEntity<Pessoa> addPessoa(@PathVariable Long id, @PathVariable UUID idPessoa){
        Optional<Pessoa> p = this.pessoaService.findById(idPessoa);
        Optional<ExpProfissional> exp = this.expProfissionalService.findById(id);
        if(p.isPresent()){
            if(exp.isPresent()){
                Pessoa pessoa = p.get();
                ExpProfissional expProfissional = exp.get();
                List<ExpProfissional> expLista = new ArrayList<>();
                expLista.add(expProfissional);
                pessoa.setTrabalhos(expLista);
                expProfissional.setPessoa(pessoa);
                this.expProfissionalService.save(expProfissional);
                return ResponseEntity.ok(this.pessoaService.save(pessoa));
            }else{
                return ResponseEntity.notFound().build();
            }
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarExpProfById(@PathVariable Long id){
        Optional<ExpProfissional> exp = expProfissionalService.findById(id);
        if(exp.isPresent()){
            expProfissionalService.deleteById(id);
            return ResponseEntity.ok("Experiência profissional deletada com sucesso");
        }else {
            return ResponseEntity.status(404).body("Experiência profissional com id fornecido não encontrada");
        }
    }
}
