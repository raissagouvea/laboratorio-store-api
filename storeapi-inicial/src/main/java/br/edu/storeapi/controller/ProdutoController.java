package br.edu.storeapi.controller;

import br.edu.storeapi.dto.ProdutoRequest;
import br.edu.storeapi.model.Produto;
import br.edu.storeapi.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

 private final ProdutoRepository repository;

 public ProdutoController(ProdutoRepository repository) {
  this.repository = repository;
 }

 @GetMapping
 public ResponseEntity<List<Produto>> listar() {
  return ResponseEntity.ok(repository.findAll());
 }

 @GetMapping("/{id}")
 public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
  return repository.findById(id)
          .map(ResponseEntity::ok)
          .orElse(ResponseEntity.notFound().build());
 }

 @PostMapping
 public ResponseEntity<Produto> cadastrar(@Valid @RequestBody ProdutoRequest dto) {
  Produto produto = new Produto();
  produto.setNome(dto.getNome());
  produto.setDescricao(dto.getDescricao());
  produto.setPreco(dto.getPreco());
  produto.setEstoque(dto.getEstoque());
  produto.setCategoria(dto.getCategoria());

  Produto salvo = repository.save(produto);

  URI location = ServletUriComponentsBuilder.fromCurrentRequest()
          .path("/{id}")
          .buildAndExpand(salvo.getId())
          .toUri();

  return ResponseEntity.created(location).body(salvo);
 }

 @PutMapping("/{id}")
 public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoRequest dto) {
  return repository.findById(id)
          .map(produtoExistente -> {
           produtoExistente.setNome(dto.getNome());
           produtoExistente.setDescricao(dto.getDescricao());
           produtoExistente.setPreco(dto.getPreco());
           produtoExistente.setEstoque(dto.getEstoque());
           produtoExistente.setCategoria(dto.getCategoria());

           Produto atualizado = repository.save(produtoExistente);
           return ResponseEntity.ok(atualizado);
          })
          .orElse(ResponseEntity.notFound().build());
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<Void> excluir(@PathVariable Long id) {
  if (!repository.existsById(id)) {
   return ResponseEntity.notFound().build();
  }
  repository.deleteById(id);
  return ResponseEntity.noContent().build();
 }

 @GetMapping("/busca")
 public ResponseEntity<List<Produto>> buscarPorNome(@RequestParam String nome) {
  List<Produto> resultados = repository.findByNomeContainingIgnoreCase(nome);
  return ResponseEntity.ok(resultados);
 }
}