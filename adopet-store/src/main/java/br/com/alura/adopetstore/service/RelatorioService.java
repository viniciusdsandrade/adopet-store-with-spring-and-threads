package br.com.alura.adopetstore.service;

import br.com.alura.adopetstore.dto.ProdutoDTO;
import br.com.alura.adopetstore.dto.RelatorioEstoque;
import br.com.alura.adopetstore.dto.RelatorioFaturamento;
import br.com.alura.adopetstore.repository.EstoqueRepository;
import br.com.alura.adopetstore.repository.PedidoRepository;
import br.com.alura.adopetstore.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;
    private final PedidoRepository pedidoRepository;

    public RelatorioService(ProdutoRepository produtoRepository,
                            EstoqueRepository estoqueRepository,
                            PedidoRepository pedidoRepository) {
        this.produtoRepository = produtoRepository;
        this.estoqueRepository = estoqueRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public RelatorioEstoque infoEstoque(){
        var produtosSemEstoque = estoqueRepository.produtosComEstoqueZerado()
                .stream().map(ProdutoDTO::new)
                .collect(Collectors.toList());
        return new RelatorioEstoque(produtosSemEstoque);
    }

    public RelatorioFaturamento faturamentoObtido() {
        var dataOntem = LocalDate.now().minusDays(1);
        var faturamentoTotal = pedidoRepository.faturamentoTotalDoDia(dataOntem);

        var estatisticas = pedidoRepository.faturamentoTotalDoDiaPorCategoria(dataOntem);

        return new RelatorioFaturamento(faturamentoTotal, estatisticas);
    }
}