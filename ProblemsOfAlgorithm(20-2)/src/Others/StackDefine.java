package Others;

import java.util.*;
import java.io.*;

interface st {
	boolean isEmpty();
	void push(String s);
	String pop();
	String peek();
	void clear();
}

class UserStack implements st {
	private int top;
	private String[] stackArr;
	
	public UserStack(int size) {
		this.top = -1;
		stackArr = new String[size];
	}

	@Override
	public boolean isEmpty() {
		if(top == -1) 
			return true;
		else
			return false;
	}

	@Override
	public void push(String s) {
		top++;
		stackArr[top] = s;
	}

	@Override
	public String pop() {
		if(!isEmpty()) {
			String box = stackArr[top];
			top--;
			return box;
		}
		else
			return "Stack size is 0";
	}

	@Override
	public String peek() {
		if(!isEmpty()) 
			return stackArr[top];
		else
			return "Stack size is 0";
	}

	@Override
	public void clear() {
		while(!isEmpty()) {
			pop();
		}
	}
}

public class StackDefine {
	public static void main(String[] args) {
		// 1. 라이브러리 사용
		Stack<String> st = new Stack<String>();
		st.push("NUM1");
		st.push("NUM2");
		st.push("NUM3");
		st.push("NUM4");
		
		while(!st.isEmpty()) {
			System.out.println(st.pop());
		}
		
		// 2. 직접 정의한 스택 사용
		UserStack st2 = new UserStack(5);
		st2.push("NUM1");
		st2.push("NUM2");
		st2.push("NUM3");
		st2.push("NUM4");
		
		while(!st2.isEmpty()) {
			System.out.println(st2.pop());
		}
	}

}
