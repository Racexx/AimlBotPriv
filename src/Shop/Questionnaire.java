package Shop;

public class Questionnaire {
	String product=null;
	int pices=-1;
	String personality=null;
	String location=null;
	String street=null;
	
	public Questionnaire()
	{
	}
	public void addStreet(String street)
	{
		this.street=street;
	}
	public void addPersonality(String personality)
	{
		this.personality = personality;
	}
	public void addLocation(String location)
	{
		this.location = location;
	}
	public void addProduct(String product)
	{
		
		this.product = product;
	}
	public void addPiecs(int pices)
	{
		this.pices = pices;
	}
	public String getProduct()
	{
		return product;
	}
	
	public int statusQuestion()
	{
		if(product == null)
		{
			return 1; // pytanie o jaki produkt
		}
		if(pices == -1)
		{
			return 2; // pytanie o ilosc produktow
		}
		if(personality == null)
		{
			return 3;//pytanie o imie nazwisko
		}
		if(location == null)
		{
			return 4;// pytanie o miasto
		}
		if(street == null)
		{
			return 5;// pytanie o ulice
		}
		return 0;
	}
	public String toString()
	{
		
		String temp = "QUEST\n";
		temp +="PERSON: "+ personality + "\n";
		temp+="LOCATION: "+ location +"\n";
		temp+="STREET: "+ street +"\n";
		temp+="PRODUCT: " + product +"\n";
		temp+="PIECS: " + pices +"\n";
		return temp;
	}
	
}