package arb.logic.commands.project;

import static java.util.Objects.requireNonNull;

import java.util.List;

import arb.commons.core.Messages;
import arb.commons.core.index.Index;
import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.logic.commands.exceptions.CommandException;
import arb.model.ListType;
import arb.model.Model;
import arb.model.client.Client;

public class LinkProjectToClientCommand extends Command {

    public static final String MESSAGE_LINK_PROJECT_TO_CLIENT_SUCCESS = "Successfully linked the project to %1$s";

    public static final String MESSAGE_USAGE = "Please select a client to link this project to.\n"
            + "For example, enter 1 to select the first client in the list.";

    private final Index targetIndex;

    /**
     * Creates a DeleteProjectCommand to delete the specified {@code Project}
     */
    public LinkProjectToClientCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) throws CommandException {
        requireNonNull(model);
        assert currentListBeingShown == ListType.CLIENT : "This command should only be executed " +
                "with a client list being shown";

        List<Client> lastShownList = model.getSortedClientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        // add an error check here for this project is already linked?
        // what if for edit we already have a linked project? Do we just make it do nothing instead?

        Client clientToLinkTo = lastShownList.get(targetIndex.getZeroBased());
        model.linkProjectToClient(clientToLinkTo);
        return new CommandResult(String.format(MESSAGE_LINK_PROJECT_TO_CLIENT_SUCCESS,
                clientToLinkTo.getName().fullName), ListType.PROJECT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short-circuit if same object
                || (other instanceof LinkProjectToClientCommand // handles null
                && targetIndex.equals(((LinkProjectToClientCommand) other).targetIndex)); // state check
    }

}
