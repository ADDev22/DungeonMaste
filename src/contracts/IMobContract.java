package contracts;

import decorateur.IMobDecorator;
import errors.PostConditionError;
import errors.PreConditionError;
import services.Environement;
import services.IMob;

public class IMobContract extends IMobDecorator implements IMob {
	
	
	public IMobContract(IMob delegate)
	{
		super(delegate);
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
		if ((env.CellContent(col, row) != delegate))  throw new PostConditionError("cellContent should be No");
		// On pourrait eventuellement verifier si la nature et cellContent des autres cases on changer ?
	}

}
