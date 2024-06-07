package atividade.aos.curriculo.controller;

import atividade.aos.curriculo.model.Especializacao;
import atividade.aos.curriculo.model.ExpProfissional;
import atividade.aos.curriculo.model.Pessoa;
import atividade.aos.curriculo.service.EspecializacaoService;
import atividade.aos.curriculo.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/curriculo/especializacao")
public class EspecializacaoController {
    @Autowired
    private EspecializacaoService especializacaoService;
    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Especializacao> listarEspecializacoes(){
        return this.especializacaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especializacao> listarEspecializacaoById(@PathVariable Long id){
        Optional<Especializacao> especializacao = especializacaoService.findById(id);
        if(especializacao.isPresent()){
            return ResponseEntity.ok(especializacao.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Especializacao> criarEspecializacao(@PathVariable UUID id, @RequestBody Especializacao esp){
        Optional<Pessoa> pessoa = pessoaService.findById(id);
        if(pessoa.isPresent()){
            Pessoa p = pessoa.get();
            List<Especializacao> listaEsp = p.getEspecializacoes();
            listaEsp.add(esp);
            p.setEspecializacoes(listaEsp);
            pessoaService.save(p);
            esp.setPessoa(p);
            return ResponseEntity.ok(this.especializacaoService.save(esp));
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Especializacao> alterarEspecializacao(@PathVariable Long id, @RequestBody Especializacao alteracoes){
        Optional<Especializacao> especializacao = this.especializacaoService.findById(id);
        if(especializacao.isPresent()){
            Especializacao espAtualizada = especializacao.get();
            espAtualizada.setNomeEspecializacao(alteracoes.getNomeEspecializacao());
            espAtualizada.setNomePlataforma(alteracoes.getNomePlataforma());
            espAtualizada.setDescricao(alteracoes.getDescricao());
            espAtualizada.setCargaHoraria(alteracoes.getCargaHoraria());
            return ResponseEntity.ok(this.especializacaoService.save(espAtualizada));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEspecializacaoById(@PathVariable Long id){
        Optional<Especializacao> especializacao = especializacaoService.findById(id);
        if(especializacao.isPresent()){
            especializacaoService.deleteById(id);
            return ResponseEntity.ok("Especialização deletada com sucesso");
        }else {
            return ResponseEntity.status(404).body("Especialização com id fornecido não encontrada");
        }
    }
}
