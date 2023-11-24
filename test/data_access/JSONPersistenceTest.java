package data_access;

import entity.*;

import org.json.JSONException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class JSONPersistenceTest {
    private JSONPersistence instance;
    private final String filePath = "temp.json";
    private User filledUser;
    private User emptyUser;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    @Before
    public void init() {
        /* Make the factories. */
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        UserFactory userFactory = new CommonUserFactory();
        /* Use factories to create some mocking data. */
        this.emptyUser = userFactory.create("empty");
        this.filledUser = userFactory.create("asdf");
        Recipe[] recipes = new Recipe[]{
                recipeFactory.create(52802, "Fish pie", "Seafood", "instructions", "British"),
                recipeFactory.create(52872, "Spanish Tortilla", "Vegetarian", "instructions", "Spanish"),
                recipeFactory.create(52855, "Banana Pancakes", "Dessert", "instructions", "American"),
                recipeFactory.create(52870, "Chickpea Fajitas", "Vegetarian", "instructions", "Mexican"),
        };
        /* Add the recipes to the user. */
        this.filledUser.addFavourite(recipes[0]);
        this.filledUser.addFavourite(recipes[1]);
        this.filledUser.assignTag(recipes[2], "tag");
        this.filledUser.assignTag(recipes[3], "tag");

        /* Try and open the temporary file, fail if we can't. */
        try {
            folder.newFile(filePath);
        } catch (IOException e) {
            System.out.println("Couldn't make testing file");
            fail();
        }
        /* Make the instance. */
        RecipeAPI recipeDAO = new TheMealDB(recipeFactory);
        this.instance = new JSONPersistence(userFactory,
                String.valueOf(folder.getRoot()).concat("/" + filePath),
                recipeDAO);
    }

    @Test
    public void testEmptyUserOutput() throws IOException {
        instance.save(emptyUser);
        String fileContents = new String(Files.readAllBytes(
                Path.of(String.valueOf(folder.getRoot()).concat("/" + this.filePath))
        ));
        assertTrue(fileContents.contains("favourites"));
        assertTrue(fileContents.contains("tags"));
        assertTrue(fileContents.contains("{}"));
        assertTrue(fileContents.contains("[]"));
    }

    @Test
    public void testSingleUserOutput() throws IOException {
        instance.save(filledUser);
        String fileContents = new String(Files.readAllBytes(
                Path.of(String.valueOf(folder.getRoot()).concat("/" + this.filePath))
        ));
        /* Check that we have the right things in the contents of the files */
        assertTrue(fileContents.contains("asdf"));
        assertTrue(fileContents.contains("favourites"));
        assertTrue(fileContents.contains("tags"));
        assertTrue(fileContents.contains("tag"));
        assertTrue(fileContents.contains("52802"));
        assertTrue(fileContents.contains("52872"));
        assertTrue(fileContents.contains("52855"));
        assertTrue(fileContents.contains("52870"));
    }

    @Test
    public void testReadingContents() throws IOException {
        /* Write some testing data to the file */
        File filePath = new File(String.valueOf(folder.getRoot()), "temp.json");
        new PrintWriter(filePath).close();
        PrintWriter file = new PrintWriter(filePath);
        file.println("{\n" +
                "    \"favourites\": [\n" +
                "        52802,\n" +
                "        52872\n" +
                "    ],\n" +
                "    \"username\": \"asdf\",\n" +
                "    \"tags\": {\"tag\": [\n" +
                "        52855,\n" +
                "        52870\n" +
                "    ]}\n" +
                "}");
        file.close();
        Set<Integer> actualFavouriteIds = Set.of(52802, 52872);
        Set<Integer> actualTaggedIds = Set.of(52855, 52870);

        User user = instance.load();
        assertEquals(user.getUsername(), "asdf");
        assertEquals(user.getFavourites().size(), 2);

        Integer[] favouriteIds = new Integer[2];
        for (int i = 0; i < user.getFavourites().size(); i++) {
            favouriteIds[i] = user.getFavourites().get(i).getMealId();
        }

        Integer[] taggedIds = new Integer[2];
        ArrayList<Recipe> taggedRecipes = user.getTaggedRecipes().get("tag");
        for (int i = 0; i < taggedRecipes.size(); i++) {
            taggedIds[i] = taggedRecipes.get(i).getMealId();
        }

        assertEquals(favouriteIds.length, 2);
        assertTrue(actualFavouriteIds.containsAll(List.of(favouriteIds)));
        assertTrue(actualTaggedIds.containsAll(List.of(taggedIds)));
    }

    @Test
    public void TestEmptyFileLoad() throws IOException {
        /* Write empty file */
        File filePath = new File(String.valueOf(folder.getRoot()), "temp.json");
        new PrintWriter(filePath).close();
        try {
            instance.load();
        } catch (RuntimeException e) {
            /* Successfully threw an exception, so do nothing */
        }
    }
}
