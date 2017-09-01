package by.epam.totalizator.command;

import by.epam.totalizator.content.RequestContent;
import by.epam.totalizator.receiver.Receiver;
import by.epam.totalizator.util.Router;

public abstract class AbstractCommand {
    protected Receiver receiver;

    /**
     * Default constructor.
     *
     * @param receiver
     */
    public AbstractCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Execute command.
     *
     * @param requestContent
     * @return
     */
    public abstract Router execute(RequestContent requestContent);

    /**
     * Get receiver.
     *
     * @return
     */
    public Receiver getReceiver() {
        return receiver;
    }

}
