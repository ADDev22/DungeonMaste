package services;

import enumeration.Cell;

public interface EditMap extends /* refine */  Map{
	

	// ===========OBSERVATORS==============
	/**
	 * 
	 * pre : isReachable(x1,y1,x2,y2)requires
	 *       cellNature(x1,y1)  != WLL &&
	 *       cellNature(x2,y2)  != WLL
	 */
	public boolean isReachable(int x1,int y1,int x2,int y2);
	
	public boolean isReady();
	
	
	// ========INVARIANTS==================
	/**
	 * isReachable(M,x1,y1,x2,y2) = exists P in Array[int,int],P[0] = (x1,y1) and P[size(P)-1] = (x2,y2)
	 * and forall i in [1;size(P)-1], (P[i-1]=(u,v) and P[i]=(s,t)) implies (u−s)^2 + (v−t)^22 = 1
	 * and forall i in [1;size(P)-2], P[i-1]=(u,v) implies CellNature(M,u,v) != WLL
	 * 
	 * isReady() = exists xi,yi,xo,yo in int^4 ,
	 * 				CellNature(xi,yi) = IN and CellNature(xi,yi) = OUT
	 * 				and isReachable(xi,yi,xo,yo)
	 * 				and forall x,y in int^2 , x  != xi or y != yi implies CellNature(M,x,y) != IN
	 * 				and forall x,y in int^2 , x  != xo or y  != yo implies CellNature(M,x,y) != OUT
	 * 	    forall x,y in int, CellNature(x,y) ∈ { DNO, DNC} implies
	 * 				cellNature(x+1,y) = cellNature(x-1,y) = EMP and
	 * 				cellNature(x,y-1) = cellNature(x,y+1) = WLL
	 * 		forall x,y in int, CellNature(M,x,y) ∈ { DWO, DWC} implies
	 * 				cellNature(x+1,y) = CellNature(x-1,y) = WLL and
	 * 				cellNature(x,y-1) = CellNature(x,y+1) = EMP
	 * 
	 */
	// ===========CONSTRUCTORS==============
	

	// ===========OPERATORS==============
	
	/**
	 * pre : setNature(x,y) requires 
	 * 		0<= x < width()
	 *      0<= y < height()
	 * post: cellNature(x,y) = nat
	 *  	forall u,v in int^2 , u  != x or v != y implies cellNature(u,v) = cellNature(u,v)@pre
	 *      
	 */
	public void setNature(int x, int y,Cell nat);
	
}
