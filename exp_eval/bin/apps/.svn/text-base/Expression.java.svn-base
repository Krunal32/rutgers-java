package apps;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import structures.Stack;

/*
 * Methods developed by Steven Lu (sjlu at eden dot rutgers dot edu)
 */

public class Expression {

	/**
	 * Expression to be evaluated
	 */
	String expr;                
    
	/**
	 * Scalar symbols in the expression 
	 */
	ArrayList<ScalarSymbol> scalars;   
	
	/**
	 * Array symbols in the expression
	 */
	ArrayList<ArraySymbol> arrays;
    
	/**
	 * Positions of opening brackets
	 */
	ArrayList<Integer> openingBracketIndex; 
    
	/**
	 * Positions of closing brackets
	 */
	ArrayList<Integer> closingBracketIndex; 
	
	public StringTokenizer expr_tokens = null;

    /**
     * String containing all delimiters (characters other than variables and constants), 
     * to be used with StringTokenizer
     */
    public static final String delims = "*+-/()[]";
    
    /**
     * Initializes this Expression object with an input expression. Sets all other
     * fields to null.
     * 
     * @param expr Expression
     */
    public Expression(String expr) {
        this.expr = expr;
        scalars = null;
        arrays = null;
        openingBracketIndex = null;
        closingBracketIndex = null;
    }

    /**
     * Matches parentheses and square brackets. Populates the openingBracketIndex and
     * closingBracketIndex array lists in such a way that closingBracketIndex[i] is
     * the position of the bracket in the expression that closes an opening bracket
     * at position openingBracketIndex[i]. For example, if the expression is:
     * <pre>
     *    (a+(b-c))*(d+A[4])
     * </pre>
     * then the method would return true, and the array lists would be set to:
     * <pre>
     *    openingBracketIndex: [0 3 10 14]
     *    closingBracketIndex: [8 7 17 16]
     * </pre>
     * 
     * @return True if brackets are matched correctly, false if not
     */
    
    /*
     * Method: We scan the expression, char by char and see if they are any of the
     * "([])" and puts their index into an array list, opening and closing respectively.
     * We then make sure the sizes of both index arrays match. 
     * 
     * If all is good, we then have to nest into the string, making sure that each
     * beginning bracket matches its sister ending bracket.
     * 
     * For example, if someone puts in (a+b+A[3)], it will past the first checks, but
     * will not pass the second checks. This is why we need our private, recursive
     * helper method.
     */
    
    private boolean isLegallyMatched(StringTokenizer tokenizer, String match)
    {
    	String token = null;
    	boolean good = true;
    	while (tokenizer.hasMoreTokens())
    	{
    		token = tokenizer.nextToken().trim();
    		
    		if (token != null && !token.equals(""))
    		{
	    		if (token.equalsIgnoreCase("(") || token.equalsIgnoreCase("["))
	    		{
	    			good = this.isLegallyMatched(tokenizer, token);
	    			
	    			if (good == false)
	    			{
	    				return false;
	    			}
	    			else
	    			{
	    				continue;
	    			}
	    		}
	    		else if (token.equalsIgnoreCase(")") || token.equalsIgnoreCase("]"))
	    		{
	    			if (match.equals("("))
	    			{
	    				if (token.equals(")"))
	    				{
	    					return true;
	    				}
	    				else
	    				{
	    					return false;
	    				}
	    			}
	    			else if (match.equals("["))
	    			{
	    				if (token.equals("]"))
	    				{
	    					return true;
	    				}
	    				else
	    				{
	    					return false;
	    				}
	    			}
	    		}
    		}
    	}
    	
    	return good;
    }
    
    public boolean isLegallyMatched() {
    	openingBracketIndex = new ArrayList<Integer>();
    	closingBracketIndex = new ArrayList<Integer>();
    	
    	for (int i = 0; i < expr.length(); i++)
    	{
    		if (expr.charAt(i) == '(' || expr.charAt(i) == '[')
    		{
    			openingBracketIndex.add(i);
    			continue;
    		}
    		
    		if (expr.charAt(i) == ')' || expr.charAt(i) == ']')
    		{
    			closingBracketIndex.add(i);
    			continue;
    		}
    	}
    	
    	if (openingBracketIndex == null && closingBracketIndex == null)
    	{
    		return true;
    	}
    	else if (openingBracketIndex == null || closingBracketIndex == null || openingBracketIndex.size() != closingBracketIndex.size())
    	{
    		return false;
    	}
    	
    	//ammended on 3/7/11
    	if (openingBracketIndex.get(0) < closingBracketIndex.get(0))
    	{
    		return false;
    	}
    	
    	expr_tokens = new StringTokenizer(expr, delims, true);
    	return this.isLegallyMatched(expr_tokens, "");
    }

    /**
     * Populates the scalars and arrays lists with symbols for scalar and array
     * variables in the expression. For every variable, a SINGLE symbol is created and stored,
     * even if it appears more than once in the expression.
     * At this time, values for all variables are set to
     * zero - they will be loaded from a file in the loadSymbolValues method.
     */
    
