

/**
 * 
 * @author Deepak Sharma DS5930
 * @author Sree Lakshmi Kurra SK9040
 *
 */

public class AA extends A {
	/**
	 * Class AA is inheriting class from A
	 */
	int aInt = 1;

	/**
	 * Constructor of class AA. which will assign value 11 when any object of AA
	 * will be initialized.
	 */
	AA() {
		aInt = 11;
	}

	/**
	 * Value of aInt is incremented by 1.
	 * 
	 * @return increases the value of aInt.
	 */
	public int intPlusPlus() {

		return ++aInt;
	}

	/**
	 * This method overrides toString method of A class and returns class name
	 * followed by value of class field aInt. Default implementation in
	 * Object.toString() prints the class name followed by the object's hash
	 * code.
	 */
	public String toString() {
		/**
		 * this.getClass gives current object in the memory.
		 */
		return this.getClass().getName() + ": " + aInt;
	}

	public static void main(String args[]) {

		AA aAA = new AA();
		/**
		 * Object of AA class with reference aAA,constructor of A class and AA
		 * class has been invoked. aAA.aInt assigned to value 11 super.aInt
		 * assigned to value 11 using constructor of class A.
		 */
		A aA = (A) aAA;
		/**
		 * In the above statement reference of A class aA was created.
		 * Previously created object aAA upcasted to class A, and assigned to
		 * newly created reference aA of class A.
		 */
		aAA.intPlusPlus();
		/**
		 * method intPlusPlus of object aAA has been invoked. value of aAA.aInt
		 * becomes 12.
		 */
		aA.intPlusPlus();
		/**
		 * method intPlusPlus of object aAA has been invoked because reference
		 * aA is also referring to the same object. Value of aAA.aInt becomes
		 * 13.
		 */
		System.out.println(aA);
		/**
		 * 
		 * aA implicitly invokes method aAA.toString() while printing the
		 * statement. The only object exists in memory is of class AA hence
		 * this.getClass().getName() gives AA and aInt value for AA.aInt.
		 * Finally it returns "AA: 13" and print statement prints the return
		 * value.
		 */
		System.out.println(aAA);
		/**
		 * aAA implicitly invokes method aAA.toString() in print statement. The
		 * only object that exists in memory is of class AA hence
		 * this.getClass().getName() gives AAA and aInt value for AA.aInt, which
		 * returns "AA: 13" and prints the return value.
		 */
		System.out.println("aA: " + aA.aInt);
		/**
		 * Fields in Java are not overridden,they only become hidden when child
		 * class has the same Field name. Field's binding to the object in Java
		 * is not a run time phenomena, it is decided during the compile time.
		 * Hence according to the type of the object reference, files are
		 * accessed in java. hence in this case, aA.aInt accesses the value of
		 * aInt filed associated with aA reference.
		 * 
		 */
	}
}
