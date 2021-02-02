package guru.springframework.sfgrecipe.repository;

import guru.springframework.sfgrecipe.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
