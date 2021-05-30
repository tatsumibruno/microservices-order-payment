package tatsumibruno.order.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;
import tatsumibruno.order.payment.util.Constants;

import java.time.ZonedDateTime;

@Getter
@ToString
public class StatusPagamentoPedido {
    private final String code;
    private final StatusPagamento status;
    @JsonFormat(pattern = Constants.ISO_DATE_TIME)
    private final ZonedDateTime timestamp;

    public StatusPagamentoPedido(String code, StatusPagamento status, ZonedDateTime timestamp) {
        this.code = code;
        this.status = status;
        this.timestamp = timestamp;
    }
}
