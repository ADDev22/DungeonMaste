package services;


import enumeration.Dir;
import errors.*;
public interface Mob extends /*refines*/ Containable {

	
	
	public Environement Env();
	public int Col();
	public int Row();
	public Dir Face();

	
	/** Invariants
	 * 0 <= Col() < Env().Width() 
	 * 0 <= Row() < Env().Height()
	 * Env().CellNature(Col(),Row()) !∈ {WLL,DNC,DWC,IN,OUT} 
	 *  
	 *  */
	
	
	/** 
	 * \pre  e != null && d !=null && 0 ≤ col < e.Width() && 0 ≤ row < e.Height() && e.cellContent() == No
	 * \post Col()==col && Row()==row && Env()==e && Face()==d 	&& Env().CellContent() == So(this)
	 * 
	 * 	on pourrait ajouter Env().CellContent(Col(),Row())== So(this) comme invariant mais c'est inutile car on le vérifie dans 
	 * 	la post condition de chaque constructeur ou opérateur
	 * @throws PostconditionError 
	 * @throws PreconditionError 
	 * @throws Exception 
	 * */
	public void init(Environement e,int col , int row, Dir d) throws InvariantError, PreConditionError, PostConditionError, Error;
	
	
	/**
	 * \pre  true
	 * \post 
	 *  Face()==N => ( Env().CellNature(Col(),Row()+1) ∈ {EMP,DWO} &&  Row()+1 < Env().Height() && Env().CellContent(Col(),Row()+1)== No ) => 
	    Row() == Row () @pré +1 && Col()== Col() @pré && Face()== N  && Env().CellContent(Col(),Row()) == So(this) .
	    Face()==N => ! ( Env().CellNature(Col(),Row()+1) ∈ {EMP,DWO} &&  Row()+1 < Env().Height() && Env().CellContent(Col(),Row()+1)== No ) => 
	    Row() == Row () @pré  && Col()== Col() @pré && Face()== N  && Env().CellContent(Col(),Row()) == So(this) .
	    
	    Face()==E => ( Env().CellNature(Col()+1,Row()) ∈ {EMP,DNO} &&  Col()+1 < Env().Width() && Env().CellContent(Col()+1,Row())== No ) => 
	    Row() == Row () @pré && Col()== Col() @pré +1 && Face()== E  && Env().CellContent(Col(),Row()) == So(this).
		Face()==E => ! ( Env().CellNature(Col()+1,Row()) ∈ {EMP,DNO} &&  Col()+1 < Env().Width() && Env().CellContent(Col()+1,Row())== No ) => 
	    Row() == Row () @pré && Col()== Col() @pré && Face()== E  && Env().CellContent(Col(),Row()) == So(this).
	    
	    Face()==S => ( Env().CellNature(Col(),Row()-1) ∈ {EMP,DWO} &&  Row()-1 >=0  && Env().CellContent(Col(),Row()-1)== No ) => 
	    Row() == Row () @pré - 1 && Col()== Col() @pré && Face()== S  && Env().CellContent(Col(),Row()) == So(this) .
	    Face()==S => ! ( Env().CellNature(Col(),Row()-1) ∈ {EMP,DWO} &&  Row()-1 >=0  && Env().CellContent(Col(),Row()-1)== No ) => 
	    Row() == Row () @pré && Col()== Col() @pré && Face()== S  && Env().CellContent(Col(),Row()) == So(this) .
	    
	    Face()==W => ( Env().CellNature(Col()-1,Row()) ∈ {EMP,DNO} &&  Col()-1 >=0  && Env().CellContent(Col()-1,Row())== No ) => 
	    Row() == Row () @pré  && Col()== Col() @pré -1 && Face()== W  && Env().CellContent(Col(),Row()) == So(this) .
	    Face()==W => ! ( Env().CellNature(Col()-1,Row()) ∈ {EMP,DNO} &&  Col()-1 >=0  && Env().CellContent(Col()-1,Row())== No ) => 
	    Row() == Row () @pré  && Col()== Col() @pré && Face()== W  && Env().CellContent(Col(),Row()) == So(this) .
	 * @throws PostconditionError 
	 * @throws PreconditionError 
	*/
	public void Forward() throws InvariantError, PostConditionError, PreConditionError;
	

