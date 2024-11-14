package ra.api.categy.service.department;

import org.springframework.data.domain.Page;
import ra.api.categy.dto.response.ResponseDepartmentDto;
import ra.api.categy.entity.Account;
import ra.api.categy.entity.Department;
import ra.api.categy.service.IService;

import java.util.List;

public interface IServiceDepartment extends IService<Department,Long> {
    Department saveById(Department department);
    List<ResponseDepartmentDto> findAllDto();
    Page<Department> getAllDepartment(int page, int size, String sort);

}
