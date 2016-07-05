package aiml;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MainAiml {
	String[] command = { "cmd", };
	Process p;
	PrintWriter stdin ;
	InputStream is ;
	BufferedReader br ;
	AimlTample temp = null;

	public MainAiml() {
		try {
			p = Runtime.getRuntime().exec(command);
			stdin = new PrintWriter(p.getOutputStream());
			is = p.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
		} catch (IOException e) {
			// TODO Auto-generated catch block
		System.out.println(e.getMessage());	
		}
		runAiml();
	}

	public void runAiml() {
		try {
			temp = new AimlTample();
			// temp.addaiml("siema", "dupa");
		} catch (FileNotFoundException e) {
			System.err.println("nie ma poliku");
			e.printStackTrace();
		}
		stdin.println("cd c:\\ab");// \ /A /Q
		stdin.flush();
		stdin.println(" java -cp lib/Ab.jar Main bot=test action=chat trace=false");
		// java -cp lib/Ab.jar Main bot=test action=chat trace=false
		stdin.flush();
		try {
			while (br.ready()) {
				br.read();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/*
 * metody nie testowane niestety wiec dopiero wyjdzie w praniu czy dziala
 */
	public String checkAnswer(String question) throws IOException {
		stdin.println(question);
		stdin.flush();
		StringBuilder sBilder= new StringBuilder();
		try {
			while (br.ready()) {
				sBilder.append(br.read());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String answer = sBilder.toString();
		if (answer.contains("no answer for"))
			return null;
		else
			return answer;
	}

	public boolean addToAiml(String question, String answer) {
		try {
			temp.addaiml(question, answer);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public void destroy() {
		
		stdin.close();
		p.destroyForcibly();
		// TODO Auto-generated method stub
		
	}

}
