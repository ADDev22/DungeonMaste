package servicesImplementations;

import services.Arrow;
import services.Environement;

public class ArrowImpl implements Arrow{

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
		return Striking_power;
	}

	@Override
	public Environement env() {
		return env;
	}

	@Override
	public int row() {
		return row;
	}

	@Override
	public int col() {
		return col;
	}

	@Override
	public void init(Environement env, int col, int row) {
		this.env=env;
		this.row=row;
		this.col = col;
		this.Striking_power=4; 
		env.setCellContent(col, row, this);
		
	}

	@Override
	public void init(Environement env, int col, int row, int Striking_power) {
		this.env=env;
		this.row=row;
		this.col = col;
		this.Striking_power=Striking_power; 
		env.setCellContent(col, row, this);
	}

	@Override
	public void init(int Striking_power) {
		this.Striking_power=Striking_power; 
	}

	@Override
	public void init() {
		this.Striking_power=4; 
	}

}
