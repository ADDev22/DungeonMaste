package servicesImplementationsBug;

import services.Environement;
import services.Key;

public class KeyBugImpl extends IMobBugImpl implements  Key {

	private int Door_col;
	private int Door_row;
	
	@Override
	public String toString() {
		return "Key";
	}
	
	@Override
	public void init(Environement env, int col, int row) {
       
		throw new Error("Deprecated constructor. you need to specify door coordinates"); 
		
	}
	@Override
	public void init(int door_col, int door_row, int col, int row, Environement env) {
		
		this.env=env;
	    this.col=col;
	    this.row=row;
	    env.setCellContent(col, row, this);
		this.Door_col=door_col;
		this.Door_row=door_row;
		
	}
	@Override
	public int Door_col() {
		return Door_col;
	}
	@Override
	public int Door_row() {
		return Door_row;
	}
	@Override
	public void init(int door_col, int door_row, Environement env) {
		this.Door_col=door_col;
		this.Door_row=door_row;
		this.env=env;
	}
	
	
	
}
