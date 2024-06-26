package br.com.alura.adopetstore.controller;

import br.com.alura.adopetstore.dto.RelatorioEstoque;
import br.com.alura.adopetstore.dto.RelatorioFaturamento;
import br.com.alura.adopetstore.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/relatorios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RelatorioController {

    private final RelatorioService service;

    public RelatorioController(RelatorioService service) {
        this.service = service;
    }

    @GetMapping("estoque")
    public ResponseEntity<RelatorioEstoque> obterInfoEstoque(){
        var relatorio = service.infoEstoque();
        return ResponseEntity.ok(relatorio);
    }

    @GetMapping("faturamento")
    public ResponseEntity<RelatorioFaturamento> obterInfoFaturamento(){
        var relatorio = service.faturamentoObtido();
        return ResponseEntity.ok(relatorio);
    }
}