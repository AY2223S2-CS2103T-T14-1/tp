package arb.model.project;

import static arb.logic.commands.CommandTestUtil.VALID_DEADLINE_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_DEADLINE_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_TAG_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_TITLE_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_TITLE_SKY_PAINTING;
import static arb.testutil.TypicalProjects.OIL_PAINTING;
import static arb.testutil.TypicalProjects.SKY_PAINTING;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import arb.testutil.ProjectBuilder;

public class ProjectTest {

    @Test
    public void equals() {

        Project defaultProject = new ProjectBuilder().build();
        Project defaultCopy = new ProjectBuilder().build();

        Project sky = new ProjectBuilder().withTitle(VALID_TITLE_SKY_PAINTING)
                .withDeadline(VALID_DEADLINE_SKY_PAINTING).build();

        assertFalse(defaultProject.equals(null)); // null
        assertFalse(defaultProject.equals(3)); // different type
        assertFalse(defaultProject.equals(sky)); // different project

        Project editedSky;
        editedSky = new ProjectBuilder(sky).withTitle(VALID_TITLE_OIL_PAINTING).build();
        assertFalse(sky.equals(editedSky)); // changed title
        editedSky = new ProjectBuilder(sky).withDeadline(VALID_DEADLINE_OIL_PAINTING).build();
        assertFalse(sky.equals(editedSky)); // changed deadline
        editedSky = new ProjectBuilder(sky).withStatus(true).build();
        assertFalse(sky.equals(editedSky)); // changed status
        editedSky = new ProjectBuilder(editedSky).withStatus(false).build();
        assertTrue(sky.equals(editedSky)); // reverted status
        editedSky = new ProjectBuilder(sky).withTags(VALID_TAG_PAINTING).build();
        assertFalse(sky.equals(editedSky)); // changed tags

        assertTrue(defaultProject.equals(defaultProject)); // Same instance
        assertTrue(defaultProject.equals(defaultCopy)); // Same details

        Project defaultProjectWithoutDeadline = new ProjectBuilder().withDeadline(null).build();
        Project defaultCopyWithoutDeadline = new ProjectBuilder().withDeadline(null).build();

        Project skyWithoutDeadline = new ProjectBuilder()
                .withTitle(VALID_TITLE_SKY_PAINTING).withDeadline(null).build();

        assertFalse(defaultProjectWithoutDeadline.equals(null)); // null
        assertFalse(defaultProjectWithoutDeadline.equals(3)); // different type
        assertFalse(defaultProjectWithoutDeadline.equals(skyWithoutDeadline)); //different project

        assertFalse(defaultProjectWithoutDeadline.equals(defaultProject)); // different deadlines

        Project editedSkyWithDeadline = new ProjectBuilder(skyWithoutDeadline)
                .withDeadline(VALID_DEADLINE_SKY_PAINTING).withStatus(true).build();
        editedSky = new ProjectBuilder(sky).withDeadline(VALID_DEADLINE_SKY_PAINTING).build();

        assertFalse(editedSkyWithDeadline.equals(editedSky));
        editedSkyWithDeadline = new ProjectBuilder(editedSkyWithDeadline)
                .withStatus(false).build();
        assertTrue(editedSkyWithDeadline.equals(editedSky));
        assertTrue(defaultProjectWithoutDeadline.equals(defaultCopyWithoutDeadline));
        assertTrue(defaultCopyWithoutDeadline.equals(defaultProjectWithoutDeadline));
    }

    @Test
    public void isSameProject() {
        // same object -> returns true
        assertTrue(SKY_PAINTING.isSameProject(SKY_PAINTING));

        // null -> returns false
        assertFalse(SKY_PAINTING.isSameProject(null));

        // same name, all other attributes different -> returns true
        Project editedSkyPainting = new ProjectBuilder(SKY_PAINTING).withDeadline(VALID_DEADLINE_OIL_PAINTING).build();
        editedSkyPainting.markAsDone();
        assertTrue(SKY_PAINTING.isSameProject(editedSkyPainting));

        // different name, all other attributes same -> returns false
        editedSkyPainting = new ProjectBuilder(SKY_PAINTING).withTitle(VALID_TITLE_OIL_PAINTING).build();
        assertFalse(SKY_PAINTING.isSameProject(editedSkyPainting));

        // name differs in case, all other attributes same -> returns false
        Project editedOilPainting = new ProjectBuilder(OIL_PAINTING)
                .withTitle(VALID_TITLE_OIL_PAINTING.toLowerCase()).build();
        assertFalse(OIL_PAINTING.isSameProject(editedOilPainting));

        // name has trailing spaces, all other attributes same -> returns false
        String titleWithTrailingSpaces = VALID_TITLE_OIL_PAINTING + " ";
        editedOilPainting = new ProjectBuilder(OIL_PAINTING).withTitle(titleWithTrailingSpaces).build();
        assertFalse(OIL_PAINTING.isSameProject(editedOilPainting));
    }
}