    /*
     * Method: We will use StringTokenizer (basically a regexp) to split our string up
     * with the delimiters listed above. From this, we basically get a list of all the 
     * variable names.
     * 
     * We tokenize everything and place it into a stack. We then reverse the stack, to
     * make it easily parsable as a "equation". If we hit one of our delimiters as a popped item,
     * we make sure not to add it and continue to the next pop. If our popped item is anything 
     * else, we check to see if the next pop (by peeking) is a array (square bracket). We add
     * it as an array if its true, and a scalar if it is false, keeping in mind that if it already
     * exists, we probably don't want to add in a duplicate.
     * 
     * Notes: We place everything we've calcualted as a string into our stack, since we want to be
     * a little more uniform.
     * 
     * Since we put in results in backwards, we also need to reverse the stack everytime we create
     * a new stack. This prevents us from parsing the equation backwards.
     */
    public void buildSymbols() {
    	arrays = new ArrayList<ArraySymbol>();
    	scalars = new ArrayList<ScalarSymbol>();
    	
    	expr_tokens = new StringTokenizer(expr, delims, true);
    	Stack<String> st_stack = new Stack<String>();
    	
    	while (expr_tokens.hasMoreTokens())
    	{
    		String token = expr_tokens.nextToken().trim();
    		if (token != null && !token.equals("")) // this is just a way of making sure StringTokenizer works like we want it to...
    		{
    			st_stack.push(token);
    		}
    	}
    	
    	st_stack = this.reverseStack(st_stack);
    	
    	while (!st_stack.isEmpty())
    	{
    		String token = st_stack.pop();
    		if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")
    				|| token.equals("(") || token.equals(")") || token.equals("[") || token.equals("]"))
    		{
    			continue;
    		}
    		else
    		{
    			try
    			{
    				Float.parseFloat(token);
    			}
    			catch (Exception e)
    			{
    				if (!st_stack.isEmpty() && st_stack.peek().equals("["))
        			{
    					if (!this.isArray(token))
    					{
    						arrays.add(new ArraySymbol(token));
    					}
        			}
        			else
        			{
        				if (!this.isScalar(token))
        				{
        					scalars.add(new ScalarSymbol(token));
        				}
        			}
    			}
    		}
    	}
    }
    
    /**
     * Loads values for symbols in the expression
     * 
     * @param br BufferedReader for values input
     * @throws IOException If there is a problem with the input 
     */
    public void loadSymbolValues(BufferedReader br) 
    throws IOException {
        String line;
        while ((line = br.readLine())!= null) {
            StringTokenizer st = new StringTokenizer(line);
            int numTokens = st.countTokens();
            String sym = st.nextToken();
            ScalarSymbol ssymbol = new ScalarSymbol(sym);
            ArraySymbol asymbol = new ArraySymbol(sym);
            int ssi = scalars.indexOf(ssymbol);
            int asi = arrays.indexOf(asymbol);
            if (ssi == -1 && asi == -1) {
            	continue;
            }
            int num = Integer.parseInt(st.nextToken());
            if (numTokens == 2) { // scalar symbol
                scalars.get(ssi).value = num;
            } else { // array symbol
            	asymbol = arrays.get(asi);
            	asymbol.values = new int[num];
                // following are (index,val) pairs
                while (st.hasMoreTokens()) {
                    String tok = st.nextToken();
                    StringTokenizer stt = new StringTokenizer(tok," (,)");
                    int index = Integer.parseInt(stt.nextToken());
                    int val = Integer.parseInt(stt.nextToken());
                    asymbol.values[index] = val;              
                }
            }
        }
    }
    
    /**
     * Evaluates the expression, using RECURSION to evaluate subexpressions and to evaluate array 
     * subscript expressions.
     * 
     * @return Result of evaluation
     */
    
    /* Method: We go into the tokenizer and add our operands and operators into our stack. If we
     * hit a bracket or paranthesis, we recursively call our function. This makes sure we have values
     * instead of brackets/paranthesis so that we can evaluate it.
     * 
     * After building our stack which now only has single operands and operators, we then replace
     * all symbols with values. If our symbol matches as an array, we then pop the next number
     * in our stack in order to get the value.
     * 
     * Now everything is a float and a operator, therefore we want to perform all of our multiplication
     * and division first according to PEMDAS. We do those operations, placing their result into a new stack.
     * 
     * Addition and subtraction work the same way as multiplcation and division. After this step,
     * there will only be one number in our new stack, which we will return.
     */
    
    private boolean isScalar(String name)
    {
    	for (int i = 0; i < scalars.size(); i++)
    	{
    		if (scalars.get(i).name.equals(name))
    		{
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    private boolean isArray(String name)
    {
    	for (int i = 0; i < arrays.size(); i++)
    	{
    		if (arrays.get(i).name.equals(name))
    		{
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    private int getScalarValue(String name)
    {
    	for (int i = 0; i < scalars.size(); i++)
    	{
    		if (scalars.get(i).name.equals(name))
    		{
    			return scalars.get(i).value;
    		}
    	}
    	
    	return 0;
    }
    
    private int getArrayValue(String name, int position)
    {
    	for (int i = 0; i < arrays.size(); i++)
    	{
    		if (arrays.get(i).name.equals(name))
    		{
    			System.out.println("array val:"+arrays.get(i).values[position]);
    			return arrays.get(i).values[position];
    		}
    	}
    	
    	return 0;
    }
    
    private Stack<String> reverseStack(Stack<String> stack)
    {
    	Stack<String> reversed_stack = new Stack<String>();
    	
    	while (!stack.isEmpty())
    	{
    		reversed_stack.push(stack.pop());
    	}
    	
    	return reversed_stack;
    }
    
    private float performOperation(String int1, String int2, String oper)
    {
    	if (oper.equalsIgnoreCase("*"))
    	{
    		return Float.parseFloat(int1)*Float.parseFloat(int2);
    	}
    	else if (oper.equalsIgnoreCase("/"))
        {
        	return Float.parseFloat(int1)/Float.parseFloat(int2);
        }
    	else if (oper.equalsIgnoreCase("+"))
        {
        	return Float.parseFloat(int1)+Float.parseFloat(int2);
        } 
    	else if (oper.equalsIgnoreCase("-"))
        {
    		return Float.parseFloat(int1)-Float.parseFloat(int2);
        }
    	
    	return 0;
    }
    
    private float evaluate(StringTokenizer tokenizer)
    {
    	Stack<String> expr_stack = new Stack<String>();
    	
    	String token = null;
    	while (tokenizer.hasMoreTokens())
    	{
    		token = tokenizer.nextToken().trim();
    		
    		if (token != null && !token.equals(""))
    		{
    		
	    		if (token.equalsIgnoreCase("(") || token.equalsIgnoreCase("["))
	    		{
	    			expr_stack.push(Float.toString(this.evaluate(tokenizer)));
	    		}
	    		else if (token.equalsIgnoreCase(")") || token.equalsIgnoreCase("]"))
	    		{
	    			break;
	    		}
	    		else
	    		{
	    			expr_stack.push(token);
	    		}
    		}
    	}
    	// This will give us all tokens that are scalarsymbols, arraysymbols and operators
    	// First thing we want to do is convert all symbols to values
    	expr_stack = this.reverseStack(expr_stack);
    	
    	Stack<String> non_symbol_stack = new Stack<String>();
    	
    	while (!expr_stack.isEmpty())
    	{
    		String stack_pop = expr_stack.pop();
    		if (this.isScalar(stack_pop))
    		{
    			non_symbol_stack.push(Float.toString(this.getScalarValue(stack_pop)));
    		}
    		else if (this.isArray(stack_pop))
    		{
    			String index = expr_stack.pop();
    			non_symbol_stack.push(Float.toString(this.getArrayValue(stack_pop, (int) Float.parseFloat(index))));
    		}
    		else
    		{
    			non_symbol_stack.push(stack_pop);
    		}
    	}
    	
    	// Now we want to reverse our stack.
    	non_symbol_stack = this.reverseStack(non_symbol_stack);
    	
    	// Now we want to evaluate multiplication and division symbols ONLY
    	Stack<String> multi_divi_eval_stack = new Stack<String> ();
    	while (!non_symbol_stack.isEmpty())
    	{
    		String stack_pop = non_symbol_stack.pop();
    		
    		if (stack_pop.equalsIgnoreCase("*") || stack_pop.equalsIgnoreCase("/"))
    		{
    			System.out.println(multi_divi_eval_stack.peek()+" "+stack_pop+" "+non_symbol_stack.peek());
    			multi_divi_eval_stack.push(Float.toString(this.performOperation(multi_divi_eval_stack.pop(), non_symbol_stack.pop(), stack_pop)));
    			System.out.println(multi_divi_eval_stack.peek());
    		}
    		else
    		{
    			multi_divi_eval_stack.push(stack_pop);
    		}
    	}
    	
    	multi_divi_eval_stack = this.reverseStack(multi_divi_eval_stack);
    	Stack<String> eval_stack = new Stack<String> ();
    	
    	while (!multi_divi_eval_stack.isEmpty())
    	{
    		String stack_pop = multi_divi_eval_stack.pop();
    		
    		if (stack_pop.equalsIgnoreCase("-") || stack_pop.equalsIgnoreCase("+"))
    		{
    			System.out.println(eval_stack.peek()+" "+stack_pop+" "+multi_divi_eval_stack.peek());
    			eval_stack.push(Float.toString(this.performOperation( eval_stack.pop(),multi_divi_eval_stack.pop(),  stack_pop)));
    			System.out.println(eval_stack.peek());
    		}
    		else
    		{
    			eval_stack.push(stack_pop);
    		}
    	}
    	
    	return Float.parseFloat(eval_stack.pop());
    }
    
    public float evaluate() {
    	expr_tokens = new StringTokenizer(expr, delims, true);
    	return evaluate(expr_tokens);
    }

    /**
     * Prints the symbols in the scalars list
     */
    public void printScalars() {
        for (ScalarSymbol ss: scalars) {
            System.out.println(ss);
        }
    }
    
    /**
     * Prints the symbols in the arrays list
     */
    public void printArrays() {
    	for (ArraySymbol as: arrays) {
    		System.out.println(as);
    	}
    }

}