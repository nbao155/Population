import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *	Population - Creates a database from a list of US cities and sorts the data using selection sort, merge sort, and insertion sort. 
 *  This database is then used to answer simple queries such as "What are the US cities with the highest population?".
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author		Nathan Bao	
 *	@since		1/9/2023
 */
public class Population {
	
	// List of cities
	private List<City> cities = new ArrayList<City>();
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	/**
	 * Reads the usPopData2017.txt file and inserts the data into the cities List
	 */
	public void readFile(){
		String statename = "p";
		String cityname = "p";
		String type = "p";
		String strpop = "";
		String waste = "";
		City ct;
		int population = -1;
		FileUtils file = new FileUtils();
		Scanner sc = new Scanner(System.in);
		sc = file.openToRead("usPopData2017.txt");
		sc.useDelimiter("[\t\n]");
		while(sc.hasNext()){
			if(statename.equals("p")){
				statename = sc.next();
			//	System.out.println(statename);
			}
			else if(cityname.equals("p")){
				cityname = sc.next();
			//	System.out.println(cityname);
			}
			else if(type.equals("p")){
				type = sc.next();
			//	System.out.println(type);
			}
			else if(population==-1){
				strpop = sc.next();
			//	System.out.println(strpop);
				population = Integer.parseInt(strpop);
			}
			else{
				ct = new City(cityname, statename, type, population);
				cities.add(ct);
				statename = "p";
				cityname = "p";
				type = "p";
				strpop = "";
				population = -1;
			}
		}
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
	/**	main */
	public static void main(String [] args){
		Population pp = new Population();
		pp.run();
	}
	
	/**
	 * Runs the program
	 */
	public void run(){
		boolean isRunning = true;
		Prompt pt = new Prompt();
		SortMethods sm = new SortMethods();
		long startTime = System.currentTimeMillis();
		long endTime = System.currentTimeMillis();
		String statenamed = "";
		String citynamed = "";
		ArrayList<City> citiesOfState = new ArrayList<City>();
		ArrayList<City> specificCities = new ArrayList<City>();
		printIntroduction();
		while(isRunning){
			readFile();
			printMenu();
			int selection = pt.getInt("Enter selection");
			if(selection==1){
				startTime = System.currentTimeMillis();
				sm.selectionSort(cities);
				endTime = System.currentTimeMillis();
				System.out.println("\nFifty least populous cities");
				for(int i = 0;i<50;i++){
					System.out.println(cities.get(i).toString());
				}
				System.out.println("\nElapsed Time "+(endTime-startTime)+" milliseconds\n");
				cities = new ArrayList<City>();
			}
			else if(selection==2){
				startTime = System.currentTimeMillis();
				sm.mergeSort(cities);
				endTime = System.currentTimeMillis();
				System.out.println("\nFifty most populous cities");
				for(int i = 0;i<50;i++){
					System.out.println(cities.get(i).toString());
				}
				System.out.println("\nElapsed Time "+(endTime-startTime)+" milliseconds\n");
				cities = new ArrayList<City>();
			}
			else if(selection==3){
				startTime = System.currentTimeMillis();
				sm.insertionSort(cities);
				endTime = System.currentTimeMillis();
				System.out.println("\nFifty cities sorted by name");
				for(int i = 0;i<50;i++){
					System.out.println(cities.get(i).toString());
				}
				System.out.println("\nElapsed Time "+(endTime-startTime)+" milliseconds\n");
				cities = new ArrayList<City>();
			}
			else if(selection==4){
				startTime = System.currentTimeMillis();
				sm.mergeSortName(cities);
				endTime = System.currentTimeMillis();
				System.out.println("\nFifty cities sorted by name descending");
				for(int i = 0;i<50;i++){
					System.out.println(cities.get(i).toString());
				}
				System.out.println("\nElapsed Time "+(endTime-startTime)+" milliseconds\n");
				cities = new ArrayList<City>();
			}
			else if(selection==5){
				boolean isValidState = false;
				System.out.println("");
				while(isValidState == false){
					statenamed = pt.getString("Enter state name (ie. Alabama)");
					for(int i = 0;i<cities.size();i++){
						if(statenamed.equals(cities.get(i).getState()))
							isValidState = true;
					}
					if(isValidState == false){
						System.out.println("ERROR: "+statenamed+" is not valid");
					}
				}	
				System.out.println("\nFifty most populous cities in "+statenamed); 
				for(int i = 0;i<cities.size();i++){
					if(statenamed.equals(cities.get(i).getState())){
						citiesOfState.add(cities.get(i));
					}
				}
				//startTime = System.currentTimeMillis();
				sm.mergeSort(citiesOfState);
				//endTime = System.currentTimeMillis();
				for(int i = 0;i<50;i++){
					System.out.println(citiesOfState.get(i).toString());
				}
				System.out.println("");
				//System.out.println("\nElapsed Time "+(endTime-startTime)+" milliseconds\n");
				citiesOfState = new ArrayList<City>();
				cities = new ArrayList<City>();
			}
			else if(selection==6){
				citynamed = pt.getString("\nEnter city name");
				for(int i = 0;i<cities.size();i++){
					if(citynamed.equals(cities.get(i).getName())){
						specificCities.add(cities.get(i));
					}
				}
			//	startTime = System.currentTimeMillis();
				sm.mergeSort(specificCities);
			//	endTime = System.currentTimeMillis();
				System.out.print("\nCity "+citynamed+" by population\n");
				for(int i = 0;i<specificCities.size();i++){
					System.out.println(specificCities.get(i).toString());
				}
				System.out.println("");
				//	System.out.println("\nElapsed Time "+(endTime-startTime)+" milliseconds\n");
				specificCities = new ArrayList<City>();
				cities = new ArrayList<City>();
			}
			else if(selection==9){
				isRunning = false;
			}
			else{
				
			}
		}
	}
}
