import java.util.Scanner;

/**
 * 
 * @author HPS251 Harminder Singh
 * Credit to Professor Klukowska for providing code to evaluating Postfix strings. I did change it up a little!
 * This is the main class that changes infix expressions to postfix. Similarly, it evaluates Postfix expressions.
 */
public class PostFixEvaluator {
	
	//Postfix String. Returned by the infixtopostfix conversion method.
	private String postfix;
	
	/**
	 * Constructor for the PostFixEvaluator
	 */
	public PostFixEvaluator() {
		
	}
	
	/**
	 * This method converts an infix string to a postfix string.
	 * @param infix - An infix string
	 * @return - A postfix string
	 */
	public String InfixToPostfix(String infix) {

		//Initialize the postfix string
		this.postfix = "";
		//Initialize the stack
		MyStack<String> stacks = new MyStack<String>();
		
		//Initialize the operator, value, and temporary string.
		String operator;
		int val;
		String temp;
		
		//Initialize the token with the infix string
		Scanner token = new Scanner(infix);
		
		//Loop through each token
		while(token.hasNext()) {
			//If it's an int, add it to the postfix expression
			if(token.hasNextInt()) {
				val = token.nextInt();
				postfix += val + " ";
			}
			else {
				//Set the next token to a string
				operator = token.next();
				
				//If it's a left bracket, push it to the stack
				if(isBrace(operator) == 1) {
					stacks.push(operator);
				}
				
				//If it's an operator
				else if(isOperator(operator)) {
					//If the stack isn't empty
					if(stacks.isEmpty() != true) {
						//If the head of the stack is an operator of higher precendence, pop and add to Postfixexpression. Similarly, push new operator to stack
						if(precendence(stacks.peek()) >= precendence(operator)) {
							temp = stacks.pop();
							postfix += temp + " ";
							stacks.push(operator);
						}
						else {
							//Push operator to stack if it's not of higher precendence
							stacks.push(operator);
						}
					}
					//If the stack doesn't contain an operator on the top, push operator onto stack
					else {
						stacks.push(operator);
					}
				}
				
				//If it's a right bracket
				else if(isBrace(operator) == 2) {
					//If stack isn't empty
					while(stacks.isEmpty() != true) {
						//If top of the stack isn't a left bracket
						if(isBrace(stacks.peek()) != 1) {
							//Pop and add to postfix expression
							temp = stacks.pop();
							postfix += temp + " ";
						}
						else {
							//Pop all strings remaining on stack
							stacks.pop();
							break;
						}
					}
				}
			}
		}
		
		//If stacks not empty, pop and add all values to the expression
		while(stacks.isEmpty() != true) {
			temp = stacks.pop();
			postfix += temp + " ";
		}
		
		//Return the post fix expression
		return postfix;
	}
	
	/**
	 * This method returns an int representing the strength of precedence
	 * @param operator - String operator
	 * @return - int telling the precendence of the operator
	 */
	public int precendence(String operator) {
		//+ and - are 1
		if(operator.equals("+")) {
			return 1;
		}
		else if(operator.equals("-")) {
			return 1;
		}
		//* and / are 2
		else if(operator.equals("*")) {
			return 2;
		}
		else if(operator.equals("/")) {
			return 2;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * This method returns true if the string operator is an operator
	 * @param operator - String operator
	 * @return boolean indicating if it's an operator
	 */
	public boolean isOperator(String operator) {
		if(operator.equals("*")) {
			return true;
		}
		else if(operator.equals("/")) {
			return true;
		}
		else if(operator.equals("-")) {
			return true;
		}
		else if(operator.equals("+")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Return an int indicating whether or not it's a left or right brace.
	 * @param operator - String
	 * @return - 1 if it's a left bracket, 2 if it's a right bracket
	 */
	public int isBrace(String operator) {
		if(operator.equals("(")) {
			return 1;
		}
		else if(operator.equals(")")) {
			return 2;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * This is the main method used to evaluate postfix expressions. Credit to Professor Klukowska for providing most of the code for this method.
	 * @param eval - PostFix String to be evaluated
	 * @return - String with the solution
	 * @throws PostFixException - Exception used to handle various problems encountered with evaluation
	 */
	public String evaluate(String eval) throws PostFixException {
		
		//Initiallize a stack to hold integers
		MyStack<Integer> stack = new MyStack<Integer>();
		
		//Initiallize the operator and the value used to reference next ints
		int value;
		String operator;
		
		//Initialize the operands and result
		int operand1;
		int operand2;
		int result = 0;
		
		//Initiallize the Scanner with the Post Fix Expression
		Scanner tokenizer = new Scanner(eval);
		int counter = 0;
		//Loop through each token
		while(tokenizer.hasNext()) {
			
			//If it's an integer, add it to the stack
			if(tokenizer.hasNextInt()) {
				value = tokenizer.nextInt();
				stack.push(value);
				counter++;
			}
			else {
				//If it's an operator
				operator = tokenizer.next();
				
				//If stack is empty, PFE - Not enough operands
				if(stack.isEmpty()) {
					tokenizer.close();
				}
				
				//Obtain operand2 by popping stack
				operand2 = stack.peek();
				stack.pop();
				
				//If stack is empty after only popping once, PFE - Not enough operands
				if(stack.isEmpty()) {
					tokenizer.close();
					throw new PostFixException("Not enough operands - stack underflow");
				}
				
				//Pop operand1 from stack
				operand1 = stack.peek();
				stack.pop();

				//Evaluation steps using operator and operands...
				if(operator.equals("*")) {
					result = operand1 * operand2;
				}
				else if(operator.equals("/")) {
					result = operand1 / operand2;
				}
				else if(operator.equals("+")) {
					result = operand1 + operand2;
				}
				else if(operator.equals("-")) {
					result = operand1 - operand2;
				}
				//PFE - Invalid symbol
				else {
					tokenizer.close();
					throw new PostFixException("Illegal symbol: " + operator);
				}
				//Push result back onto the stack
				stack.push(result);
			}
			
		}
		//Close tokenizer
		tokenizer.close();
		
		//If stack doesn't contain results, Not enough operands!
		if(stack.isEmpty()) {
			throw new PostFixException("Not enough operands - stack underflow");
		}
		//Pop result
		result = stack.peek();
		stack.pop();
		
		//If stack contains more numbers after popping results, PFE - Too many operands
		if(!stack.isEmpty()) {
			throw new PostFixException("Too many operands - operands left over");
		}
		//Return the value in the form of a string
		return Integer.toString(result);
	}
	
	
	
	
}
