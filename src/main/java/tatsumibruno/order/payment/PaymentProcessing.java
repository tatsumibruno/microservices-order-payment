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
public class PaymentProcessing {

    @StreamListener(OrderChannels.ORDERS_CREATED)
    @SendTo(OrderChannels.ORDERS_CHANGES)
    public OrderPaymentStatus process(CreatedOrder createdOrder) {
        log.info("Receiving new order to process payment: {}", createdOrder);
        OrderPaymentStatus paymentResult = sendToGateway(createdOrder);
        log.info("Payment processing results for order {}: {}", createdOrder.getCode(), paymentResult);
        return paymentResult;
    }

    private OrderPaymentStatus sendToGateway(CreatedOrder createdOrder) {
        try {
            //Simulação de "gargalo" na espera de um pagamento
            Thread.sleep(BigDecimal.valueOf(Math.random() * 10).longValue() * 1000);
        } catch (InterruptedException e) {
            log.error("Error while simulate the delay in payment gateway");
        }
        return new OrderPaymentStatus(
                createdOrder.getCode(),
                Math.random() <= 0.7 ? PaymentStatus.PAYMENT_APPROVED : PaymentStatus.PAYMENT_DENIED,
                ZonedDateTime.now());
    }
}
