package guru.springframework.sfgrecipe.repository;

import guru.springframework.sfgrecipe.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
