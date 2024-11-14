package ra.api.categy.dto.response;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.api.categy.entity.Department;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResponseEmployeeDto {

    private Long id;
    private String fullName;
    private String address;
    private String phone;
    private boolean status;
    private Long departmentId;
}
