package decorateur;

import enumeration.Dir;
import errors.*;
import services.Cow;
import services.Environement;
import services.IMob;

public class CowDecorator implements Cow{

	protected Cow delegate;
	
	public CowDecorator(Cow delegate) {
		
		this.delegate=delegate;
	}
	
	@Override
	public int Hp() {
		return delegate.Hp() ;
	}

	@Override
	public void init(Environement e, int col, int row, Dir d, int hp) throws PreConditionError, PostConditionError, InvariantError {
		delegate.init(e, col, row, d, hp);
	}

	@Override
	public void Step() throws InvariantError, PostConditionError, PreConditionError {
		
		delegate.Step();
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
	public void init(Environement e, int col, int row, Dir d)
			throws Error {
		delegate.init(e, col, row, d);
	}

	@Override
	public void Forward() throws InvariantError, PostConditionError, PreConditionError {
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
	public void StrafeL() throws InvariantError, PostConditionError, PreConditionError {
		delegate.StrafeL();		
	}

	@Override
	public void StrafeR() throws InvariantError, PostConditionError, PreConditionError {
		delegate.StrafeR();		
	}

	@Override
	public void SetHp(int hp) {
		delegate.SetHp(hp);
	}

	@Override
	public void Set_Spoil(IMob spoil) {
		delegate.Set_Spoil(spoil);
	}

	@Override
	public IMob Drop_Spoil() {
		return delegate.Drop_Spoil(); 
	}

}
