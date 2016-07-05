package mainpackagenlp;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import edu.stanford.nlp.hcoref.CorefCoreAnnotations;
import edu.stanford.nlp.hcoref.data.CorefChain;
import edu.stanford.nlp.hcoref.data.CorefChain.CorefMention;
import edu.stanford.nlp.io.EncodingPrintWriter.out;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

public class Nlp {
	PrintWriter out;
	PrintWriter xmlOut = null;
	static StanfordCoreNLP pipeline;
	public Nlp()
	{
		out =new PrintWriter(System.out);
	    Properties props = new Properties();
	    props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref, sentiment");
	    pipeline = new StanfordCoreNLP(props);
	}
	
	
	
	public AnswerBuilder question(String question)
	{

		AnswerBuilder answer = new AnswerBuilder(question);
		    Annotation annotation;
		   
		      annotation = new Annotation(question);//("Kosgi Santosh sent an email to Stanford University. He didn't get a reply.");
		    pipeline.annotate(annotation);
		    List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		    if (sentences != null && ! sentences.isEmpty()) {
		      CoreMap sentence = sentences.get(0);
		      for (CoreMap token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
		    	
		    	  if(token.toShorterString().contains("NamedEntityTag=LOCATION"))
		    	  {
		    		 answer.addlocation(getString(token.toShorterString()));
		    	  }
		    	  if(token.toShorterString().contains("NamedEntityTag=PERSON"))
		    	  {
		    		 answer.addPerson(getString(token.toShorterString()));
		    	  }
		      }
		      Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
		      SemanticGraph graph = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
		      Map<Integer, CorefChain> corefChains =
		          annotation.get(CorefCoreAnnotations.CorefChainAnnotation.class);
		      for (Map.Entry<Integer,CorefChain> entry: corefChains.entrySet()) {
		        for (CorefChain.CorefMention m : entry.getValue().getMentionsInTextualOrder()) {
		        
		          List<CoreLabel> tokens = sentences.get(m.sentNum - 1).get(CoreAnnotations.TokensAnnotation.class);
		     
		          answer.addChain(lookForChain(m.toString()));
		         
		        }
		      }
		    
		      answer.setRoot(lookForMainDependency(sentence.get(SemanticGraphCoreAnnotations.BasicDependenciesAnnotation.class ).toString(SemanticGraph.OutputFormat.LIST)));

		      return answer;
		      
		    }
			return answer;
	}
	private String getString(String temp) {
		// TODO A=to-generated method stub
		int startoffset = -1;
		int endoffset = -1;
		for(int i=0 ; i < temp.length() ; i++ )
		{
		 	if (temp.charAt(i) == '=')
		 	{
		 		startoffset = i;
		 	}
		 	if (temp.charAt(i) == ' ')
		 	{
		 		endoffset = i;
		 	}
		 	if(startoffset != -1 && endoffset != -1)
		 	{
		 		return temp.substring((startoffset +1) , endoffset);
		 	}
		}
		return null;
	}







	private String lookForChain(String  m) {
		int startoffset=-1;
		int endoffset=-1;
		for(int i = 0 ; i < m.length() ; i ++)
		{
			if(m.charAt(i)== '"')
			{
				if(startoffset == -1)
				{
					startoffset = i;	
				}else
				{
					endoffset = i ;
				}
			}
			if(startoffset != -1 && endoffset != -1)
			{
				return m.substring((startoffset+1) , endoffset);
			}
		}
		return m; 
	}



	public String lookForMainDependency(String lookfordependency)
	{
		  String temporary="";
	      int startoffset= -1;
	      int endoffset= -1;
	      for(int i = 0 ;  i < lookfordependency.length() ; i++)
	      {
	    	  
	    	  temporary+= lookfordependency.charAt(i);
	    	  if(lookfordependency.charAt(i) == ',')
	    	  {	
	    		  if (startoffset == -1)
	    		  {
	    			  startoffset = i+2;
	    		  }
	    	  }
	    	  if(lookfordependency.charAt(i) == ')')
	    	  {	
	    		  if (endoffset == -1)
	    		  {
	    			  endoffset = i;
	    		  }
	    	  }
	    	  if(startoffset != -1 && endoffset != -1 )
	    	  {	
	    		  String temp = lookfordependency.substring(startoffset, endoffset);
	    		  for(int j = 0 ; j < temp.length() ; j++)// wycinam po -
	    		  {
	    			  if(temp.charAt(j) == '-')
	    			  {
	    				  return temp.substring(0, j);
	    			  }
	    		  }
	    		  return lookfordependency.substring(startoffset, endoffset);
	    	  }
	      }
	      return "OFF "+String.valueOf(startoffset)+ "ENDOFF "+String.valueOf(endoffset)+temporary;
	}
}
