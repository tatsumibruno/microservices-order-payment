package tatsumibruno.order.payment;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

interface OrderChannels {
    String ORDERS_CREATED = "orders-created";
    String ORDERS_CHANGES = "orders-changes";

    @Input(ORDERS_CREATED)
    SubscribableChannel ordersCreated();

    @Output(ORDERS_CHANGES)
    MessageChannel ordersChanges();
}
