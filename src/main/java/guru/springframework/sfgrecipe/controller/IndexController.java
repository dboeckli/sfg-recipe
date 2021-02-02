package guru.springframework.sfgrecipe.controller;

import guru.springframework.sfgrecipe.model.Category;
import guru.springframework.sfgrecipe.model.UnitOfMeasure;
import guru.springframework.sfgrecipe.repository.CategoryRepository;
import guru.springframework.sfgrecipe.repository.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {

        Optional<Category> category = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Category is: " + category.get().getId());
        System.out.println("Unit Of Measure is: " + unitOfMeasure.get().getId());

        return "index";
    }

}
