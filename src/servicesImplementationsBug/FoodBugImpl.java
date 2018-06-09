package servicesImplementationsBug;

import services.Environement;
import services.Food;

public class FoodBugImpl  extends IMobBugImpl implements Food{

	protected int Hp_value=1;

	@Override
	public String toString() {
		return "Food";
	}
	
	@Override
	public int Hp_Value() {
	
		return Hp_value;
	}
	
	@Override
	public void init(Environement env, int col, int row) {
		super.init(env, col, row);
		this.Hp_value=1;  
	}

	@Override
	public void init(Environement env, int col, int row, int Hp_value) {
		super.init(env, col, row);
		this.Hp_value=Hp_value; 
		
	}
	@Override
	public void init(int Hp_value) {
		this.Hp_value=Hp_value;
		
	}
	@Override
	public void init() {
		this.Hp_value=1; 
		
	}

}
