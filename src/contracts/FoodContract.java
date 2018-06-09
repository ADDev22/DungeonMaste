package contracts;

import decorateur.FoodDecorator;
import errors.PostConditionError;
import errors.PreConditionError;
import services.Environement;
import services.Food;


public class FoodContract  extends FoodDecorator{

	@Override
	public String toString() {
		return "Food";
	}
	
	public FoodContract(Food delegate) {
		super(delegate);
	
	}
	
	@Override
	public void init(Environement env, int col, int row) {

		if(env==null) throw new PreConditionError("null env");
		if(!(0<=col)) throw new PreConditionError("col<0");
		if(! (col< env.width()) ) throw new PreConditionError("col >= width()");
		if(!(0<=row)) throw new PreConditionError("y<0");
		if(! (row< env.height()) ) throw new PreConditionError("row >= heigth()");
		if (!(env.CellContent(col, row) == null))  throw new PreConditionError("cellContent should be No");
		
		super.init(env, col, row);
		
		if(row() != row)throw new PostConditionError("Row() != row");
		if(col() != col)throw new PostConditionError("Col() != col");
		if(env() != env)throw new PostConditionError("env() != env");
		if ((env.CellContent(col, row) != delegate))  throw new PostConditionError("cellContent should be No");
		
		
		if(Hp_Value() != 1) throw new PostConditionError("hp != 1");
	}

	@Override
	public void init(Environement env, int col, int row, int Hp_value) {
		
		if(Hp_value <=0 ) throw new PreConditionError("hp_value should be positive");
		if(env==null) throw new PreConditionError("null env");
		if(!(0<=col)) throw new PreConditionError("col<0");
		if(! (col< env.width()) ) throw new PreConditionError("col >= width()");
		if(!(0<=row)) throw new PreConditionError("y<0");
		if(! (row< env.height()) ) throw new PreConditionError("row >= heigth()");
		if (!(env.CellContent(col, row) == null))  throw new PreConditionError("cellContent should be No");
	
		super.init(env, col, row, Hp_value);
		
		
		if(row() != row)throw new PostConditionError("Row() != row");
		if(col() != col)throw new PostConditionError("Col() != col");
		if(env() != env)throw new PostConditionError("env() != env");
		if ((env.CellContent(col, row) != delegate))  throw new PostConditionError("cellContent should be No");
	
		if(Hp_Value() != this.Hp_Value()) throw new PostConditionError("hp != hp_value");
	}

	@Override
	public void init(int Hp_value) {
		if(Hp_value <=0 ) throw new PreConditionError("hp_value should be positive");
		super.init(Hp_value);
		if(Hp_Value() != this.Hp_Value()) throw new PostConditionError("hp != hp_value");
	}

	@Override
	public void init() {
		
		super.init();
		if(Hp_Value() != 1) throw new PostConditionError("hp != 1");
	}

	

}
