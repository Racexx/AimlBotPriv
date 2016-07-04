package shops;
import java.util.ArrayList;

public class Inventory {
	public static ArrayList<Product> productsList = new ArrayList<Product>();
	
	
	public Inventory() {
		addProducts(new Product("headfons", 100 , 20));
		addProducts(new Product("monitor", 50 , 520));
		addProducts(new Product("mouse", 50 , 40));
		addProducts(new Product("GPU", 40 , 300));
	}
	public void addProducts(Product products)
	{
		productsList.add(products);
	}
	public ArrayList <Product> getProductsList()
	{
		return productsList;
	}
	public String toString()
	{	String temp="Products :\n";
		for(Product x : productsList )
		{
			temp+=x.toString()+"\n";
		}
		return temp;
	}
	public void updatePices(String product , int piecs) {
		
		for(Product x: productsList)
		{
			if(x.getName().equals(product))
			{
				x.removePiecs(piecs);
			}
		}
		
		
		// TODO Auto-generated method stub
		
	}
	
}
