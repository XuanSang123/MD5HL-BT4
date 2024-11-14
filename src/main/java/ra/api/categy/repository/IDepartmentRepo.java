package ra.api.categy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ra.api.categy.dto.response.ResponseDepartmentDto;
import ra.api.categy.entity.Department;

import java.util.List;

public interface IDepartmentRepo extends JpaRepository<Department, Long> {

    @Query(value = "select d from Department d where d.status=true ")
    List<Department> getAllDto();

    Page<Department> findAll(Pageable pageable);


    @Query("select d from Department d where d.name like %:name%")
    List<Department> findByNameContaining(@Param("name") String name);


}
