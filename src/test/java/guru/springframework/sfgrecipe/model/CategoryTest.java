package guru.springframework.sfgrecipe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    Category category;

    @BeforeEach
    public void setup() {
        category = new Category();
    }

    @Test
    void getId() {
        Long exptedId = 4L;
        category.setId(exptedId);
        assertEquals(exptedId, category.getId());
    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecipies() {
    }
}