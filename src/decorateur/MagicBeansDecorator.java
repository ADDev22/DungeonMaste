package decorateur;


import services.Environement;
import services.MagicBeans;

public class MagicBeansDecorator implements MagicBeans{

	protected MagicBeans delegate;
	
	public MagicBeansDecorator(MagicBeans delegate) {
		this.delegate=delegate;
	}
	
	@Override
	public Environement env() {
		return delegate.env();
	}

	@Override
	public int row() {
		return delegate.row();
	}

	@Override
	public int col() {
		return delegate.col();
	}

	@Override
	public void init(Environement env, int col, int row) {
		delegate.init(env, col, row);
	}

	@Override
	public void init(Environement env, int col, int row, int Striking_power) {
		delegate.init(env, col, row, Striking_power);	
		}

	@Override
	public int Striking_power_bonus() {
		return delegate.Striking_power_bonus();
	}

	@Override
	public void init(int Striking_power_bonus) {
		delegate.init(Striking_power_bonus);
		
	}

	@Override
	public void init() {
		delegate.init();
		
	}
	
}
