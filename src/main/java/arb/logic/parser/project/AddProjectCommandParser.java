package arb.logic.parser.project;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static arb.logic.parser.CliSyntax.PREFIX_NAME;
import static arb.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import arb.logic.commands.project.AddProjectCommand;
import arb.logic.parser.ArgumentMultimap;
import arb.logic.parser.ArgumentTokenizer;
import arb.logic.parser.Parser;
import arb.logic.parser.ParserUtil;
import arb.logic.parser.Prefix;
import arb.logic.parser.exceptions.ParseException;
import arb.model.project.Deadline;
import arb.model.project.Project;
import arb.model.project.Title;
import arb.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddProjectCommand object
 */
public class AddProjectCommandParser implements Parser<AddProjectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddProjectCommand
     * and returns an AddProjectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddProjectCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DEADLINE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProjectCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_NAME).get());

        Optional<String> deadlineString = argMultimap.getValue(PREFIX_DEADLINE);
        Deadline deadline = null;
        if (deadlineString.isPresent()) {
            deadline = ParserUtil.parseDeadline(deadlineString.get());
        }

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Project project = new Project(title, deadline, tagList);

        return new AddProjectCommand(project);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
