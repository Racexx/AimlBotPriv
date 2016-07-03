import java.util.Scanner;

public class MainClass {

	public static void main(String [] args)
	{
		Nlp maciek = new Nlp();
		Scanner scan = new Scanner(System.in);
		while(true)
		{
			maciek.question(scan.nextLine());
			
		}
	//	maciek.question(scan.nextLine());
	}
}
