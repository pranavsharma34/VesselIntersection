package main;

public class Pair<T1, T2> {

	private T1 _left;
	private T2 _right;
	
	public Pair(T1 left, T2 right)
	{
		setLeft(left);
		setRight(right);
	}
	
	public Pair()
	{
		
	}
	
	private void setRight(T2 right) 
	{
		_right = right;
	}

	private void setLeft(T1 left) 
	{
		_left = left;
	}

	public T1 getLeft()
	{
		return _left;
	}
	
	public T2 getRight()
	{
		return _right;
	}	
	
	
}
