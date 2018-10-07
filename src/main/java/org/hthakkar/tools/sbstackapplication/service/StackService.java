package org.hthakkar.tools.sbstackapplication.service;

import org.hthakkar.tools.sbstackapplication.domain.EmptyStackException;
import org.hthakkar.tools.sbstackapplication.domain.FullStackException;
import org.springframework.stereotype.Service;

@Service("stackService")
public class StackService {

	private StackElement top;
	private int maxSize;
	private int currentSize;
	
	public StackService(int maxSize) {
		this.maxSize = maxSize;
		this.currentSize = 0;
		this.top = null;
	}
	
	public StackService() {
		this(5);
	}
	
	public int getSize() {
		return currentSize;
	}
	
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	
	public void push(int value) throws FullStackException {
		if (currentSize == maxSize) {
			throw new FullStackException();
		}
		currentSize++;
		StackElement currentElement = new StackElement();
		currentElement.setValue(value);
		if (top == null) {
			top = currentElement;
			currentElement.setNext(null);
		} else {
			currentElement.setNext(top);
			top = currentElement;
		}
	}
	
	public int pop() throws EmptyStackException {
		if (top == null) {
			throw new EmptyStackException();
		}
		StackElement returnElement = top;
		top = returnElement.getNext();
		currentSize--;
		return returnElement.getValue();
	}
	
	public int peek() throws EmptyStackException {
		if (top == null) {
			throw new EmptyStackException();
		}
		return top.getValue();
	}
	
	private class StackElement {
		private int value;
		private StackElement next;
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public StackElement getNext() {
			return next;
		}
		public void setNext(StackElement next) {
			this.next = next;
		}
	}
}
