package tatsumibruno.order.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tatsumibruno.order.payment.util.Constants;

import java.time.ZonedDateTime;

@Getter
@ToString
@NoArgsConstructor
class NovoPedido {

    private String code;
    @JsonFormat(pattern = Constants.ISO_DATE_TIME)
    private ZonedDateTime createdAt;
}
