import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TradeManager {
	
	private static String center(String s, int width) {
	    if (s == null) s = "";
	    if (s.length() >= width) return s.substring(0, width);

	    int left = (width - s.length()) / 2;
	    int right = width - s.length() - left;

	    return " ".repeat(left) + s + " ".repeat(right);
	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		int tariffUser = 0, iterations = 0;
		String origin = "", productCateg = "", destination = "";
		ArrayList<Product> productList = new ArrayList<Product>();

		// Created two empty lists from TariffList Class
		TariffList list1 = new TariffList();
		TariffList list2 = new TariffList();

		try (BufferedReader br = new BufferedReader(new FileReader("src/Tariffs.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length == 4) {
					String destinationCountry = data[0].trim();
					String originCountry = data[1].trim();
					String productCategory = data[2].trim();
					double minimumTariff = Double.parseDouble(data[3].trim());
					Tariff tariff = new Tariff(destinationCountry, originCountry, productCategory, minimumTariff);

					// no duplicate
					if (!list1.contains(destinationCountry, originCountry, productCategory)) {
						list1.addToStart(tariff);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Input file not found: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}

		try (BufferedReader br = new BufferedReader(new FileReader("src/TradeData.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length == 4) {
					String productName = data[0].trim();
					String country = data[1].trim();
					String category = data[2].trim();
					double initialPrice = Double.parseDouble(data[3].trim());

					Product product = new Product(productName, country, category, initialPrice);
					productList.add(product);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Input file not found: " + e.getMessage());
			return; // Exit if file not found
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
			return; // Exit on IO error
		}

		// Process the products
		for (Product product : productList) {
			String country = product.getCountry();
			String category = product.getCategory();
			double initialPrice = product.getInitialPrice();
			double updatedPrice = initialPrice;

			switch (country) {
			case "China":
				updatedPrice = initialPrice * 1.25; // +25% for all products
				break;
			case "USA":
				if (category.equals("Electronics")) {
					updatedPrice = initialPrice * 1.10; // +10% for Electronics
					break;
				}
			case "Japan":
				if (category.equals("Automobiles")) {
					updatedPrice = initialPrice * 1.15; // +10% for Automobiles
					break;
				}
			case "India":
				if (category.equals("Agriculture")) {
					updatedPrice = initialPrice * 1.05; // +5% for Agriculture
					break;
				}
			case "South Korea":
				if (category.equals("Electronics")) {
					updatedPrice = initialPrice * 1.08; // 8% for Electronics
					break;
				}
			case "Saudi Arabia":
				if (category.equals("Energy")) {
					updatedPrice = initialPrice * 1.12; // 12% for Energy
					break;
				}
			case "Germany":
				if (category.equals("Manufacturing")) {
					updatedPrice = initialPrice * 1.06; // 6% for Manufacturing
					break;
				}
			case "Bangladesh":
				if (category.equals("Textile")) {
					updatedPrice = initialPrice * 1.04; // 4% for Textile
					break;
				}
			case "Brazil":
				if (category.equals("Agriculture")) {
					updatedPrice = initialPrice * 1.09; // 9% for Agriculture
					break;
				}
			}

			product.setInitialPrice(updatedPrice); // Update the product price
		}
		for (int i = 0; i < productList.size(); i++) {
			for (int j = 0; j < productList.size() - i - 1; j++) {
				if (productList.get(j).compareTo(productList.get(j + 1)) > 0) {
					Product t = productList.get(j);
					productList.set(j, productList.get(j + 1));
					productList.set(j + 1, t);

				}
			}
		}
		// Write to file
		try (FileWriter myWriter = new FileWriter("UpdatedTradeData.txt")) {

		    final String indent = "                "; // 16 spaces to visually center the whole table

		    int wName = 18, wCountry = 14, wCategory = 18, wPrice = 16;

		    myWriter.write(
		             center("PRODUCT NAME", wName) + "||"
		            + center("COUNTRY", wCountry) + "||"
		            + center("CATEGORY", wCategory) + "||"
		            + center("UPDATED PRICE", wPrice) + "||\n");

		    myWriter.write("-".repeat(wName + wCountry + wCategory + wPrice + 8) + "\n");

		    for (Product product : productList) {
		        myWriter.write(
		                 center(product.getProductName(), wName) + "||"
		                + center(product.getCountry(), wCountry) + "||"
		                + center(product.getCategory(), wCategory) + "||"
		                + center(String.format("%.2f", product.getInitialPrice()), wPrice) + "||\n");
		    }

		    System.out.println("Successfully wrote to UpdatedTradeData.txt");

		} catch (IOException e) {
		    System.out.println("Error writing to file: " + e.getMessage());
		    e.printStackTrace();
		}

		
		ArrayList<TradeRequest> tradeRequests = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("src/TradeRequests.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length == 6) {
					String requestID = data[0].trim();
					String originCountry = data[1].trim();
					String destinationCountry = data[2].trim();
					String productCategory = data[3].trim();
					double tradeValue = Double.parseDouble(data[4].trim());
					double proposedTariff = Double.parseDouble(data[5].trim());

					TradeRequest request = new TradeRequest(requestID, originCountry, destinationCountry,
							productCategory, tradeValue, proposedTariff);
					tradeRequests.add(request);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Input file not found: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}

		System.out.println("\nTRADE REQUEST RESULTS:");
		System.out.println("------------------------------------------------");

		for (TradeRequest request : tradeRequests) {

			TariffList.TariffNode node = list1.find(request.getDestinationCountry(), request.getOriginCountry(),
					request.getProductCategory());

			double minimumTariff = node.getTariff().getMinimumTariff();
			String result = list1.evaluateTrade(request.getProposedTariff(), minimumTariff);

			// Print in console
			System.out.println("Trade Request ID " + request.getRequestID() + ": " + result);

			
		}
		System.out.println("How many Tariffs are you looking for?");
		tariffUser = input.nextInt();
		input.nextLine();
		for (int i = 0; i < tariffUser; i++) {
			System.out.println("Please Enter Destination Country");
			destination = input.nextLine();
			System.out.println("Please Enter Origin Country");
			origin = input.nextLine();
			System.out.println("Please Enter Product Category ");
			productCateg = input.nextLine();
		}
		TariffList.TariffNode current = list1.getHead();
		Tariff foundTariff = null;
		Tariff tempTariff = new Tariff(destination, origin, productCateg, 0.0);

		foundTariff = list1.find(destination, origin, productCateg).getTariff();
		iterations = list1.getLastFindIterations();
		
		if (foundTariff != null) {
			System.out.println("Tariff found! Iterations = " + iterations);
		} else {
			System.out.println("Tariff not found.");
		}

		
		System.out.println("\n----- Testing Part -----");

		// Test Product
		Product p1 = new Product();
		Product p2 = new Product("Laptop", "USA", "Electronics", 1200.00);
		System.out.println("Default Product: " + p1);
		System.out.println("Parameterized Product: " + p2);
		System.out.println("Default and Parameterized are the same?: " + p1.equals(p2));

		// Test Tariff
		Tariff t1 = new Tariff("USA", "China", "Electronics", 25.0);
		// Copy constructor
		Tariff t2 = new Tariff(t1);
		Tariff clone = t1.clone();
		System.out.println("\nTariff: " + t1);
		System.out.println("Copied Tariff: " + t2);
		System.out.println("Cloned Tariff: " + clone);
		System.out.println("Tariff are the same?: " + t1.equals(t2));

		// Test TariffList
		TariffList testList = new TariffList();
		testList.addToStart(t1);
		Tariff t3 = new Tariff("Germany", "India", "Manufacturing", 30.0);
		testList.addToStart(t3);
		System.out.println(testList.contains("USA", "China", "Electronics"));
		System.out.println(testList.contains("Brazil", "USA", "Agriculture"));
		TariffList.TariffNode foundNode = testList.find("USA", "China", "Electronics");
		if (foundNode != null) {
			System.out.println("Found Tariff in testList: " + foundNode.getTariff());
		} else {
			System.out.println("Tariff not found in testList.");
		}

		testList.setCurrentTradeValue(100000);
		TradeRequest req1 = new TradeRequest("REQ001", "China", "USA", "Electronics", 100000, 20);

		TradeRequest req2 = new TradeRequest("REQ002", "India", "Germany", "Manufacturing", 50000, 30);

		TradeRequest req3 = new TradeRequest("REQ003", "Brazil", "USA", "Agriculture", 50000, 5);
		// Add tariff for req3:
		Tariff t4 = new Tariff("USA", "Brazil", "Agriculture", 10.0);
		testList.addToStart(t4);

		String outcome1 = testList.evaluateTrade(req1.getProposedTariff(), t1.getMinimumTariff());
		String outcome2 = testList.evaluateTrade(req2.getProposedTariff(), t3.getMinimumTariff());
		String outcome3 = testList.evaluateTrade(req3.getProposedTariff(), t4.getMinimumTariff());

		System.out.println("Outcome for REQ001: " + outcome1);
		System.out.println("Outcome for REQ002: " + outcome2);
		System.out.println("Outcome for REQ003: " + outcome3);
	}
}