	/**
	 * \pre  true
	 * \post 
	 *  Face()==N => ( Env().CellNature(Col(),Row()-1) ∈ {EMP,DWO} &&  Row()-1 >= 0 && Env().CellContent(Col(),Row()-1)== No ) => 
	    Row() == Row () @pré - 1 && Col()== Col() @pré && Face()== N  && Env().CellContent(Col(),Row()) == So(this) .
	    Face()==N => ! ( Env().CellNature(Col(),Row()-1) ∈ {EMP,DWO} &&  Row()-1 >= 0 && Env().CellContent(Col(),Row()-1)== No ) => 
	    Row() == Row () @pré  && Col()== Col() @pré && Face()== N  && Env().CellContent(Col(),Row()) == So(this) .
	    
	    Face()==E => ( Env().CellNature(Col()-1,Row()) ∈ {EMP,DNO} &&  Col()-1 >=0 && Env().CellContent(Col()-1,Row())== No ) => 
	    Row() == Row () @pré && Col()== Col() @pré -1 && Face()== E  && Env().CellContent(Col(),Row()) == So(this).
		Face()==E => ! ( Env().CellNature(Col()-1,Row()) ∈ {EMP,DNO} &&  Col()-1 >=0  && Env().CellContent(Col()-1,Row())== No ) => 
	    Row() == Row () @pré && Col()== Col() @pré && Face()== E  && Env().CellContent(Col(),Row()) == So(this).
	    
	    Face()==S => ( Env().CellNature(Col(),Row()+1) ∈ {EMP,DWO} &&  Row()+1 <= Env().Height()  && Env().CellContent(Col(),Row()+1)== No ) => 
	    Row() == Row () @pré + 1 && Col()== Col() @pré && Face()== S  && Env().CellContent(Col(),Row()) == So(this) .
	    Face()==S => ! ( Env().CellNature(Col(),Row()+1) ∈ {EMP,DWO} &&  Row()+1 <= Env().Height()  && Env().CellContent(Col(),Row()+1)== No ) => 
	    Row() == Row () @pré && Col()== Col() @pré && Face()== S  && Env().CellContent(Col(),Row()) == So(this) .
	    
	    Face()==W => ( Env().CellNature(Col()+1,Row()) ∈ {EMP,DNO} &&  Col()+1<=Env().Width()  && Env().CellContent(Col()+1,Row())== No ) => 
	    Row() == Row () @pré  && Col()== Col() @pré +1 && Face()== W  && Env().CellContent(Col(),Row()) == So(this) .
	    Face()==W => ! ( Env().CellNature(Col()+1,Row()) ∈ {EMP,DNO} &&  Col()+1 <= Env().Width()  && Env().CellContent(Col()+1,Row())== No ) => 
	    Row() == Row () @pré  && Col()== Col() @pré && Face()== W  && Env().CellContent(Col(),Row()) == So(this) .
	 * @throws PostconditionError 
	 * @throws PreconditionError 
	*/
	
	
	
	
	
	
	public void Backward() throws InvariantError, PostConditionError, PreConditionError;

	
	/**
	 *  \pre true
	 *  \post Face() @pré == N => Face()== W
	 *  	  Face() @pré == S => Face()== E
	 * 		  Face() @pré == E => Face()== N
	 *  	  Face() @pré == W => Face()== S
	 *  Env().CellNature() @ pré == Env().CellNature() && Env().CellContent(Col(),Row()) == Env().CellContent(Col(),Row()) @ pré ==So(this).   
	 * @throws PostconditionError 
	 * @throws PreconditionError 
	 *  	
	 */
	public void TurnL() throws InvariantError, PostConditionError, PreConditionError;
	
	/**
	 *  \pre true
	 *  \post Face() @pré == N => Face()== E
	 *  	  Face() @pré == S => Face()== W
	 * 		  Face() @pré == E => Face()== S
	 *  	  Face() @pré == W => Face()== N
	 *  Env().CellNature() @ pré == Env().CellNature() && Env().CellContent(Col(),Row()) == Env().CellContent(Col(),Row())  @ pré ==So(this). 	
	 * @throws PostconditionError 
	 * @throws PreconditionError 
	 */
	public void TurnR()  throws InvariantError, PostConditionError, PreConditionError;
	
