package br.com.alura.adopetstore.controller;

import br.com.alura.adopetstore.dto.CadastroPedidoDTO;
import br.com.alura.adopetstore.dto.PedidoDTO;
import br.com.alura.adopetstore.email.EmailPedidoRealizado;
import br.com.alura.adopetstore.model.Usuario;
import br.com.alura.adopetstore.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pedidos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoController {

    private final PedidoService service;
    private final EmailPedidoRealizado email;

    public PedidoController(PedidoService service, EmailPedidoRealizado email) {
        this.service = service;
        this.email = email;
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> cadastrar(@Valid @RequestBody CadastroPedidoDTO dto, @AuthenticationPrincipal Usuario usuario) {
        var pedido = this.service.cadastrar(dto, usuario);
        email.enviar(pedido, usuario);
        return ResponseEntity.ok(pedido);
    }
}
