package guru.springframework.sfgrecipe.service;

import guru.springframework.sfgrecipe.model.Recipe;
import guru.springframework.sfgrecipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    final RecipeRepository recipeRepository;

    @Override
    public List<Recipe> getAllRecipe() {
        ArrayList<Recipe> recipees = new ArrayList<>();
        recipeRepository.findAll().forEach(recipees::add);
        return recipees;
    }
}
