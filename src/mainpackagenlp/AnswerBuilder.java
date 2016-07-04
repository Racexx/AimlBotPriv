package mainpackagenlp;
import java.util.ArrayList;

import edu.stanford.nlp.util.StringUtils;
import shops.Inventory;
import shops.Product;

public class AnswerBuilder {
	String root;
	String qustion;
	int piecs= -1;
	ArrayList <String> chain = new ArrayList<String>();
	ArrayList <String> location = new ArrayList<String>();
	ArrayList <String> person = new ArrayList<String>();
	
	
	
	AnswerBuilder(String question)
	{
		this.qustion = question;
	}
	
	
	public void setRoot(String root)
	{
		this.root=root;
	}
	public void addChain(String chain)
	{
		this.chain.add(chain);
	}
	public void addlocation(String chain)
	{
		this.location.add(chain);
	}
	public void addPerson(String chain)
	{
		this.person.add(chain);
	}
	
	public String getPerson()
	{
		if(person.isEmpty())
			return null;
		String temp="";
		for(String x: person)
		{
			temp += person; 
		} 
		if(person !=null)
		return temp;
		return null;
	}
	public String getLocation()
	{
		if(location.isEmpty())
			return null;
		String temp="";
		for(String x: location)
		{
			temp += location; 
		}
		if(location !=null)
		return temp;
		return null;
	}
	public int getPiecs()
	{
		try{
		if(StringUtils.isNumeric(root) )
			return Integer.valueOf(root);
		for(String x : chain)
		{
			if(StringUtils.isNumeric(x))
				return Integer.valueOf(x);
		}}catch(Exception e)
		{// jak bedzie pusto
		}
		
		return -1;
	}
	public String getProduct()
	{	
		
		for(Product x :Inventory.productsList)
		{
			if(root.contains(x.getName()))
				return x.getName();
			for(String y : chain)
			{
				if(y.contains(x.getName()))
				{
					return x.getName();
				}
			}
		}
		return null;
	}
	
	public String toString()
	{
		
		String temp ="Q:" + qustion + "\n Root:" + root+"\n";
		if(!chain.isEmpty())
		for(String x : chain)
		{
			temp +="chain "+x.indexOf(x)+" :" +x+"\n";	
		}
		if(!location.isEmpty())
		for(String x : location)
		{
			temp +="location: "+x.indexOf(x)+" :" +x+"\n";	
		} 
		if(!person.isEmpty())
		for(String x : person)
		{
			temp +="person: "+x.indexOf(x)+" :" +x+"\n";	
		}
		return temp;
		
	}
	
}
