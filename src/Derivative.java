//A simple Java class that can calculate the derivative of a simple polynomial.

public class Derivative {

	// Parses the function string. Takes in a string with the function that you
	// want the derivative of, and returns an array of integers that represents
	// the coeffiecients of each degree
	private static int[] parseFunc(String func) {
		String newFunc = func.replace(" ", "");
		boolean isPoly = true;
		int high = 0;
		int l = newFunc.length();

		// check and make sure that there are no operands or symbols that would
		// not be found in a polynomial
		for (int i = 0; i < l; i++) {
			char c = newFunc.charAt(i);
			if (c == '/') {
				isPoly = false;
				break;
			}
			if (c == '^') {
				char t = newFunc.charAt(i + 1);
				if (Character.isDigit(t)) {
					if (Integer.parseInt(Character.toString(t)) > high)
						high = Integer.parseInt(Character.toString(t));
				} else
					isPoly = false;

			}
		}

		if (!isPoly)
			return null;

		int[] polyArray = new int[high];

		for (int i = 0; i < l; i++) {
			int e;
			int ce;
			char x = newFunc.charAt(i);
			if (Character.isLetter(x)) {
				if (i + 1 < l) {
					char c = newFunc.charAt(i + 1);

					if (c == '^') {
						e = Integer.parseInt(Character.toString(newFunc
								.charAt(i + 2)));
					} else {
						e = 1;
					}
				} else
					e = 1;

				if (Character.isDigit(newFunc.charAt(i - 1)))
					ce = Integer.parseInt(Character.toString(newFunc
							.charAt(i - 1)));
				else
					ce = 1;

				polyArray[e - 1] = ce;
				if ((i - 2) >= 0) {
					char s = newFunc.charAt(i - 2);
					char ss = newFunc.charAt(i - 1);
					if (s == '-' || ss == '-')
						polyArray[e - 1] = polyArray[e - 1] * (-1);
				}
			}
		}

		return polyArray;
	}

	// Builds a string that gives you the derivative function. Takes in an
	// integer array representing the coefficients of each degree in the
	// function
	private static String constructDerivative(int[] polyArray) {

		if (polyArray == null)
			return null;

		int l = polyArray.length;
		StringBuilder sb = new StringBuilder(l * 3);
		for (int i = l - 1; i >= 0; i--) {
			if (polyArray[i] != 0) {
				if (sb.length() == 0) {
					sb.append(polyArray[i] * (i + 1));
				} else {
					if (polyArray[i] < 0) {
						sb.append(" - ");
						sb.append(Math.abs(polyArray[i]) * (i + 1));
					} else if (polyArray[i] > 0) {
						sb.append(" + ");
						sb.append(polyArray[i] * (i + 1));
					}
				}
				if (i > 0)
					sb.append("x");
				if (polyArray[i] != 0 && i > 1) {
					sb.append("^");
					sb.append(i);
				}
			}
		}
		return (new String(sb));
	}

	// The public function. Takes in a string from the user representing a
	// polynomial. Functions should be written in the form
	// "Cx^n + Cx^n-1 - Cx^n-2...etc." Otherwise the
	// the program won't parse the function correctly or at all.
	public static String calculateDerivative(String func) {
		return constructDerivative(parseFunc(func));
	}

	public static void main(String[] args) {
		System.out.println(Derivative.calculateDerivative("4x^4 + 3x^2 + 3x"));

	}

}
