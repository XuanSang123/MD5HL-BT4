package ra.api.categy.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.api.categy.dto.mapper.EmployeeMapper;
import ra.api.categy.dto.response.ResponseEmployeeDto;
import ra.api.categy.entity.Department;
import ra.api.categy.entity.Employee;
import ra.api.categy.exception.NotFoundException;
import ra.api.categy.repository.EmployeeRepo;
import ra.api.categy.repository.IDepartmentRepo;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IServiceEmployeeImpl implements IServiceEmployee {
    @Autowired
    private EmployeeRepo serviceEmployee;
    @Autowired
    private EmployeeMapper mapper;
    @Autowired
    private IDepartmentRepo iDepartmentRepo;

//    @Override
//    public List<ResponseEmployeeDto> findAllDto() {
//        return serviceEmployee.findAll().stream().map(EmployeeMapper::mapEntitytoDTOReponse).toList();
//    }

    @Override
    public List<ResponseEmployeeDto> findAllDto() {
        return serviceEmployee.findAll().stream().map(employee -> mapper.mapEntitytoDTOReponse(employee)).toList();
    }

    @Override
    public List<Employee> findAll() {
        return serviceEmployee.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return serviceEmployee.findById(id).orElse(null);
    }

    @Override
    public ResponseEmployeeDto findByIdDto(Long id) {
        Employee employee1 = serviceEmployee.findById(id).orElse(null);
        assert employee1 != null;
        return mapper.mapEntitytoDTOReponse(employee1);
    }

    @Override
    public Employee save(Employee employee) {
        return serviceEmployee.save(employee);
    }

    @Override
    public ResponseEmployeeDto saveById(Employee employee) {
        Employee savedEmployee = serviceEmployee.save(employee);
        return mapper.mapEntitytoDTOReponse(savedEmployee);


    }
//    @Override
//    public ResponseEmployeeDto saveById(F id) {
//        Employee employee1 = serviceEmployee.findById(id).orElse(null);
//        assert employee1 != null;
//        return EmployeeMapper.mapEntitytoDTOReponse(employee1);
//    }

    @Override
    public void delete(Long id) {
        serviceEmployee.deleteById(id);
    }

    @Override
    public List<ResponseEmployeeDto> getAllEmployeeByDepartment(Long id) throws NotFoundException {

        Department department1 = iDepartmentRepo.findById(id).orElseThrow(() -> new NotFoundException("not Found exception "));

        if (department1 != null) {
            List<Employee> employees = serviceEmployee.findAll();
            return employees.stream().filter(e -> e.getDepartmentId().getId().equals(id)).map(employee -> mapper.mapEntitytoDTOReponse(employee)).toList();

        } else return null;


    }
}

