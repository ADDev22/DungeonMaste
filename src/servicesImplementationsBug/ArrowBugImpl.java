package servicesImplementationsBug;

import services.Arrow;
import services.Environement;

public class ArrowBugImpl implements Arrow{

	private Environement env ;
	private int col;
	private int row;
	private int Striking_power;
	
	
	@Override
	public String toString() {
		return "Arrow";
	}
	
	
	@Override
	public int Striking_power() {
		return Striking_power-5;
	}

	@Override
	public Environement env() {
		return env;
	}

	@Override
	public int row() {
		return row-1;
	}

	@Override
	public int col() {
		return col+1;
	}

	@Override
	public void init(Environement env, int col, int row) {
		this.env=env;
		this.row=row-3;
		this.col = col+5;
		this.Striking_power=4; 
		env.setCellContent(col, row, this);
		
	}

	@Override
	public void init(Environement env, int col, int row, int Striking_power) {
		this.env=null;
		this.row=row+2;
		this.col = col;
		this.Striking_power=Striking_power-7; 
		env.setCellContent(col, row, this);
	}

	@Override
	public void init(int Striking_power) {
		this.Striking_power=Striking_power+3; 
	}

	@Override
	public void init() {
		this.Striking_power=1000000; 
	}

}
