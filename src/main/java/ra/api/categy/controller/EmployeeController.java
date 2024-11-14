package ra.api.categy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.api.categy.dto.mapper.EmployeeMapper;
import ra.api.categy.dto.request.FormAddEmployee;
import ra.api.categy.dto.response.ResponseDto;
import ra.api.categy.dto.response.ResponseEmployeeDto;
import ra.api.categy.entity.Employee;
import ra.api.categy.exception.NotFoundException;
import ra.api.categy.repository.EmployeeRepo;
import ra.api.categy.service.employee.IServiceEmployee;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private IServiceEmployee iServiceEmployee;
    @Autowired
    public EmployeeMapper mapper;
    @Autowired
    public EmployeeRepo employeeRepo;

    @GetMapping
    public ResponseEntity<ResponseDto> getAllEmployee() {
        List<ResponseEmployeeDto> list = iServiceEmployee.findAllDto();
        return new ResponseEntity<>(new ResponseDto(list, HttpStatus.OK, HttpStatus.OK.value()), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getEmployeeById(@PathVariable Long id) {
        ResponseEmployeeDto employee1 = iServiceEmployee.findByIdDto(id);
        if (employee1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseDto(employee1, HttpStatus.CREATED, HttpStatus.CREATED.value()), HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<ResponseDto> addEmploy(@RequestBody FormAddEmployee request) {

//        Employee e1 = EmployeeMapper.mapRequestToEntity(request);
//        ResponseEmployeeDto responseEmployeeDto = iServiceEmployee.saveById(e1);
        Employee e1 = iServiceEmployee.save(mapper.mapRequestToEntity(request));

        ResponseEmployeeDto responseEmployeeDto = mapper.mapEntitytoDTOReponse(e1);
        return new ResponseEntity<>(new ResponseDto(responseEmployeeDto, HttpStatus.CREATED, HttpStatus.OK.value()), HttpStatus.CREATED);


    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateEmploy(@PathVariable Long id, @RequestBody FormAddEmployee request) {
        Employee existingEmployee = iServiceEmployee.findById(id);

        if (existingEmployee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Employee updatedEmployee = mapper.mapRequestToEntity(request);

        updatedEmployee.setId(id);

        ResponseEmployeeDto responseDto = iServiceEmployee.saveById(updatedEmployee);

        return new ResponseEntity<>(new ResponseDto(responseDto, HttpStatus.OK, HttpStatus.OK.value()), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable Long id
    ) {
        Employee employee1 = iServiceEmployee.findById(id);
        if (employee1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HttpStatus status = HttpStatus.OK;

        iServiceEmployee.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDto> searchEmployee(@RequestParam String name) {

        if (name == null || name.trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<ResponseEmployeeDto> list = employeeRepo.searchByFullName(name).stream().map(employee -> mapper.mapEntitytoDTOReponse(employee)).toList();

        return new ResponseEntity<>(new ResponseDto(list, HttpStatus.OK, HttpStatus.OK.value()), HttpStatus.OK);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<ResponseDto> getAllEmployeeByDepartment(@PathVariable Long id) throws NotFoundException {

        List<ResponseEmployeeDto> list = iServiceEmployee.getAllEmployeeByDepartment(id);
        return new ResponseEntity<>(new ResponseDto(list, HttpStatus.OK, HttpStatus.OK.value()), HttpStatus.OK);


    }
}
