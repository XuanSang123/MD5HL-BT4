package ra.api.categy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ra.api.categy.entity.Employee;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    @Query(value = "select e from Employee e")
    List<Employee> getAllDto();

    @Query("select e from Employee e where e.fullName like %:name%")
    List<Employee> searchByFullName(@Param("name") String name);
}
