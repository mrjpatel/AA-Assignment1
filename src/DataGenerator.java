import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class DataGenerator {
	
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		if (args.length != 2) {
			inputs();
		}
		
		String fileNameAdd;
		String fileNameRem;
		System.out.println("Enter filename for additions:");
		fileNameAdd = sc.nextLine();
		System.out.println("Enter filename for removals/scenarios:");
		fileNameRem = sc.nextLine();
		
		int numOfStr = Integer.parseInt(args[0]);
		String randString;
		String[] randArr;
		randArr = new String[numOfStr];
		
		for(int i = 0; i < numOfStr; i++)	{
			randString = randomStringGen();
			randArr[i] = randString;
		}
		
		try{
		    PrintWriter writer = new PrintWriter(fileNameAdd);
		    for(int i = 0; i < numOfStr; i++)	{
		    	writer.print("A ");
		    	writer.println(randArr[i]);
			}
		    writer.close();
		} catch (IOException e) {
		   e.printStackTrace();
		}

	}
	
	public static void inputs()	{
		System.out.println("Arguments: <num of strings to gen> <percentage of removals> ");
		System.out.println("i.e. 100 75");
		System.exit(0);
	}
	
	public static String randomStringGen()	{
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * chars.length());
            salt.append(chars.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
	}

}
