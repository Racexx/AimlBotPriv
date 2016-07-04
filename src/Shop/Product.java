package Shop;

public class Product {
	private String name;
	private int price;
	private int piecs;
	Product(String name, int piecs , int price)
	{
		this.name = name;
		this.price = price;
		this.piecs=piecs;
	}
	public String getName()
	{
		return name;
	}
	public String getPrice()
	{
		return String.valueOf(price);
	}
	public void removePiecs(int amount)
	{
		piecs = piecs - amount;
	}
	public String toString()
	{
		return name+" "+piecs+"pics "+price+"$";
				
	}
	
}
