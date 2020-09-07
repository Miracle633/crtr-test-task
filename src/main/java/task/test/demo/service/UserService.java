package task.test.demo.service;

import org.springframework.stereotype.Service;
import task.test.demo.model.UserEntity;
import task.test.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserEntity findById(Long id) {
        return repository.getOne(id);
    }

    public String findUserCategoryByIin(Long iin) {
        return repository.findCategoryByIin(iin, "category");
    }

    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    public UserEntity saveUser(UserEntity user) {
        return repository.save(user);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
