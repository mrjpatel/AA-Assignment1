import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class DataGenerator {
		
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		if (args.length != 4) {
			inputs();
		}
		
		String fileNameAdd;
		System.out.println("Enter filename:");
		fileNameAdd = sc.nextLine();
		
		
		ArrayList<String> rand = new ArrayList<String>();
		
		int numOfStr = Integer.parseInt(args[0]);
		double numOfScen = Double.parseDouble(args[1]) /100;
		int indexes = (int)Math.floor(numOfScen * numOfStr);
		
		//Produce random string into array
		randStringArr(rand, numOfStr);
				
		ArrayList<String> randSc = new ArrayList<String>();
		
		//If there is a 4th argument, add the scenario and search for a ratio
		
		if(args[3] != null)	{
			for(String i : rand) {
				randomScenGen(randSc, i, args[2]);
			}
			double numOfSea = Double.parseDouble(args[3]) /100;
			int indexSea = (int)Math.floor(numOfSea * numOfStr);
			Random rnd = new Random();
			for(int i = 0; i < indexSea; i++)	{
				String x = rand.get(rnd.nextInt(rand.size()));
				randomScenGen(randSc, x, "S");
			}
		}
		
		//randomize scenarios
		Collections.shuffle(randSc);
		
		//print them all to a txt file
		try {
			PrintWriter writer = new PrintWriter(fileNameAdd);
			printAdd(rand, writer);
			Collections.shuffle(rand);
			if(args[2].equalsIgnoreCase("a"))	{
				writer.close();
			} else	{
				if(args[3] != null)	{
					for(String i : randSc)	{
				    	writer.println(i);
					}
				}
				else	{
					printScenario(rand, writer, args[2], indexes);
				}
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
	public static void inputs()	{
		System.out.println("Arguments: <num of strings to gen> <percentage of removals> <scenario> <percentage of searches>");
		System.out.println("i.e. 100 75 S 200");
		System.out.println("Possible scenarios:A, S, RO, RA");
	}
	
	//Random Generators
	
	public static String randomStringGen()	{
		//Code adapted from https://stackoverflow.com/questions/20536566/creating-a-random-string-with-a-z-and-0-9-in-java
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder randstr = new StringBuilder();
        Random rnd = new Random();
        while (randstr.length() < 18) {
            int index = (int) (rnd.nextFloat() * chars.length());
            randstr.append(chars.charAt(index));
        }
        String str = randstr.toString();
        return str;
	}
	
	//Generate scenarios 
	
	public static void randomScenGen(ArrayList<String> dest, String key, String scen)	{
		StringBuilder randstr = new StringBuilder();
		randstr.append(scen + " ");
    	randstr.append(key);
    	String str = randstr.toString();
    	dest.add(str);
	}
	
	public static void randStringArr(ArrayList<String> list, int numOfStr)	{
		String randString;
		for(int i = 0; i < numOfStr; i++)	{
			randString = randomStringGen();
			list.add(randString);
		}
	}
	
	//Print Operations
	
	public static void printAdd(ArrayList<String> list, PrintWriter writer)	{
		for(String i : list)	{
	    	writer.print("A ");
	    	writer.println(i);
		}
	}
	
	public static void printScenario(ArrayList<String> list, PrintWriter writer, String scenario, int index)	{
		//List must be randomised first
		for(int i = 0; i < index; i++)	{
			writer.print(scenario + " ");
			writer.println(list.get(i));
		}
	}
}
