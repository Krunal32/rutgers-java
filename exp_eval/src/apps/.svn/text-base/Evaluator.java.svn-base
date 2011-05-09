package apps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Evaluator {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader brstdin = new BufferedReader(new InputStreamReader(
				System.in));
		brstdin = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.print("\nEnter the expression, or hit return to quit => ");
			String line = brstdin.readLine();
			if (line.length() == 0) {
				return;
			}
			Expression expr = new Expression(line);
			boolean match = expr.isLegallyMatched();
			System.out.println("Expression legally matched: " + match);
			if (!match) {
				continue;
			}
			expr.buildSymbols();
    		expr.printScalars();
    		expr.printArrays();

			System.out.print("Enter symbol values file name, or hit return if no symbols => ");
			line = brstdin.readLine();
			if (line.length() != 0) {
				BufferedReader brfile = new BufferedReader(new FileReader(line));
				expr.loadSymbolValues(brfile);
				//expr.printScalars();
				//expr.printArrays();
			}
			System.out.println("Value of expression = " + expr.evaluate());
		}

	}

}