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
    String [] questionList  = {"What do you want to buy ?" ,"How many * you wont to buy ?" , "What is you name and surrname ?" ,"Where do you live ?" ,"What is your street ?" , "Dziêkujemy za zakupy w tesko XD"}; 
    public ChatController() {
		// TODO Auto-generated constructor stub
    	shop = new Inventory();
   	 	nlp = new Nlp();
   	 	
	}
    @FXML
    public void initialize(){
    	quest = new Questionnaire();
    	listStatic=listOfMessages;
    	System.out.println("Tworze initalize");
    	question(-1,shop, nlp, textToSendArea.getText());
    	sendButton.setOnAction(e->{
    		System.out.println("WSZEDLEM");
    		
    		switch (quest.statusQuestion()) {
			case 1:
				question(1,shop, nlp, textToSendArea.getText());
				break;
			case 2:
				question(2,shop , nlp, textToSendArea.getText());
				break;
			case 3:
				question(3,shop, nlp, textToSendArea.getText());
				break;
			case 4:
				question(4,shop, nlp, textToSendArea.getText());
				break;
			case 5:
				question(5,shop, nlp, textToSendArea.getText());
				break;
			case 0:
				initialize();
				//chyba dokleic przedmioty trza
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
	private void question(int i, Inventory shop, Nlp nlp, String scan) {
		if(i == -1){listOfMessages.getItems().add(shop.toString()); listOfMessages.getItems().add(questionList[0]);}
		
		String question = scan;
		if(question == null || question.equals("") || question.equals("null") || question.equals("\n"))
		{
			// tekst jest pusty
		}else{
		AnswerBuilder answer = nlp.question(question);
		//listOfMessages.getItems().add("------------------------\n"+answer.toString());
		System.out.println("------------------------\n"+answer.toString());
		if(i == -1){}
		else{
			if(questionnaireupdate(answer))
				{
					
					AddtoQuestionProducts();
					listOfMessages.getItems().add(questionList[i]);
				}else{
					listOfMessages.getItems().add(questionList[i-1]);
				}
			}
		}
	}
		
	private void AddtoQuestionProducts() {
		if(questionList[1].contains("*") && quest.getProduct() != null)
		{
			questionList[1] = questionList[1].replace("*", quest.getProduct());
		}
		
	}
	private  boolean questionnaireupdate(AnswerBuilder answer) {
		int p=0;
		if (answer.getLocation() != null && quest.getLocation() == null)
		{	
			quest.addLocation(answer.getLocation());
			p++;
		}
		if (answer.getPerson() != null && quest.getPerson() == null)
		{
			quest.addPersonality(answer.getPerson());
			p++;
		}
		if (answer.getProduct() != null && quest.getProduct() == null)
		{
			quest.addProduct(answer.getProduct());
			p++;
		}
		if (answer.getPiecs() != -1 && quest.getPiecs() == -1)
		{
			quest.addPiecs(answer.getPiecs());
			p++;
		}
		if(answer.getStreet() != null)
		{
			quest.addStreet(answer.getStreet());
			p++;
		}
		System.out.println(quest.toString());
		if(p>0)
			return true;
		return false;
	//	listStatic.getItems().add(quest.toString()); //<- to moze byc w konsoli wypisywane tak naprawde
		
	}

}
