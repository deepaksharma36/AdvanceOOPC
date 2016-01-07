

public class X {
	public static void main(String args[]) {

		int n = 0;

		here:

		while (true) {
			/**
			 * while true,the loop will run forever if it is not broken
			 * explicitly, program counter enters this while loop with n
			 * initiated to zero.
			 */
			while (((n != 3) || (n != 5)) && (n < 4)) {
				/**
				 * this loop accepts all the values of n that are not equal to 3
				 * and 5(which is not possible at any value on n) at the same
				 * time and less than 4.
				 * 
				 * At the first Iteration n=0: While loop returns true for n=0.
				 * Step1: at if condition, pre increment increases the value of
				 * n by 1 before performing '==' operation as compiler executes
				 * expression from left to right. Here comparison fails but
				 * value of n becomes 1. Step2: Program first checks else-if
				 * condition, before post incrementing the value. Value of n at
				 * index 1 is compared to 1, test will be passed and value of n
				 * will be increased to 2. Print statement will print
				 * "b/  n is 2". Step3: Program will come out of if-else block
				 * as last else if statement was true then executes the print
				 * statement "executing break here"
				 * 
				 * Second Iteration n=2
				 * 
				 * While loop will returns true for n=2. Step1: Program will
				 * check the condition at if, pre increment will increase the
				 * value of n by 1 before comparing it with 0 as compiler
				 * executes expression from left to right.Comparison fails but
				 * value of n become 3. Step2: Program first checks else-if
				 * condition, before post incrementing the value. The value of
				 * n(3) is compared to 1,again the test will fail but the value
				 * of n will become 4. Step3: Program checks second else-if
				 * condition, before post incrementing the value.The value of
				 * n(4) is compared to 2, test will fail but value of n will
				 * become 5. Step4: Print statement of else part will execute
				 * and "d/  n is 5" will be printed.
				 * 
				 * Third Iteration n=5 Condition in while loop will return false
				 * for n=5
				 */
				if (++n == 0)
					System.out.println("a/  n is " + n);
				else if (n++ == 1) {
					System.out.println("b/  n is " + n);
				} else if (n++ == 2)
					System.out.println("c/  n is " + n);
				else
					System.out.println("d/  n is " + n);

				System.out.println("executing break here");

			}

			System.out.println(n % 2 == 0 ? (n == 4 ? "=" : "!") : (n % 3 != 0 ? "3" : "!3"));
			/**
			 * Inside the print statement first n%2==0 will be executed as n=5
			 * test will fail,When the test fails, second part after the :'s ((
			 * n % 3 != 0 ? "3" : "!3" )) will get executed. As n%3 !=0 for n=5
			 * this inner test will return true and String "3" will be returned
			 * to the print statement. 3 will be printed.
			 */

			break here;
		}
	}
}

