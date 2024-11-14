package ra.api.categy.service.acount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.api.categy.entity.Account;
import ra.api.categy.repository.AccountRepo;

import java.util.List;

@Service
public class IServiceAccountImpl implements IServiceAcount {
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public List<Account> findAll() {
        return accountRepo.findAll();
    }

    @Override
    public Account findById(Long id) {
        return accountRepo.findById(id).orElse(null);
    }

    @Override
    public Account save(Account account) {

        return accountRepo.save(account);
    }

    @Override
    public void delete(Long id) {
        accountRepo.deleteById(id);
    }

    @Override
    public Account saveById(Account account) {
        Account account1 = Account.builder()
                .id(account.getId())
                .fullName(account.getFullName())
                .password(account.getPassword())
                .status(account.isStatus())
                .build();
        return accountRepo.save(account1);
    }
}
