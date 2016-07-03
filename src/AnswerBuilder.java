import java.util.ArrayList;

public class AnswerBuilder {
	String root;
	String qustion;
	ArrayList <String> chain = new ArrayList<String>();
	ArrayList <String> location = new ArrayList<String>();
	ArrayList <String> person = new ArrayList<String>();
	
	
	
	AnswerBuilder(String question)
	{
		this.qustion = question;
		build();
	}
	private void build() {
		
		
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
	
	public String toString()
	{
		
		String temp ="Q:" + qustion + "/n Root:" + root+"/n";
		for(String x : chain)
		{
			temp +="chainy "+x.indexOf(x)+" :" +x+"/n";	
		}
		for(String x : location)
		{
			temp +="chainy "+x.indexOf(x)+" :" +x+"/n";	
		} 
		for(String x : person)
		{
			temp +="chainy "+x.indexOf(x)+" :" +x+"/n";	
		}
		return temp;
		
	}
	
}
