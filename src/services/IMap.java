package services;

import enumeration.Cell;

public interface IMap {
	
	// ===========OBSERVATORS==============
	public int height();
	public int width();
	/**
	 * pre : cellNature(x,y) requires 0 ≤ x < width(M) and 0 ≤ y < height(M)
	 */
	public Cell cellNature(int x,int y); 
	
	// ===========CONSTRUCTORS==============
	
	/**
	 * pre init(h,w) requires 0 < w and 0 < h
	 */
	public void init(int h, int w);
	
	// ===========OPERATORS==============
	
	/**
	 * pre : openDoor(x,y) requires CellNature(x,y) ∈ {DNC, DWC }
	 * <br>
	 * post : cellNature(x,y) == DWC implies CellNature(OpenDoor(M,x,y),x,y) = DWO
	CellNature(M,x,y) = DNC implies CellNature(OpenDoor(M,x,y),x,y) = DNO
	forall u ∈ [0; Width(M)-1] forall v ∈ [0; Height(M)-1] (u  != x or v != y)
      implies CellNature(OpenDoor(M,x,y),u,v) = CellNature(M,u,v)
	 */
	public void openDoor(int x, int y);
	
	/**
	 * pre : closeDoor(x,y) requires CellNature(x,y) ∈ {DNC, DWC }
	 * <br>
	 * post : cellNature(x,y) == DWO implies CellNature(OpenDoor(M,x,y),x,y) = DWC
	CellNature(M,x,y) = DNO implies CellNature(OpenDoor(M,x,y),x,y) = DNC
	forall u ∈ [0; Width(M)-1] forall v ∈ [0; Height(M)-1] (u  != x or v != y)
      implies CellNature(OpenDoor(M,x,y),u,v) = CellNature(M,u,v)
	 */
	public void closeDoor(int x, int y);
}
