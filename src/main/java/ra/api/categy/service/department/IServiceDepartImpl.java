package ra.api.categy.service.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.api.categy.dto.mapper.DepartmentMapper;
import ra.api.categy.dto.response.ResponseDepartmentDto;
import ra.api.categy.entity.Department;
import ra.api.categy.repository.IDepartmentRepo;

import java.util.List;

@Service
public class IServiceDepartImpl implements IServiceDepartment {
    @Autowired
    private IDepartmentRepo departmentRepo;

    @Override
    public List<ResponseDepartmentDto> findAllDto() {
        return departmentRepo.getAllDto().stream().map(DepartmentMapper::mapEntitytoDTOReponse).toList();
    }

    @Override
    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

    @Override
    public Department findById(Long id) {
        return departmentRepo.findById(id).orElse(null);
    }

    @Override
    public Department save(Department department) {
        return departmentRepo.save(department);
    }

    @Override
    public Department saveById(Department department) {
        Department department1 = Department.builder()
                .id(department.getId())
                .name(department.getName())
                .status(department.isStatus())
                .build();
        return departmentRepo.save(department1);
    }

    @Override
    public void delete(Long id) {
        departmentRepo.deleteById(id);
    }

    @Override
    public Page<Department> getAllDepartment(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(sort));

        return departmentRepo.findAll(pageable);
    }
}
