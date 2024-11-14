package ra.api.categy.dto.mapper;

import ra.api.categy.dto.response.ResponseDepartmentDto;
import ra.api.categy.entity.Department;
import ra.api.categy.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentMapper {
    public static ResponseDepartmentDto mapEntitytoDTOReponse(Department department) {



        // Xây dựng DTO trả về
        return ResponseDepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .status(department.isStatus())
                .build();
    }
}
