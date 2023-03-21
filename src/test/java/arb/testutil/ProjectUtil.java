package arb.testutil;

import static arb.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static arb.logic.parser.CliSyntax.PREFIX_NAME;
import static arb.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import arb.logic.commands.project.AddProjectCommand;
import arb.logic.commands.project.EditProjectCommand.EditProjectDescriptor;
import arb.model.project.Project;
import arb.model.tag.Tag;

/**
 * A utility class for Project.
 */
public class ProjectUtil {

    /**
     * Returns an add project command string for adding the {@code project}.
     */
    public static String getAddProjectCommand(Project project) {
        return AddProjectCommand.COMMAND_WORD + " " + getProjectDetails(project);
    }

    /**
     * Returns the part of command string for the given {@code project}'s details.
     */
    public static String getProjectDetails(Project project) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + project.getTitle().fullTitle + " ");
        sb.append(PREFIX_DEADLINE + project.getDeadline().toString() + " ");
        project.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
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
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
