// Imported Libraries 
import java.util.Scanner;
import java.math.BigInteger;

public class BaseConversion {
	public static void main(String args[]) {
		// Variables
		Scanner input = new Scanner(System.in);
		String value;
		int initialBase;
		int finalBase;
		final int MIN_BASE = 2;
		final int MAX_BASE = 36;

		// In case of an input of all values are made, skip the output.
		if (args.length == 3) {
			value = args[0];
			initialBase = Integer.parseInt(args[1]);
			finalBase = Integer.parseInt(args[2]);
			input.close();
		} else {

			// Output and input.
			System.out.println("Hello there! Welcome to the Base Conversion program.\n");
			System.out.print("Please enter a value to convert:");

			value = input.next();

			System.out.print("\nPlease enter the initial base:");
			initialBase = input.nextInt();

			System.out.print("\nPlease enter the base to convert to:");
			finalBase = input.nextInt();

			input.close();
		}

		// Check initial base and final base and make sure they are both in range.
		if (initialBase < MIN_BASE || initialBase > MAX_BASE) {
			System.out.println("Initial base is not within the 2-36 range.");
			return;
		} else if (finalBase < MIN_BASE || finalBase > MAX_BASE) {
			System.out.println("Final base is not within the 2-36 range.");
			return;
		}

		// If the value is valid with the initial base given, continue with calculations
		// and output result.
		if (isValidInteger(value, initialBase) == true) {
			String convertedNum = convertBase(value, initialBase, finalBase);
			System.out.print("\nConverted Value: " + convertedNum);
		}

	}

	// Method for validation, compares the value with the initial base's rules.
	public static boolean isValidInteger(String theValue, int theBase) {
		char charCheck;
		for (int i = 0; i < theValue.length(); i++) {
			charCheck = theValue.toUpperCase().charAt(i);
			if (Character.isDigit(charCheck) && (charCheck - '0') >= theBase) {
				System.out.println("Value is not compatible with initial base.");
				return false;
			} else if (Character.isLetter(charCheck) && (charCheck - 'A') + 10 >= theBase) {
				System.out.println("Value is not compatible with initial base.");
				return false;
			} else if (!Character.isDigit(charCheck) && !Character.isLetter(charCheck)) {
				System.out.println("Value is not compatible with initial base.");
				return false;
			}
		}
		return true;
	}

	// This is the main method for conversion.
	public static String convertBase(String value, int initialBase, int finalBase) {
		String finalValue;

		// For the base 10 number it calls the base 10 conversion method.
		BigInteger baseTenValue = convertToBaseTen(value, initialBase);

		if (finalBase == 10) { // If the wanted final base is base 10 stop here.
			return baseTenValue.toString();
		} else {
			// Take the base 10 number, send it to the convertFromBaseTen method, and get
			// the final result back.
			finalValue = convertFromBaseTen(baseTenValue, finalBase);
			return finalValue;
		}
	}

	public static BigInteger convertToBaseTen(String value, int initialBase) {
		BigInteger result = BigInteger.ZERO; // Initialized BigInteger result at 0.
		int power = 0; // Initialized the power variable for the math.

		// This loop goes through the value and converts any letters to their base 10
		// correspondent.
		for (int i = value.length() - 1; i >= 0; i--) {
			char charTest = value.charAt(i);

			int charValue;
			if (Character.isDigit(charTest)) {
				charValue = charTest - '0';
			} else {
				charValue = Character.toUpperCase(charTest) - 'A' + 10;

			}
			BigInteger convertedValue = BigInteger.valueOf(charValue)
					.multiply(BigInteger.valueOf(initialBase).pow(power));
			result = result.add(convertedValue); // This keeps track of the numbers being translated to base 10.
			power++;
		}

		return result;
	}

	public static String convertFromBaseTen(BigInteger baseTenValue, int finalBase) {

		StringBuilder result = new StringBuilder();

		// Go through the loop till the value goes to 0.
		while (!baseTenValue.equals(BigInteger.ZERO)) {
			BigInteger remainder = baseTenValue.remainder(BigInteger.valueOf(finalBase));

			char digitCharValue; // This is used to find what the digit's char value would be depending on the
									// base.

			// This either leaves the numbers alone or if letters are needed, adds them.
			if (remainder.intValue() < 10) {
				digitCharValue = (char) (remainder.intValue() + '0');
			} else {
				digitCharValue = (char) (remainder.intValue() - 10 + 'A');
			}

			// This keeps track of the results of the calculations.
			result.insert(0, digitCharValue);

			// This line is responsible for repeatedly dividing the baseTenValue by the
			// target base.
			baseTenValue = baseTenValue.divide(BigInteger.valueOf(finalBase));
		}
		// Returns the result as a string.
		return result.toString();
	}
}