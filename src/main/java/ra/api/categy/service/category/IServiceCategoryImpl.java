package ra.api.categy.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.api.categy.entity.Category;
import ra.api.categy.repository.ICategoryRepo;

import java.util.List;

@Service
public class IServiceCategoryImpl implements IServiceCategory {
    @Autowired
    private ICategoryRepo categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category save(Category category) {
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category saveById(Category category) {
        Category category1 = Category.builder()
                .id(category.getId())
                .name(category.getName())
                .status(category.isStatus())
                .build();
        return categoryRepository.save(category1);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);

    }
}
