package contracts;

import decorateur.ArrowDecorator;
import errors.PostConditionError;
import errors.PreConditionError;
import services.Arrow;
import services.Environement;

public class ArrowContract extends ArrowDecorator{

	
	
	public ArrowContract(Arrow delegate) {
		super(delegate);
		
	}

	@Override
	public String toString() {
		return "Arrow";
	}
	
	@Override
	public Environement env() {
	
		return super.env();
	}

	@Override
	public int row() {

		return super.row();
	}

	@Override
	public int col() {

		return super.col();
	}
	
	@Override
	public int Striking_power() {
		
		return super.Striking_power();
	}

	@Override
	public void init(Environement env, int col, int row) {
		
		if(env==null) throw new PreConditionError("null env");
		if(!(0<=col)) throw new PreConditionError("col<0");
		if(! (col < env.width()) ) throw new PreConditionError("col >= width()");
		if(!(0<=row)) throw new PreConditionError("y<0");
		if(! (row < env.height()) ) throw new PreConditionError("row >= heigth()");
		if (!(env.CellContent(col, row) == null))  throw new PreConditionError("cellContent should be No");
		
		super.init(env, col, row);
		
		if(row() != row)throw new PostConditionError("Row() != row");
		if(col() != col)throw new PostConditionError("Col() != col");
		if(env() != env)throw new PostConditionError("env() != env");
		if(Striking_power() != 4 )throw new PostConditionError("Striking_power() != 4 ");
		if ((env.CellContent(col, row) != delegate))  throw new PostConditionError("cellContent should be No");
		// On pourrait eventuellement verifier si la nature et cellContent des autres cases on changer ?
		
	}

	@Override
	public void init(Environement env, int col, int row, int Striking_power) {
		
		if(env==null) throw new PreConditionError("null env");
		if(!(0<=col)) throw new PreConditionError("col<0");
		if(! (col < env.width()) ) throw new PreConditionError("col >= width()");
		if(!(0<=row)) throw new PreConditionError("y<0");
		if(! (row < env.height()) ) throw new PreConditionError("row >= heigth()");
		if (!(env.CellContent(col, row) == null))  throw new PreConditionError("cellContent should be No");
		if(Striking_power<=0) throw new PreConditionError("Striking_power<=0");
		
		super.init(env, col, row, Striking_power);
		
		if(row() != row)throw new PostConditionError("Row() != row");
		if(col() != col)throw new PostConditionError("Col() != col");
		if(env() != env)throw new PostConditionError("env() != env");
		if(Striking_power() != Striking_power )throw new PostConditionError("Striking_power() != triking_power ");
		if ((env.CellContent(col, row) != delegate))  throw new PostConditionError("cellContent should be No");
		// On pourrait eventuellement verifier si la nature et cellContent des autres cases on changer ?
				
	}

	@Override
	public void init(int Striking_power) {
		if(Striking_power<0) throw new PreConditionError("Striking_power<0");
		super.init(Striking_power);
		if(Striking_power() != Striking_power )throw new PostConditionError("Striking_power() != triking_power ");
	}

	@Override
	public void init() {
		
		super.init();
		if(Striking_power() != 4 )throw new PostConditionError("Striking_power() != 4 ");
	}

	

	
	
}
