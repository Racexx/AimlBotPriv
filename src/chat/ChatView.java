package chat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class ChatView {

    @FXML
    private TextArea textToSendArea;

    @FXML
    private ListView<?> listOfMessages;

    @FXML
    private Button sendButton;

}
