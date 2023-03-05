package arb.logic.commands;

import arb.model.ListType;
import arb.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

    @Override
    public CommandResult execute(Model model, ListType currentListType) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, ListType.NONE);
    }

}
