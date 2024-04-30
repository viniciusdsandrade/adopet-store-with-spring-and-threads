package br.com.alura.adopetstore.controller;

import br.com.alura.adopetstore.dto.CadastroProdutoDTO;
import br.com.alura.adopetstore.dto.ProdutoDTO;
import br.com.alura.adopetstore.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDTO> cadastrar(@Valid @RequestBody CadastroProdutoDTO dto) {
        var produto = this.service.cadastrar(dto);
        return ResponseEntity.ok(produto);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> listar(Pageable paginacao) {
        var produtos = this.service.listar(paginacao);
        return ResponseEntity.ok(produtos);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
