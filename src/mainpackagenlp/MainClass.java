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
				questionOne(shop,nlp,scan);
				// tu powinno bys sprawdzenie czy chain zawiera produkt jezeli nie to wypisanie ze nie zostal wybrany produkt
			}
			if(quest.statusQuestion() == 2)
			questionTwo(shop,nlp,scan);
			if(quest.statusQuestion() == 3)
			questionThree(shop,nlp,scan);
			if(quest.statusQuestion() == 4)
			questionFour(shop,nlp,scan);
			if(quest.statusQuestion() == 5)
			questionFive(shop,nlp,scan);
			if(quest.statusQuestion() == 0)
			{
				System.out.println("Complieted");
			}
		}
	//	maciek.question(scan.nextLine());
	}

	private static void questionFive(Inventory shop, Nlp nlp, Scanner scan) {
		System.out.println("What is your street ?");// nie wiem czy pytania nie umieœciæ w aimlu
		AnswerBuilder answer = nlp.question(scan.nextLine());
		System.out.println("------------------------\n"+answer.toString());
		questionnaireupdate(answer);
		
	}

	private static void questionFour(Inventory shop, Nlp nlp, Scanner scan) {
		System.out.println("Where do you live ?");
		AnswerBuilder answer = nlp.question(scan.nextLine());
		System.out.println("------------------------\n"+answer.toString());
		questionnaireupdate(answer);
		
	}

	private static void questionThree(Inventory shop, Nlp nlp, Scanner scan) {
		System.out.println("What is you name and surrname ?");
		AnswerBuilder answer = nlp.question(scan.nextLine());
		System.out.println("------------------------\n"+answer.toString());
		questionnaireupdate(answer);
		
	}

	private static void questionTwo(Inventory shop, Nlp nlp, Scanner scan) {
		System.out.println("How many "+quest.getProduct()+" you wont to buy ?");// chyba trzeba zapisac do aimla xd
		AnswerBuilder answer = nlp.question(scan.nextLine());
		System.out.println("------------------------\n"+answer.toString());
		questionnaireupdate(answer);
	}

	private static void questionOne(Inventory shop, Nlp nlp, Scanner scan) 
	{
		
		System.out.println(shop.toString());
		System.out.println("What do you want to buy ?");
		AnswerBuilder answer = nlp.question(scan.nextLine());
		System.out.println("------------------------\n"+answer.toString());
		questionnaireupdate(answer);
	}

	private static void questionnaireupdate(AnswerBuilder answer) {
		if(answer.getLocation() != null)
		quest.addLocation(answer.getLocation());
		if(answer.getPerson() != null)
		quest.addPersonality(answer.getPerson());
		if(answer.getProduct() != null)
		quest.addProduct(answer.getProduct());
		if(answer.getPiecs() != -1)
		quest.addPiecs(answer.getPiecs());
		System.out.println(quest.toString());
		
	}
	
}
