package task.test.demo.service;

import org.springframework.stereotype.Service;
import task.test.demo.model.CategoryEntity;
import task.test.demo.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public CategoryEntity findById(Long id) {
        return repository.getOne(id);
    }

    public List<CategoryEntity> findAll() {
        return repository.findAll();
    }

    public CategoryEntity saveCategory(CategoryEntity category) {
        return repository.save(category);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
