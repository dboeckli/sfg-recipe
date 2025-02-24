package guru.springframework.sfgrecipe.bootstrap;

import guru.springframework.sfgrecipe.model.Category;
import guru.springframework.sfgrecipe.model.Difficulty;
import guru.springframework.sfgrecipe.model.Recipe;
import guru.springframework.sfgrecipe.repository.CategoryRepository;
import guru.springframework.sfgrecipe.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {

        Category americanCategory = categoryRepository.findByDescription("American").get();
        Category mexicanCategory = categoryRepository.findByDescription("Mexican").get();

        log.info("Categories: {}", categoryRepository.count());

        Recipe guacamole = new Recipe();
        guacamole.setDescription("Guacamole");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.getCategories().add(americanCategory);
        guacamole.getCategories().add(mexicanCategory);
        recipeRepository.save(guacamole);

        Recipe tacos = new Recipe();
        tacos.setDescription("Spicy Grilled Chicken Tacos");
        tacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacos.setDifficulty(Difficulty.EASY);
        tacos.getCategories().add(americanCategory);
        recipeRepository.save(tacos);

        log.info("Saved {} recipes", recipeRepository.count());
        log.info("init done");
   }
}
