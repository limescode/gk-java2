package pl.limescode.clientchat.model;

import pl.limescode.command.Command;

public interface ReadMessageListener {

    void processReceivedCommand(Command command);

}
