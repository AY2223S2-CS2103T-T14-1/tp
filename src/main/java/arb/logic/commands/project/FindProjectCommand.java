package arb.logic.commands.project;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import arb.commons.core.Messages;
import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.model.ListType;
import arb.model.Model;
import arb.model.project.Project;

/**
 * Finds and lists all projects in address book whose title contains any of the argument keywords given
 * and contains any of the tags given and falls within the provided timeframe. Keyword matching is case insensitive.
 */
public class FindProjectCommand extends Command {

    private static final String MAIN_COMMAND_WORD = "find-project";
    private static final String ALIAS_COMMAND_WORD = "fp";
    private static final Set<String> COMMAND_WORDS =
            new HashSet<>(Arrays.asList(MAIN_COMMAND_WORD, ALIAS_COMMAND_WORD));

    public static final String MESSAGE_USAGE = MAIN_COMMAND_WORD + ": Finds all projects whose titles contain any of "
            + "the specified keywords (case-insensitive). contains any of the tags given (case-insensitive), "
            + "falls within the given timeframe and is linked to the client with the provided client name and displays "
            + "them as a list with index numbers.\n"
            + "Parameters: name/TITLE status/STATUS tag/TAG client/CLIENT start/START OF TIMEFRAME end/END OF TIMEFRAME...\n"
            + "Example: " + MAIN_COMMAND_WORD + " name/sculpture name/digital status/not done client/alice tag/personal start/last "
            + "week end/next year";

    private final Predicate<Project> predicate;

    public FindProjectCommand(Predicate<Project> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) {
        requireNonNull(model);
        model.updateFilteredProjectList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PROJECTS_LISTED_OVERVIEW, model.getFilteredProjectList().size()),
                ListType.PROJECT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindProjectCommand // instanceof handles nulls
                && predicate.equals(((FindProjectCommand) other).predicate)); // state check
    }

    public static boolean isCommandWord(String commandWord) {
        return COMMAND_WORDS.contains(commandWord);
    }

    public static List<String> getCommandWords() {
        return new ArrayList<>(COMMAND_WORDS);
    }
}
