package ra.api.categy.service.acount;

import ra.api.categy.entity.Account;
import ra.api.categy.service.IService;

public interface IServiceAcount extends IService<Account,Long> {
    Account saveById(Account account);
}
