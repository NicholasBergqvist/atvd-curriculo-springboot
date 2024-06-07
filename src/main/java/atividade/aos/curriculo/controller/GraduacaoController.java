package atividade.aos.curriculo.controller;

import atividade.aos.curriculo.model.Graduacao;
import atividade.aos.curriculo.model.Pessoa;
import atividade.aos.curriculo.service.GraducaoService;
import atividade.aos.curriculo.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/curriculo/graduacao")
public class GraduacaoController {
    @Autowired
    private GraducaoService graducaoService;
    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Graduacao> listarGraduacoes(){
        return this.graducaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Graduacao> listarGradById(@PathVariable Long id){
        Optional<Graduacao> g = graducaoService.findById(id);
        if(g.isPresent()){
            return ResponseEntity.ok(g.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Graduacao criarGraduacao(@RequestBody Graduacao g){
        return this.graducaoService.save(g);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Graduacao> alterarGrad(@PathVariable Long id, @RequestBody Graduacao alteracoes){
        Optional<Graduacao> graduacao = this.graducaoService.findById(id);
        if(graduacao.isPresent()){
            Graduacao gradAtualizada = graduacao.get();
            gradAtualizada.setNomeInstituicao(alteracoes.getNomeInstituicao());
            gradAtualizada.setCursoGraduacao(alteracoes.getCursoGraduacao());
            gradAtualizada.setAnoInicio(alteracoes.getAnoInicio());
            gradAtualizada.setAnoConclusao(alteracoes.getAnoConclusao());
            gradAtualizada.setDescricao(alteracoes.getDescricao());
            return ResponseEntity.ok(this.graducaoService.save(gradAtualizada));
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}/{idPessoa}")
    public ResponseEntity<Pessoa> addPessoa(@PathVariable Long id, @PathVariable UUID idPessoa){
        Optional<Graduacao> g = this.graducaoService.findById(id);
        Optional<Pessoa> p = this.pessoaService.findById(idPessoa);
        if(p.isPresent()){
            if(g.isPresent()){
                Pessoa pessoa = p.get();
                Graduacao graduacao = g.get();
                List<Graduacao> gLista = new ArrayList<>();
                gLista.add(graduacao);
                pessoa.setGraduacoes(gLista);
                graduacao.setPessoa(pessoa);
                this.graducaoService.save(graduacao);
                return ResponseEntity.ok(this.pessoaService.save(pessoa));
            }else{
                return ResponseEntity.notFound().build();
            }
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarGradById(@PathVariable Long id){
        Optional<Graduacao> g = graducaoService.findById(id);
        if(g.isPresent()){
            graducaoService.deleteById(id);
            return ResponseEntity.ok("Graduação deletada com sucesso");
        }else {
            return ResponseEntity.status(404).body("Graduação com id fornecido não encontrada");
        }
    }

}
