package ra.api.categy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.api.categy.dto.mapper.DepartmentMapper;
import ra.api.categy.dto.response.ResponseDepartmentDto;
import ra.api.categy.dto.response.ResponseDto;
import ra.api.categy.entity.Category;
import ra.api.categy.entity.Department;
import ra.api.categy.repository.IDepartmentRepo;
import ra.api.categy.service.department.IServiceDepartment;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private IServiceDepartment iServiceDepartment;
    @Autowired
    private IDepartmentRepo iDepartmentRepo;

//    @GetMapping
//    public ResponseEntity<ResponseDto> getAllDepartment() {
//        List<ResponseDepartmentDto> list = iServiceDepartment.findAllDto();
//        return new ResponseEntity<>(new ResponseDto(list, HttpStatus.OK, HttpStatus.OK.value()), HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllDepartment(@RequestParam(defaultValue = "0") int size,
                                                                @RequestParam(defaultValue = "2") int page,
                                                                @RequestParam(defaultValue = "name") String sort) {
        Page<Department> list = iServiceDepartment.getAllDepartment(size, page, sort);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("pageNumber", list.getPageable().getPageNumber());
        response.put("pageSize", list.getPageable().getPageSize());
        List<ResponseDepartmentDto> departmentDto = list.map(DepartmentMapper::mapEntitytoDTOReponse).toList();
        response.put("data", departmentDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(new ResponseDto(iServiceDepartment.save(department), HttpStatus.CREATED, HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getDepartmentById(@PathVariable Long id) {
        Department department1 = iServiceDepartment.findById(id);
        if (department1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseDto(department1, HttpStatus.OK, HttpStatus.OK.value()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        Department department1 = iServiceDepartment.findById(id);

        if (department1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseDto(iServiceDepartment.save(department), HttpStatus.OK, HttpStatus.OK.value()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteDepartment(@PathVariable Long id) {
        Department department1 = iServiceDepartment.findById(id);
        if (department1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iServiceDepartment.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ResponseDto> updateStatusDepartment(@PathVariable Long id, @RequestParam boolean status) {

        Department department1 = iServiceDepartment.findById(id);
        if (department1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        department1.setStatus(status);

        return new ResponseEntity<>(new ResponseDto(iServiceDepartment.save(department1), HttpStatus.OK, HttpStatus.OK.value()), HttpStatus.OK);

    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDto> searchByNameDepartment(@RequestParam String name) {
        if (name == null || name.trim().isEmpty()) {
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Department> list = iDepartmentRepo.findByNameContaining(name);
        return new ResponseEntity<>(new ResponseDto(list, HttpStatus.OK, HttpStatus.OK.value()), HttpStatus.OK);
    }
}
