package prison.game;

public interface ChatProvider {

    void sendMessage(String txt);

    // TODO Implement a JSON message system of some sort
    void sendJsonMessage();

}
