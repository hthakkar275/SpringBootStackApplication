package org.hthakkar.tools.sbstackapplication.service;

import java.util.Stack;
import org.springframework.stereotype.Service;

@Service("javaStackService")
public class JavaStackService {

	private Stack<Integer> stack;
	
	public JavaStackService() {
		this.stack = new Stack<>();
	}
	
	public void push(int number) {
		this.stack.push(number);
	}
	
	public int peek() {
		return this.stack.peek();
	}
	
	public int pop() {
		return this.stack.pop();
	}
	
}
