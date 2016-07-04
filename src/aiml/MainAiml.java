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
	PrintWriter stdin = new PrintWriter(p.getOutputStream());
	InputStream is = p.getInputStream();
	BufferedReader br = new BufferedReader(new InputStreamReader(is));

	public MainAiml() {
		try {
			p = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		runAiml();
	}

	public void runAiml() {
		try {
			AimlTample temp = new AimlTample();
			// temp.addaiml("siema", "dupa");
		} catch (FileNotFoundException e) {
			System.err.println("nie ma poliku");
			e.printStackTrace();
		}
		stdin.println("cd c:\\ab");// \ /A /Q
		stdin.println(" java -cp lib/Ab.jar Main bot=test action=chat trace=false");
		// java -cp lib/Ab.jar Main bot=test action=chat trace=false
		stdin.flush();
	}

	public String checkAnswer(String question) throws IOException {
		stdin.println(question);
		stdin.flush();
		String answer = br.readLine();
		if (answer.contains("no answer for"))
			return null;
		else
			return answer;
	}

}
