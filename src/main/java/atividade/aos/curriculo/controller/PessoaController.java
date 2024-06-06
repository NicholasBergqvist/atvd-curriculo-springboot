package atividade.aos.curriculo.controller;

import atividade.aos.curriculo.model.Pessoa;
import atividade.aos.curriculo.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/curriculo/pessoas")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> listarPessoas(){
        return this.pessoaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> listarPessoaById(@PathVariable UUID id){
        Optional<Pessoa> p = pessoaService.findById(id);
        if(p.isPresent()){
            return ResponseEntity.ok(p.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Pessoa criarPessoa(@RequestBody Pessoa p){
        return this.pessoaService.save(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> modificarPessoa(@PathVariable UUID id, @RequestBody Pessoa pessoaAlterada){
        Optional<Pessoa> p = pessoaService.findById(id);
        if(p.isPresent()){
            Pessoa pessoaAtualizada = p.get();
            pessoaAtualizada.setNome(pessoaAlterada.getNome());
            pessoaAtualizada.setEmail(pessoaAlterada.getEmail());
            pessoaAtualizada.setTelefone(pessoaAlterada.getTelefone());
            pessoaAtualizada.setSobre(pessoaAlterada.getSobre());
            pessoaAtualizada.setGraduacoes(pessoaAlterada.getGraduacoes());
            pessoaAtualizada.setTrabalhos(pessoaAlterada.getTrabalhos());
            pessoaAtualizada.setEspecializacoes(pessoaAlterada.getEspecializacoes());
            return ResponseEntity.ok(this.pessoaService.save(pessoaAtualizada));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPessoaById(@PathVariable UUID id){
        Optional<Pessoa> p = pessoaService.findById(id);
        if(p.isPresent()){
            pessoaService.deleteById(id);
            return ResponseEntity.ok("Pessoa deletada com sucesso");
        }else {
            return ResponseEntity.status(404).body("Pessoa com id fornecido não encontrada");
        }
    }
}
