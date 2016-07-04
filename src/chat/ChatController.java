package chat;

import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import mainpackagenlp.AnswerBuilder;
import mainpackagenlp.Nlp;
import shops.Inventory;
import shops.Questionnaire;

public class ChatController {

    @FXML
    private TextArea textToSendArea;

    @FXML
    private ListView<String> listOfMessages;
    
    private static ListView<String> listStatic;

    @FXML
    private Button sendButton;
    Inventory shop ;
	Nlp nlp ;
    static Questionnaire quest;

    public ChatController() {
		// TODO Auto-generated constructor stub
    	shop = new Inventory();
   	 	nlp = new Nlp();
   	 	quest = new Questionnaire();
	}
    @FXML
    public void initialize(){
    	listStatic=listOfMessages;
    	System.out.println("Tworze initalize");
    	sendButton.setOnAction(e->{
    		System.out.println("WSZEDLEM");
    		
    		switch (quest.statusQuestion()) {
			case 1:
				question("What do you want to buy ?",shop, nlp, textToSendArea.getText());
				break;
			case 2:
				question("How many " + quest.getProduct() + " you wont to buy ?",shop , nlp, textToSendArea.getText());
				break;
			case 3:
				question("What is you name and surrname ?",shop, nlp, textToSendArea.getText());
				break;
			case 4:
				question("Where do you live ?",shop, nlp, textToSendArea.getText());
				break;
			case 5:
				question("What is your street ?",shop, nlp, textToSendArea.getText());
				break;
			case 0:
				System.out.println("Complieted");
			}
    		textToSendArea.clear();
    		listOfMessages.scrollTo(listOfMessages.getItems().size());
    	});
    	textToSendArea.setOnKeyPressed(e -> {
			if (e.getCode().toString().equals("ENTER")) {
				sendButton.fire();
			}
		});
    	
    	 
    }
	private void question(String string, Inventory shop, Nlp nlp, String scan) {
		if(string.contains("What do you want to buy ?")){listOfMessages.getItems().add(shop.toString());}
		listOfMessages.getItems().add(string);
		String question = scan;
		if(question == null || question.equals("") || question.equals("null"))
		{
			// tekst jest pusty
		}else{
		AnswerBuilder answer = nlp.question(question);
		listOfMessages.getItems().add("------------------------\n"+answer.toString());
		questionnaireupdate(answer);
		}
	}
		
	private  void questionnaireupdate(AnswerBuilder answer) {
		if (answer.getLocation() != null)
			quest.addLocation(answer.getLocation());
		if (answer.getPerson() != null)
			quest.addPersonality(answer.getPerson());
		if (answer.getProduct() != null)
			quest.addProduct(answer.getProduct());
		if (answer.getPiecs() != -1)
			quest.addPiecs(answer.getPiecs());
		listStatic.getItems().add(quest.toString());

	}

}
