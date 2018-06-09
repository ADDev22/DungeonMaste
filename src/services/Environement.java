package services;


import errors.*;
public interface Environement extends Map{

	/**
	 * \pré delegate != null && delegate instanceof MapContract || delegate instanceof EditMapContract 
	 * @param delegate
	 * @throws PreconditionError 
	 */
	public void init(Map delegate);
	/**
	 * \pre  0 <= col < width() &&  0 <= row < height()
	 * \post true
	 * @throws PreconditionError 
	 */
	public Containable  CellContent(int col, int row) throws PreConditionError;

	
/* pré closeDoor(x,y) :  en plus des pré héritées on aura :  CellContent(x,y) == No */

	/**
	 * \pre true
	 * \post true;
	 * /**
	 * \pre  0 <= col < width() &&  0 <= row < height() 
	 * 		 && (  (cellnature(x,y) ∈ {EMP,DNO,DWO,IN,OUT} && CellContent(x,y)= null) ||  (cellnature(x,y) ∈{DWC,DNC} && c instanceof KEY) ). 
	 * \post Cellcontent(col,row) == m && forall cells != (x,y) leurs contenue reste le meme.
	 * @throws PreconditionError 
	 * @throws PostconditionError 
	 */
	public Containable setCellContent(int col, int row,Containable C) throws PreConditionError, PostConditionError;




}
