package mainpackagenlp;

import java.util.Scanner;
import shops.Inventory;
import shops.Product;
import shops.Questionnaire;

public class MainClass {
	static Questionnaire quest = new Questionnaire();
	
	
	public static void main(String [] args)
	{
		Inventory shop = new Inventory();
		Nlp nlp = new Nlp();
		Scanner scan = new Scanner(System.in);
		//Questionnaire quest = new Questionnaire();
		while(true)
		{	
			if(quest.statusQuestion() == 1)
			{
				question("What do you want to buy ?",shop,nlp,scan);
				// tu powinno bys sprawdzenie czy chain zawiera produkt jezeli nie to wypisanie ze nie zostal wybrany produkt
			}
			if(quest.statusQuestion() == 2)
				question("How many "+quest.getProduct()+" you wont to buy ?",shop,nlp,scan);
			if(quest.statusQuestion() == 3)
				question("What is you name and surrname ?",shop,nlp,scan);
			if(quest.statusQuestion() == 4)
				question("Where do you live ?",shop,nlp,scan);
			if(quest.statusQuestion() == 5)
				question("What is your street ? pls use St.",shop,nlp,scan);
			if(quest.statusQuestion() == 0)
			{
				System.out.println("Dziêkujemy za zakupy w tesko XD");
				shop.updatePices(quest.getProduct(),quest.getPiecs()); // powinno byc sprawdzanie sztuk nie ma tego
				quest = new Questionnaire();
			}
		}
	//	maciek.question(scan.nextLine());
	}

	private static void question(String string, Inventory shop, Nlp nlp, Scanner scan) {
		if(string.contains("What do you want to buy ?")){System.out.println(shop.toString());}
		System.out.println(string);
		String question = scan.nextLine();
		if(question == null || question.equals("") || question.equals("null"))
		{
			// tekst jest pusty
		}else{
		AnswerBuilder answer = nlp.question(question);
		System.out.println("------------------------\n"+answer.toString());
		questionnaireupdate(answer);
		}
	}

	

	private static void questionnaireupdate(AnswerBuilder answer) {
		if(answer.getLocation() != null)
		quest.addLocation(answer.getLocation());
		if(answer.getPerson() != null)
		quest.addPersonality(answer.getPerson());
		if(answer.getProduct() != null)
		quest.addProduct(answer.getProduct());
		if(answer.getPiecs() != -1 && quest.getPiecs() == -1)
		quest.addPiecs(answer.getPiecs());
		if(answer.getStreet() != null)
		quest.addStreet(answer.getStreet());
		System.out.println(quest.toString());
		
	}
	
}
