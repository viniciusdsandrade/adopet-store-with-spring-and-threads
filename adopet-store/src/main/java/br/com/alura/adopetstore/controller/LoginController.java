package br.com.alura.adopetstore.controller;

import br.com.alura.adopetstore.dto.LoginDTO;
import br.com.alura.adopetstore.model.Usuario;
import br.com.alura.adopetstore.security.TokenService;
import br.com.alura.adopetstore.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;
    private final UsuarioService service;

    public LoginController(AuthenticationManager manager,
                           TokenService tokenService,
                           UsuarioService service) {
        this.manager = manager;
        this.tokenService = tokenService;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> efetuarLogin(@RequestBody @Valid LoginDTO dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(tokenJWT);
    }
}
