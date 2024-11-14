package ra.api.categy.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.api.categy.dto.request.FormAddEmployee;
import ra.api.categy.dto.response.ResponseEmployeeDto;
import ra.api.categy.entity.Department;
import ra.api.categy.entity.Employee;
import ra.api.categy.repository.IDepartmentRepo;

@Component
public class EmployeeMapper {

    @Autowired
    private IDepartmentRepo iDepartmentRepo; // Không dùng từ khóa static

    // Chuyển đổi FormAddEmployee sang đối tượng Employee
    public  Employee mapRequestToEntity(FormAddEmployee request) {
        Long departmentId = request.getDepartmentId(); // Sử dụng Long thay vì Integer

        // Tìm đối tượng Department dựa trên departmentId
        Department department = iDepartmentRepo.findById(departmentId).orElse(null);

        // Xây dựng đối tượng Employee
        return Employee.builder()
                .fullName(request.getFullName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .status(request.isStatus())
                .departmentId(department) // Gán đối tượng Department vào Employee
                .build();
    }

    // Chuyển đổi Employee sang ResponseEmployeeDto
    public  ResponseEmployeeDto mapEntitytoDTOReponse(Employee employee) {
        Long departmentId = employee.getDepartmentId() != null ? employee.getDepartmentId().getId() : null; // Kiểm tra null
        return ResponseEmployeeDto.builder()
                .id(employee.getId())
                .fullName(employee.getFullName())
                .address(employee.getAddress())
                .phone(employee.getPhone())
                .status(employee.isStatus())
                .departmentId(departmentId) // Gán ID của Department vào DTO
                .build();
    }
}
