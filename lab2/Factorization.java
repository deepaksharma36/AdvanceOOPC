/**
 * this program takes integer number n from the input and finds the prime
 * factors of the n.
 * 
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 * 
 */

public class Factorization {
	/**
	 * This method takes input from user and calculates its prime factors
	 * 
	 * @param args
	 *            String array stores input from user
	 */

	public static void main(String[] args) {
		int number = Integer.parseInt(args[0]);
		// converts string type input into Integer
		// using parseInt method of the Integer class.
		int dummy = 3;
		String output = "";
		// output variable stores the output of the program.
		System.out.print(number + "=");
		
		while (number % 2 == 0) {
			output = output + "2*";
			number = number / 2;
		}
		// Calculates all the prime factors for input Number
		for (dummy = 3; dummy <= Math.sqrt(number) + 1; dummy = dummy + 2) {
			while (number % dummy == 0) {
				output = output + dummy + "*";
				number = number / dummy;
			}

		}
		// checks whether provided number itself is a prime number
		// or multiple of a prime number which is grater than the square root
		//of the number
		if (number > 1) {
			output = output + number + "*";
		}

		// Printing the output
		System.out.println(output.substring(0, output.length() - 1));

	}
}
