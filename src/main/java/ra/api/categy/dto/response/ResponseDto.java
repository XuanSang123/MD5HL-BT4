package ra.api.categy.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDto {
    private Object data;
    private HttpStatus httpStatus;
    private int code;
}
