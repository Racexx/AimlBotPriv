package shops;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
	public static ArrayList<Product> productsList = new ArrayList<Product>();

	private void loadFromFile() throws FileNotFoundException {
		File file = new File("inventory.txt");
		Scanner in = new Scanner(file);
		String zdanie = null;
		while (in.hasNextLine()) {
			zdanie = in.nextLine();
			String[] s = zdanie.split(";");
			addProducts(new Product(s[0], Integer.valueOf(s[1]), Integer.valueOf(s[2])));
		}

	}

	public Inventory() {
		try {
			loadFromFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			addProducts(new Product("headfons", 100, 20));
			addProducts(new Product("monitor", 50, 520));
			addProducts(new Product("mouse", 50, 40));
			addProducts(new Product("GPU", 40, 300));
			e.printStackTrace();
		}

	}

	public void addProducts(Product products) {
		productsList.add(products);
	}

	public ArrayList<Product> getProductsList() {
		return productsList;
	}

	public String toString() {
		String temp = "Products :\n";
		for (Product x : productsList) {
			temp += x.toString() + "\n";
		}
		return temp;
	}

	public void updatePices(String product, int piecs) {

		for (Product x : productsList) {
			if (x.getName().equals(product)) {
				x.removePiecs(piecs);
			}
		}

		// TODO Auto-generated method stub

	}

}
