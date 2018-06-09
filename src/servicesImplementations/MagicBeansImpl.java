package servicesImplementations;

import services.Environement;
import services.MagicBeans;

public class MagicBeansImpl implements MagicBeans{

	private Environement env ;
	private int col;
	private int row;
	private int Striking_power_bonus;
	
	
	@Override
	public String toString() {
		return "MagicBeans";
	}
	
	
	@Override
	public int Striking_power_bonus() {
		return Striking_power_bonus;
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
		this.Striking_power_bonus=1; 
		env.setCellContent(col, row, this);
		
	}

	@Override
	public void init(Environement env, int col, int row, int Striking_power_bonus) {
		this.env=env;
		this.row=row;
		this.col = col;
		this.Striking_power_bonus=Striking_power_bonus; 
		env.setCellContent(col, row, this);
	}

	@Override
	public void init(int Striking_power_bonus) {
		this.Striking_power_bonus=Striking_power_bonus; 
	}

	@Override
	public void init() {
		this.Striking_power_bonus=1; 
	}
}
