package task.test.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import task.test.demo.model.CategoryEntity;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query("SELECT c.name, count(u.id) FROM CategoryEntity c join c.users u group by c.name")
    List<Object[]> countTotalUsersOfCategory();
}
