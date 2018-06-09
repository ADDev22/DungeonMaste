package decorateur;

import enumeration.Dir;
import errors.*;
import services.Environement;
import services.Mob;

public class MobDecorator implements Mob{

	protected Mob delegate;
	
	 public MobDecorator(Mob delegate) {
		 this.delegate=delegate;
	}
	
	
	
	@Override
	public Environement Env() {
		return delegate.Env();
	}

	@Override
	public int Col() {
		return delegate.Col();
	}

	@Override
	public int Row() {
		return delegate.Row();
	}

	@Override
	public Dir Face() {
		return delegate.Face();
	}

	@Override
	public void init(Environement e, int col, int row, Dir d) throws Error{
		delegate.init(e, col, row, d);
		
	}

	@Override
	public void Forward() throws InvariantError, PostConditionError, PreConditionError{
		delegate.Forward();
	}

	@Override
	public void Backward() throws InvariantError, PostConditionError, PreConditionError {
		delegate.Backward();
	}

	@Override
	public void TurnL() throws InvariantError, PostConditionError, PreConditionError {
		delegate.TurnL();
	}

	@Override
	public void TurnR() throws InvariantError, PostConditionError, PreConditionError {
		delegate.TurnR();
	}

	@Override
	public void StrafeL() throws InvariantError, PostConditionError, PreConditionError{
		delegate.StrafeL();
	}

	@Override
	public void StrafeR() throws InvariantError, PostConditionError, PreConditionError {
		delegate.StrafeR();
	}

}
