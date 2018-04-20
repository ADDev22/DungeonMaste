package services;

import enumeration.Option;

public interface IEnvironment extends /* includes */ IMap{
	
	// ===========OBSERVATORS==============
	/**
	 * 
	 * pre : cellContent(x1,y1)requires
	 * cellContent(x1,y1)(x,y) requires 0 ≤ x < width(E) and 0 ≤ y < height(E)
	 *
	 */
	public Option cellContent(int x1,int y1);
	
	// ========INVARIANTS==================

	// ===========CONSTRUCTORS==============
	

	// ===========OPERATORS==============
	
	/**
	 * pre closeDoor(E,x,y) requires
	 * cellContent(E,x,y) = No
	 */
     @Override
	 public void closeDoor(int x1, int y1);

}
