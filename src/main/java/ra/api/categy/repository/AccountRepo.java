package ra.api.categy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.api.categy.entity.Account;

public interface AccountRepo extends JpaRepository<Account,Long> {
}
