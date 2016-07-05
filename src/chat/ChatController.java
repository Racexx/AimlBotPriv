package chat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import aiml.MainAiml;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
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

    public static Stage myStage;
    @FXML
    private Button sendButton;
    Inventory shop ;
	Nlp nlp ;
	MainAiml aiml;
    static Questionnaire quest;
    String [] questionList  = {"What do you want to buy ?" ,"How many * you wont to buy ?" , "What is you name and surrname ?" ,"Where do you live ?" ,"What is your street ?" , ""}; 
    public ChatController() {
		// TODO Auto-generated constructor stub
    	shop = new Inventory();
   	 	nlp = new Nlp();
   	 	
	}
    @FXML
    public void initialize(){
//    	aiml=new MainAiml();
//    	try {
//			System.out.println(aiml.checkAnswer("hello Alice"));
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
    	quest = new Questionnaire();
    	listStatic=listOfMessages;
    	System.out.println("Tworze initalize");
    	question(-1,shop, nlp, textToSendArea.getText());
    	sendButton.setOnAction(e->{
    		listOfMessages.getItems().add(textToSendArea.getText());
    		System.out.println("WSZEDLEM");
    		
    		switch (quest.statusQuestion()) {
			case 1:
				question(quest.statusQuestion(),shop, nlp, textToSendArea.getText());
				break;
			case 2:
				question(quest.statusQuestion(),shop , nlp, textToSendArea.getText());
				break;
			case 3:
				question(quest.statusQuestion(),shop, nlp, textToSendArea.getText());
				break;
			case 4:
				question(quest.statusQuestion(),shop, nlp, textToSendArea.getText());
				break;
			case 5:
				question(quest.statusQuestion(),shop, nlp, textToSendArea.getText());
				break;
			case 0:
				//listStatic.getItems().add("Podsumowanie:\n"+quest.toString());
				quest.statusQuestion();
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
    	ChatController.myStage.setOnCloseRequest(e->{
    		aiml.destroy();
    	});
    }
	private void question(int i, Inventory shop, Nlp nlp, String scan) {
		if(i == -1){listOfMessages.getItems().add(shop.toString()); listOfMessages.getItems().add(questionList[0]);}else{
		//System.out.println("-----> "+i);
		String question = scan;
		if(question == null || question.equals("") || question.equals("null") || question.equals("\n"))
		{
			// tekst jest pusty
		}
		else{
			AnswerBuilder answer = nlp.question(question);
			//listOfMessages.getItems().add("------------------------\n"+answer.toString());
			System.out.println("------------------------\n"+answer.toString());
			questionnaireupdate(answer);
			AddtoQuestionProducts();
			updateList(quest.statusQuestion());
			}
		}
	}
		
	private void updateList(int i) {
	//	System.out.println("UPDATE-----> "+i);
		if(i == 0)
		{ //wyczysc itp
			try {
				shop.updatePices(quest.getProduct(), quest.getPiecs());
				listOfMessages.getItems().add(quest.toString());
				quest.saveQuest();
				questionList[1] = questionList[1].replace(quest.getProduct(),"*");
				quest = new Questionnaire();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//listOfMessages.getItems().add(quest.toString());
			
			initialize();
		}else{
		listOfMessages.getItems().add(questionList[i-1]);
		}
	}
	private void AddtoQuestionProducts() {
		if(questionList[1].contains("*") && quest.getProduct() != null)
		{
			questionList[1] = questionList[1].replace("*", quest.getProduct());
		}
		
	}
	private  void questionnaireupdate(AnswerBuilder answer ) {
	
		if (answer.getLocation() != null && quest.getLocation() == null)
		{	
			quest.addLocation(answer.getLocation());

		}
		if (answer.getPerson() != null && quest.getPerson() == null)
		{
			
			quest.addPersonality(answer.getPerson());

		}
		if (answer.getProduct() != null && quest.getProduct() == null)
		{
			
			quest.addProduct(answer.getProduct());
	
		}
		if (answer.getPiecs() != -1 && quest.getPiecs() == -1)
		{	
			
			quest.addPiecs(answer.getPiecs());
		
		}
		if(answer.getStreet() != null)
		{	
			quest.addStreet(answer.getStreet());
		}
		
		System.out.println(quest.toString());

	//	listStatic.getItems().add(quest.toString()); //<- to moze byc w konsoli wypisywane tak naprawde
		
	}

}
