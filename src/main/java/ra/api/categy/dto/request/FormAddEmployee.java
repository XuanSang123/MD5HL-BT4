package ra.api.categy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FormAddEmployee {

    private String fullName;
    private String address;
    private String phone;
    private boolean status;
    private Long departmentId;
}