	/**
	 * \pre  true
	 * \post 
	 *  Face()==N => ( Env().CellNature(Col()-1,Row()) ∈ {EMP,DNO} &&  Col()-1 >= 0 && Env().CellContent(Col()-1,Row())== No ) => 
	    Row() == Row () @pré && Col()== Col() @pré -1  && Face()== N  && Env().CellContent(Col(),Row()) == So(this) .
	    Face()==N => ! ( Env().CellNature(Col()-1,Row()) ∈ {EMP,DNO} &&  Col()-1 >= 0 && Env().CellContent(Col()-1,Row())== No ) => 
	    Row() == Row () @pré && Col()== Col() @pré  && Face()== N  && Env().CellContent(Col(),Row()) == So(this) .
	    
	    Face()==E => ( Env().CellNature(Col(),Row()+1) ∈ {EMP,DWO} &&  Row()+1 <= Env().Height() && Env().CellContent(Col(),Row()+1)== No ) => 
	    Row() == Row () @pré +1 && Col()== Col() @pré && Face()== E  && Env().CellContent(Col(),Row()) == So(this).
	 	Face()==E => ! ( Env().CellNature(Col(),Row()+1) ∈ {EMP,DWO} &&  Row()+1 <= Env().Height() && Env().CellContent(Col(),Row()+1)== No ) => 
	    Row() == Row () @pré  && Col()== Col() @pré && Face()== E  && Env().CellContent(Col(),Row()) == So(this).
	    
	    Face()==S => ( Env().CellNature(Col()+1,Row()) ∈ {EMP,DNO} &&  Col()+1 <= Env().Width()  && Env().CellContent(Col()+1,Row())== No ) => 
	    Row() == Row () @pré && Col()== Col() @pré +1 && Face()== S  && Env().CellContent(Col(),Row()) == So(this) .
	    Face()==S => ! ( Env().CellNature(Col()+1,Row()) ∈ {EMP,DNO} &&  Col()+1 <= Env().Width() && Env().CellContent(Col()+1,Row())== No ) => 
	    Row() == Row () @pré && Col()== Col() @pré && Face()== S  && Env().CellContent(Col(),Row()) == So(this) .
	    
	    Face()==W => ( Env().CellNature(Col(),Row()-1) ∈ {EMP,DWO} &&  Row()-1 >=0  && Env().CellContent(Col(),Row()-1)== No ) => 
	    Row() == Row () @pré -1 && Col()== Col() @pré && Face()== W  && Env().CellContent(Col(),Row()) == So(this) .
 		Face()==W => ! ( Env().CellNature(Col(),Row()-1) ∈ {EMP,DWO} &&  Row()-1 >=0  && Env().CellContent(Col(),Row()-1)== No ) => 
	    Row() == Row () @pré && Col()== Col() @pré && Face()== W  && Env().CellContent(Col(),Row()) == So(this) .
	 * @throws PostconditionError 
	 * @throws PreconditionError 
	*/
	public void StrafeL() throws InvariantError, PostConditionError, PreConditionError;
	
	/**
	 * \pre  true
	 * \post 
	 *  Face()==N => ( Env().CellNature(Col()+1,Row()) ∈ {EMP,DNO} &&  Col()+1 <= Env().Width() && Env().CellContent(Col()+1,Row())== No ) => 
	    Row() == Row () @pré && Col()== Col() @pré +1  && Face()== N  && Env().CellContent(Col(),Row()) == So(this) .
	    Face()==N => ! ( Env().CellNature(Col()+1,Row()) ∈ {EMP,DNO} &&  Col()+1 <= Env().Width() && Env().CellContent(Col()+1,Row())== No ) => 
	    Row() == Row () @pré && Col()== Col() @pré && Face()== N  && Env().CellContent(Col(),Row()) == So(this) .
	    
	    Face()==E => ( Env().CellNature(Col(),Row()-1) ∈ {EMP,DWO} &&  Row()-1 >= 0 && Env().CellContent(Col(),Row()-1)== No ) => 
	    Row() == Row () @pré -1 && Col()== Col() @pré && Face()== E  && Env().CellContent(Col(),Row()) == So(this).
	 	Face()==E => ! ( Env().CellNature(Col(),Row()-1) ∈ {EMP,DWO} &&  Row()-1 >= 0 && Env().CellContent(Col(),Row()-1)== No ) => 
	    Row() == Row () @pré && Col()== Col() @pré && Face()== E  && Env().CellContent(Col(),Row()) == So(this).
	    
	    Face()==S => ( Env().CellNature(Col()-1,Row()) ∈ {EMP,DNO} &&  Col()-1 >= 0  && Env().CellContent(Col()-1,Row())== No ) => 
	    Row() == Row () @pré && Col()== Col() @pré - 1 && Face()== S  && Env().CellContent(Col(),Row()) == So(this) .
	   	Face()==S => ! ( Env().CellNature(Col()-1,Row()) ∈ {EMP,DNO} &&  Col()-1 >= 0  && Env().CellContent(Col()-1,Row())== No ) => 
	    Row() == Row () @pré && Col()== Col() @pré && Face()== S  && Env().CellContent(Col(),Row()) == So(this) .
	    
	    Face()==W => ( Env().CellNature(Col(),Row()+1) ∈ {EMP,DWO} &&  Row()+1 <= Env().Height()  && Env().CellContent(Col(),Row()+1)== No ) => 
	    Row() == Row () @pré +1 && Col()== Col() @pré && Face()== W  && Env().CellContent(Col(),Row()) == So(this) .
 		Face()==W => ! ( Env().CellNature(Col(),Row()+1) ∈ {EMP,DWO} &&  Row()+1 <= Env().Height()  && Env().CellContent(Col(),Row()+1)== No ) => 
	    Row() == Row () @pré && Col()== Col() @pré && Face()== W  && Env().CellContent(Col(),Row()) == So(this) .
	 * @throws InvariantException 
	 * @throws PostconditionError 
	 * @throws PreconditionError 
	*/
	
	
	
	public void StrafeR() throws InvariantError, PostConditionError, PreConditionError;



}
