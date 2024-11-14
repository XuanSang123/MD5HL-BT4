package ra.api.categy.service.category;

import ra.api.categy.entity.Account;
import ra.api.categy.entity.Category;
import ra.api.categy.service.IService;

public interface IServiceCategory extends IService<Category,Long> {

    Category saveById(Category category);

}
