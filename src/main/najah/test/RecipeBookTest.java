package main.najah.test;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RecipeBook Test Class")
@Execution(ExecutionMode.CONCURRENT)
public class RecipeBookTest {

    private RecipeBook recipeBook;

    @BeforeEach
    void setUp() {
        recipeBook = new RecipeBook();
        System.out.println("Setup complete");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test finished");
    }

    @Test
    @DisplayName("Add a recipe successfully")
    void testAddRecipeSuccess() {
        Recipe recipe = new Recipe();
        recipe.setName("Mocha");

        boolean added = recipeBook.addRecipe(recipe);
        assertTrue(added, "Recipe should be added");
        assertEquals("Mocha", recipeBook.getRecipes()[0].getName());
    }

    @Test
    @DisplayName("Do not add duplicate recipe")
    void testAddDuplicateRecipe() {
        Recipe recipe1 = new Recipe();
        recipe1.setName("Espresso");

        Recipe recipe2 = new Recipe();
        recipe2.setName("Espresso");

        recipeBook.addRecipe(recipe1);
        boolean added = recipeBook.addRecipe(recipe2);

        assertFalse(added, "Duplicate recipe should not be added");
    }

    @Test
    @DisplayName("Delete existing recipe")
    void testDeleteRecipeSuccess() {
        Recipe recipe = new Recipe();
        recipe.setName("Latte");
        recipeBook.addRecipe(recipe);

        String deleted = recipeBook.deleteRecipe(0);
        assertEquals("Latte", deleted);
    }

    @Test
    @DisplayName("Delete non-existing recipe")
    void testDeleteNonExistingRecipe() {
        String deleted = recipeBook.deleteRecipe(2);
        assertNull(deleted);
    }

    @Test
    @DisplayName("Edit existing recipe")
    void testEditRecipeSuccess() {
        Recipe oldRecipe = new Recipe();
        oldRecipe.setName("Cappuccino");
        recipeBook.addRecipe(oldRecipe);

        Recipe newRecipe = new Recipe();
        newRecipe.setName("Caramel");

        String oldName = recipeBook.editRecipe(0, newRecipe);

        assertEquals("Cappuccino", oldName);
        assertEquals("", recipeBook.getRecipes()[0].getName(), "New recipe name should be empty due to setName override");
    }

    @Test
    @DisplayName("Edit non-existing recipe")
    void testEditNonExistingRecipe() {
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Americano");

        String result = recipeBook.editRecipe(1, newRecipe);
        assertNull(result, "Should return null when editing non-existing recipe");
    }

    @Test
    @DisplayName("Timeout test")
    @Timeout(1)
    void timeoutTest() {
        Recipe recipe = new Recipe();
        recipe.setName("Test");
        recipeBook.addRecipe(recipe);
        assertNotNull(recipeBook.getRecipes()[0]);
    }

    @ParameterizedTest
    @DisplayName("Add multiple unique recipes")
    @ValueSource(strings = {"R1", "R2", "R3", "R4"})
    void testAddMultipleRecipes(String name) {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        boolean added = recipeBook.addRecipe(recipe);
        assertTrue(added, "Each unique recipe should be added");
    }

    @Test
    @Disabled("Fix logic before enabling")
    @DisplayName("Disabled test for invalid index")
    void disabledTest() {
        Recipe recipe = new Recipe();
        recipe.setName("Test");

        String result = recipeBook.deleteRecipe(99);
        assertEquals("Recipe not found", result); 
    }
}
