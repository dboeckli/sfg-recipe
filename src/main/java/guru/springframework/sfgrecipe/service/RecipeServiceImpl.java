package guru.springframework.sfgrecipe.service;

import guru.springframework.sfgrecipe.model.Recipe;
import guru.springframework.sfgrecipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> getAllRecipe() {
        ArrayList<Recipe> recipees = new ArrayList<>();
        recipeRepository.findAll().forEach(recipe -> {
            recipees.add(recipe);
        });
        return recipees;
    }
}
