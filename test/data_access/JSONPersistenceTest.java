package data_access;

import entity.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class JSONPersistenceTest {
    private JSONPersistence instance;
    private String filePath = "temp.json";
    private User filledUser;
    private User emptyUser;
    private Recipe[] recipes;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    @Before
    public void init() {
        /* Make the factories. */
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        UserFactory userFactory = new CommonUserFactory();
        /* Use factories to create some mocking data. */
        this.emptyUser = userFactory.create("empty", "user");
        this.filledUser = userFactory.create("asdf", "1234");
        this.recipes = new Recipe[]{
            recipeFactory.create(52802, "Fish pie", "Seafood", "instructions", "British"),
            recipeFactory.create(52872, "Spanish Tortilla", "Vegetarian", "instructions", "Spanish"),
            recipeFactory.create(52855, "Banana Pancakes", "Dessert", "instructions", "American"),
            recipeFactory.create(52870, "Chickpea Fajitas", "Vegetarian", "instructions", "Mexican"),
        };
        /* Add the recipes to the user. */
        this.filledUser.addFavourite(this.recipes[0]);
        this.filledUser.addFavourite(this.recipes[1]);
        this.filledUser.assignTag(this.recipes[2], "tag");
        this.filledUser.assignTag(this.recipes[3], "tag");

        /* Try and open the temporary file, fail if we can't. */
        try {
            folder.newFile(filePath);
        } catch (IOException e) {
            System.out.println("Couldn't make testing file");
            fail();
        }
        /* Make the instance. */
        this.instance = new JSONPersistence(userFactory, recipeFactory, filePath);
    }

    @Test
    public void testEmptyUserOutput() {
        instance.save(emptyUser);
        try {
            String fileContents = new String(Files.readAllBytes(Path.of(this.filePath)));
            assertTrue(fileContents.contains("favourites"));
            assertTrue(fileContents.contains("tags"));
            assertTrue(fileContents.contains("{}"));
            assertTrue(fileContents.contains("[]"));
        } catch (IOException e) {
            System.out.println("Error reading file after writing");
            fail();
        }
    }
    @Test
    public void testSingleUserOutput() {
        instance.save(filledUser);
        try {
            String fileContents = new String(Files.readAllBytes(Path.of(this.filePath)));
            /* Check that we have the right things in the contents of the files */
            assertTrue(fileContents.contains("favourites"));
            assertTrue(fileContents.contains("tags"));
            assertTrue(fileContents.contains("tag"));
            assertTrue(fileContents.contains("52802"));
            assertTrue(fileContents.contains("52872"));
            assertTrue(fileContents.contains("52855"));
            assertTrue(fileContents.contains("52870"));
        } catch (IOException e) {
            System.out.println("Error reading file after writing");
            fail();
        }
    }
}
