package ra.api.categy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.api.categy.dto.response.ResponseDto;
import ra.api.categy.entity.Category;
import ra.api.categy.service.category.IServiceCategory;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private IServiceCategory category;


    @GetMapping
    public ResponseEntity<ResponseDto> getAll() {
        List<Category> list = category.findAll();
//        return new ResponseEntity<>(new CategoryResponse(list,HttpStatus.OK,HttpStatus.OK.value()).getHttpStatus());
        return ResponseEntity.ok().body(new ResponseDto(list, HttpStatus.OK, HttpStatus.OK.value()));
    }

    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody Category ca) {
        Category cate = category.save(ca);
        return new ResponseEntity<>(new ResponseDto(cate, HttpStatus.CREATED, HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable Long id) {
        Category category1 = category.findById(id);
        if (category1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseDto(category1, HttpStatus.OK, HttpStatus.OK.value()), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable Long id, @RequestBody Category cate) {

        Category category1 = category.findById(id);
        if (category1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        category1.setId(cate.getId());
        category1.setName(cate.getName());
        category1.setStatus(cate.isStatus());
        return new ResponseEntity<>(new ResponseDto(category.save(category1), HttpStatus.OK, HttpStatus.OK.value()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id) {

        Category category1 = category.findById(id);
        if (category1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        category.delete(id);
        return new ResponseEntity<>(new ResponseDto(category1, HttpStatus.OK, HttpStatus.OK.value()), HttpStatus.OK);
    }

}
