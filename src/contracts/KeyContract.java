package contracts;

import decorateur.KeyDecorator;
import errors.PostConditionError;
import errors.PreConditionError;
import services.Environement;
import services.Key;

public class KeyContract extends KeyDecorator implements Key{

	@Override
	public String toString() {
		return "Key";
	}
	public KeyContract(Key delegate) {
		super(delegate);
		
	}

	@Override
	public void init(int door_col, int door_row, int col, int row, Environement env) {
	
		if(env==null) throw new PreConditionError("null env");
		if(!(0<=col)) throw new PreConditionError("col<0");
		if(! (col < env.width()) ) throw new PreConditionError("col >= width()");
		if(! (door_col >=0 )) throw new PreConditionError("door_col < 0");
		if(! (door_col < env.width()) ) throw new PreConditionError("door_col >= width()");
		if(!(0<=row)) throw new PreConditionError("y<0");
		if(! (row< env.height()) ) throw new PreConditionError("row >= heigth()");
		if(! (door_row >=0 )) throw new PreConditionError("door_row < 0");
		if(! (door_row < env.height()) ) throw new PreConditionError("door_row >= heigth()");
		if (!(env.CellContent(col, row) == null))  throw new PreConditionError("cellContent should be No");
		
		super.init(door_col, door_row, col, row, env);
		
		if(row() != row)throw new PostConditionError("Row() != row");
		if(col() != col)throw new PostConditionError("Col() != col");
		if(env() != env)throw new PostConditionError("env() != env");
		if(Door_col() != door_col)throw new PostConditionError("Door_col() != door_col");
		if(Door_row() != door_row)throw new PostConditionError("Door_row() != door_row");
		if ((env.CellContent(col, row) != delegate))  throw new PostConditionError("cellContent should be So(this)");
	
	
	
	}

	@Override
	public void init(int door_col, int door_row, Environement env) {
		if(env==null) throw new PreConditionError("null env");
		if(! (door_col >=0 )) throw new PreConditionError("door_col < 0");
		if(! (door_col < env.width()) ) throw new PreConditionError("door_col >= width()");
		if(! (door_row >=0 )) throw new PreConditionError("door_row < 0");
		if(! (door_row < env.height()) ) throw new PreConditionError("door_row >= heigth()");
		
		super.init(door_col, door_row, env);
		
		if(env() != env)throw new PostConditionError("env() != env");
		if(Door_col() != door_col)throw new PostConditionError("Door_col() != door_col");
		if(Door_row() != door_row)throw new PostConditionError("Door_row() != door_row");
	}
	
	
	
	
	
}
