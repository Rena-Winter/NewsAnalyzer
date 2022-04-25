package newsanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import newsanalyzer.ctrl.Controller;
import newsapi.NewsApi;
import newsapi.NewsApiBuilder;
import newsapi.enums.Category;
import newsapi.enums.Country;
import newsapi.enums.Endpoint;

import java.util.Scanner;

public class UserInterface 
{
	public static final String APIKEY = "214ade2bcab9482da030e57af2fdb48a";

	private Controller ctrl = new Controller();


	public void getDataFromCtrl1(){
		System.out.println("Football");

		NewsApi Ctrl1 = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("Football")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.at)
				.setSourceCategory(Category.sports)
				.createNewsApi();

		ctrl.process(Ctrl1);
	}

	public void getDataFromCtrl2(){
		System.out.println("Corona");

		NewsApi Ctrl2 = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("corona")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.at)
				.setSourceCategory(Category.health)
				.createNewsApi();

		ctrl.process(Ctrl2);
	}

	public void getDataFromCtrl3(){
		System.out.println("Ukraine");

		NewsApi Ctrl3 = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("Marvel")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.at)
				.setSourceCategory(Category.entertainment)
				.createNewsApi();


		ctrl.process(Ctrl3);

	}
	
	public void getDataForCustomInput() {
		System.out.println("Choice");

		Scanner choice1 = new Scanner(System.in);

		System.out.println("Enter Query: ");
		String choice = choice1.next();

		NewsApi CtrlCustom = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ(choice)
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.at)
				.setSourceCategory(Category.entertainment)
				.createNewsApi();

		ctrl.process(CtrlCustom);
		
	}


	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interfacx");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "Football", this::getDataFromCtrl1);
		menu.insert("b", "Corona", this::getDataFromCtrl2);
		menu.insert("c", "Marvel", this::getDataFromCtrl3);
		menu.insert("d", "Choice User Imput:",this::getDataForCustomInput);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		System.out.println("Program finished");
	}


    protected String readLine() {
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
        } catch (IOException ignored) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 	{
		Double number = null;
        while (number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                number = null;
				System.out.println("Please enter a valid number:");
				continue;
			}
            if (number < lowerlimit) {
				System.out.println("Please enter a higher number:");
                number = null;
            } else if (number > upperlimit) {
				System.out.println("Please enter a lower number:");
                number = null;
			}
		}
		return number;
	}
}
