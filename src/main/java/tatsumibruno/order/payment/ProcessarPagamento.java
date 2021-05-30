package tatsumibruno.order.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessarPagamento {

    @StreamListener(OrderChannels.ORDERS_CREATED)
    @SendTo(OrderChannels.ORDERS_CHANGES)
    public StatusPagamentoPedido executar(NovoPedido novoPedido) {
        log.info("Recebendo um novo pedido para processar pagamento: {}", novoPedido);
        StatusPagamentoPedido resultadoPagamento = enviarGatewayPagamento(novoPedido);
        log.info("Resultado do pagamento para o pedido {}: {}", novoPedido.getCode(), resultadoPagamento);
        return resultadoPagamento;
    }

    private StatusPagamentoPedido enviarGatewayPagamento(NovoPedido novoPedido) {
        try {
            //Simulação de "gargalo" na espera de um pagamento
            Thread.sleep(BigDecimal.valueOf(Math.random() * 10).longValue() * 1000);
        } catch (InterruptedException e) {
            log.error("Erro ao simular demora no processamento do pagamento");
        }
        return new StatusPagamentoPedido(
                novoPedido.getCode(),
                Math.random() <= 0.7 ? StatusPagamento.PAYMENT_APPROVED : StatusPagamento.PAYMENT_DENIED,
                ZonedDateTime.now());
    }
}
