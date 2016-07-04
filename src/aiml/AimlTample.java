package aiml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AimlTample {
	String aimlName;
	Scanner plikaiml;
	Scanner plikcsv;
	FileWriter pwaiml;
	FileWriter pwcsv;
	File aiml;

	public AimlTample() throws FileNotFoundException {
		aimlName = "test.aiml";
		aiml = new File("C:/ab/bots/test/aimlif/test.aiml");
		File csv = new File("C:/ab/bots/test/aimlif/test.aiml.csv");
		plikaiml = new Scanner(aiml);
		plikcsv = new Scanner(csv);
		try {
			pwaiml = new FileWriter(aiml, true);
			pwcsv = new FileWriter(csv, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void addaiml(String question, String answer) throws IOException // jezeli
	{
		String delimiter = System.getProperty("line.separator");
		int lastIndexOfQuestion = 0;
		String lastCsvLine = null;
		while (plikcsv.hasNextLine())
			lastCsvLine = plikcsv.nextLine();
		for (int i = 0; i < lastCsvLine.length(); i++) {
			if (lastCsvLine.charAt(i) == ',') {
				lastIndexOfQuestion = Integer.parseInt(lastCsvLine.substring(0, i));
				break;
			}
		}
		System.out.println(lastIndexOfQuestion);
		int temps = -1;
		String tempik = "";

		while (plikaiml.hasNextLine()) {
			String next = plikaiml.nextLine();
			tempik += (next + System.getProperty("line.separator"));
			if (next.equals("</category>")) {
				temps++;
			}
			if (temps == lastIndexOfQuestion) {
				tempik += "<category>" + delimiter + " <pattern> " + question + " </pattern>" + delimiter + "";
				tempik += "<template>" + delimiter + " " + answer + "" + delimiter + " </template>" + delimiter
						+ " </category>" + delimiter + "";
				break;
				// mam linie w ktorej chce dopisac
			}
		}
		plikaiml.close();
		pwaiml = new FileWriter(aiml);
		System.out.println(tempik);
		pwaiml.write(tempik);
		pwcsv.write("" + delimiter + "" + (lastIndexOfQuestion + 1) + "," + question.toUpperCase() + ",*,*," + answer
				+ "," + aimlName + "");
		pwaiml.close();
		pwcsv.close();
	}
}
