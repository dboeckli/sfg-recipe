package guru.springframework.sfgrecipe.controller;

import guru.springframework.sfgrecipe.model.Category;
import guru.springframework.sfgrecipe.model.Recipe;
import guru.springframework.sfgrecipe.model.UnitOfMeasure;
import guru.springframework.sfgrecipe.repository.CategoryRepository;
import guru.springframework.sfgrecipe.repository.UnitOfMeasureRepository;
import guru.springframework.sfgrecipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipeService recipeService;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Mock
    Model model;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        indexController = new IndexController(categoryRepository, unitOfMeasureRepository, recipeService);
    }

    @Test
    void getIndexPageWithMockMvc() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        // Given
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        recipes.add(recipe1);
        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);
        when(recipeService.getAllRecipe()).thenReturn(recipes);
        when(categoryRepository.findByDescription("American")).thenReturn(Optional.of(new Category()));
        when(unitOfMeasureRepository.findByDescription("Teaspoon")).thenReturn(Optional.of(new UnitOfMeasure()));

        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
    }

    @Test
    void getIndexPage() {

        // Given
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        recipes.add(recipe1);
        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);
        recipes.add(recipe2);

        // when
        when(recipeService.getAllRecipe()).thenReturn(recipes);
        when(categoryRepository.findByDescription("American")).thenReturn(Optional.of(new Category()));
        when(unitOfMeasureRepository.findByDescription("Teaspoon")).thenReturn(Optional.of(new UnitOfMeasure()));
        ArgumentCaptor<List<Recipe>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        String indexPage = indexController.getIndexPage(model);

        // then
        assertEquals("index", indexPage);
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

        List<Recipe> listOfRecipesInController = argumentCaptor.getValue();
        assertEquals(2, listOfRecipesInController.size());
    }
}
