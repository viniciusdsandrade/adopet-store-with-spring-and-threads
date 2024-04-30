package br.com.alura.adopetstore.email;

import br.com.alura.adopetstore.dto.PedidoDTO;
import br.com.alura.adopetstore.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class EmailPedidoRealizado {

    private final EnviadorEmail enviador;

    public EmailPedidoRealizado(EnviadorEmail enviador) {
        this.enviador = enviador;
    }

    public void enviar(PedidoDTO dto, Usuario usuario){
        enviador.enviarEmail(
                "Pedido efetuado com sucesso na Adopet Store",
                usuario.getEmail(),
                "Olá! " + "!\n\nSeu pedido foi registrado. Itens: \n" + dto.itens());
    }
}
