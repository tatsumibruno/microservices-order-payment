package tatsumibruno.order.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;
import tatsumibruno.order.payment.util.Constants;

import java.time.ZonedDateTime;

@Getter
@ToString
public class OrderPaymentStatus {
    private final String code;
    private final PaymentStatus status;
    @JsonFormat(pattern = Constants.ISO_DATE_TIME)
    private final ZonedDateTime timestamp;

    public OrderPaymentStatus(String code, PaymentStatus status, ZonedDateTime timestamp) {
        this.code = code;
        this.status = status;
        this.timestamp = timestamp;
    }
}
