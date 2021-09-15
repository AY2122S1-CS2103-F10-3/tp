package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class HideCommand extends Command {
    public static final String COMMAND_WORD = "hide";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Exports data file to folder "
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "all persons hidden";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.hideAllPersons();
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
