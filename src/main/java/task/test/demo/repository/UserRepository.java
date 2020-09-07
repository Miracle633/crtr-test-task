package task.test.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import task.test.demo.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "CALL GET_USER_CATEGORY_BY_IIN(:iin, :category);", nativeQuery = true)
    String findCategoryByIin(Long iin, String category);
}
