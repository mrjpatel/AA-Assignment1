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
		
		randStringArr(rand, numOfStr);
		
		
		//Search
		
		ArrayList<String> randSc = new ArrayList<String>();
		
		if(args[3] != null)	{
			for(String i : rand) {
				randomScenGen(randSc, i, args[2]);
			}
			double numOfSea = Double.parseDouble(args[3]) /100;
			int indexSea = (int)Math.floor(numOfSea * numOfStr);
			Random rnd = new Random();
			for(int i = 0; i < indexSea; i++)	{
				String x = rand.get(rnd.nextInt(rand.size()));
				randomSeaGen(randSc, x);
			}
		}
		
		Collections.shuffle(randSc);
		
		//end
		
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
	
	//Hack functions for search...
	
	/*public static void randomScenGen(ArrayList<String> list, ArrayList<String> dest, String scen)	{
        StringBuilder randstr = new StringBuilder();
        for(String i : list)	{
        	randstr.append(scen + " ");
        	randstr.append(i);
        	String str = randstr.toString();
        	dest.add(str);
		}
  	}*/
	
	public static void randomScenGen(ArrayList<String> dest, String key, String scen)	{
		StringBuilder randstr = new StringBuilder();
		randstr.append(scen + " ");
    	randstr.append(key);
    	String str = randstr.toString();
    	dest.add(str);
	}
	
	public static void randomSeaGen(ArrayList<String> dest, String key)	{
		StringBuilder randstr = new StringBuilder();
		randstr.append("S ");
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
