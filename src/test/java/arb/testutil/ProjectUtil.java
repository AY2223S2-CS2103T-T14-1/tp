package arb.testutil;

import static arb.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static arb.logic.parser.CliSyntax.PREFIX_NAME;
import static arb.logic.parser.CliSyntax.PREFIX_PRICE;
import static arb.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import arb.logic.commands.project.AddProjectCommand;
import arb.logic.commands.project.EditProjectCommand.EditProjectDescriptor;
import arb.logic.commands.project.FindProjectCommand;
import arb.model.project.Project;

/**
 * A utility class for Project.
 */
public class ProjectUtil {

    /**
     * Returns an add project command string for adding the {@code project}.
     */
    public static String getAddProjectCommand(Project project, String commandWord) {
        assert AddProjectCommand.isCommandWord(commandWord);
        return commandWord + " " + getProjectDetails(project);
    }

    /**
     * Returns a find project command string to find a project.
     */
    public static String getFindProjectCommand(List<String> tags, List<String> names, String commandWord) {
        assert FindProjectCommand.isCommandWord(commandWord);
        StringBuilder sb = new StringBuilder();
        sb.append(commandWord + " ");
        tags.stream().forEach(
            t -> sb.append(PREFIX_TAG + t + " ")
        );
        names.stream().forEach(
            n -> sb.append(PREFIX_NAME + n + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code project}'s details.
     */
    public static String getProjectDetails(Project project) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + project.getTitle().fullTitle + " ");
        sb.append(PREFIX_DEADLINE + project.getDeadline().toString() + " ");
        sb.append(PREFIX_PRICE + project.getPrice().getPrice() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditProjectDescriptor}'s details.
     */
    public static String getEditProjectDescriptorDetails(EditProjectDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getTitle().ifPresent(title -> sb.append(PREFIX_NAME).append(title.fullTitle).append(" "));
        descriptor.getDeadline().ifPresent(deadline -> sb.append(PREFIX_DEADLINE)
                .append(deadline.toString()).append(" "));
        descriptor.getPrice().ifPresent(price -> sb.append(PREFIX_PRICE)
                .append(price.getPrice().toString()).append(" "));
        return sb.toString();
    }
}
