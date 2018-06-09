package decorateur;

import enumeration.Dir;
import services.Environement;
import services.Monster;

public class MonsterDecorator extends CowDecorator implements Monster {

	
	protected Monster delegate;
	
	public MonsterDecorator(Monster delegate) {
		super(delegate);
		this.delegate=delegate;
	}
	@Override
	public void init(Environement e, int col, int row, Dir d, int hp, int Striking_power) {
		delegate.init(e, col, row, d, hp, Striking_power);
		
	}

	@Override
	public int Striking_Power() {
		
		return delegate.Striking_Power();
	}

	@Override
	public void Attack() {
		
		delegate.Attack();
		
	}


	@Override
	public void Step() {
		
		delegate.Step();
		
	}
}
