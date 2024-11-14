package ra.api.categy.service.employee;

import ra.api.categy.dto.response.ResponseEmployeeDto;
import ra.api.categy.entity.Employee;
import ra.api.categy.exception.NotFoundException;
import ra.api.categy.service.IService;

import java.util.List;

public interface IServiceEmployee extends IService<Employee, Long> {
    //    Employee saveById(FormAddEmployee request);
    List<ResponseEmployeeDto> findAllDto();

    ResponseEmployeeDto findByIdDto(Long id);

    ResponseEmployeeDto saveById(Employee employee);

    List<ResponseEmployeeDto> getAllEmployeeByDepartment(Long id) throws NotFoundException;

}
