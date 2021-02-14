package guru.springframework.sfgrecipe.controller;

import guru.springframework.sfgrecipe.model.Category;
import guru.springframework.sfgrecipe.model.UnitOfMeasure;
import guru.springframework.sfgrecipe.repository.CategoryRepository;
import guru.springframework.sfgrecipe.repository.UnitOfMeasureRepository;
import guru.springframework.sfgrecipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    private RecipeService recipeService;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {

        Optional<Category> category = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");

        log.info("Category is: " + category.get().getId());
        log.info("Unit Of Measure is: " + unitOfMeasure.get().getId());

        model.addAttribute("recipes", recipeService.getAllRecipe());

        return "index";
    }

}
