package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.Tesseract;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Tesseract tesseract;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaLoki.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaTesseract.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setTesseract(Tesseract tess) {
        tesseract = tess;
        String welcomeMsg = tesseract.getWelcomeMsg();
        dialogContainer.getChildren().add(DialogBox.getTesseractDialog(welcomeMsg, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tesseract.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTesseractDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
