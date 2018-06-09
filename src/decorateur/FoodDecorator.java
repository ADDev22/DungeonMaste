package decorateur;

import services.Environement;
import services.Food;

public class FoodDecorator implements Food {

	protected Food delegate;
	
	public FoodDecorator(Food delegate) {
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
	public void init(Environement env, int col, int row, int Hp_value) {
		delegate.init(env, col, row, Hp_value);
	}

	@Override
	public void init(int Hp_value) {
		delegate.init(Hp_value);
		
	}

	@Override
	public void init() {
		delegate.init();
	}

	@Override
	public int Hp_Value() {
		return delegate.Hp_Value(); 
	}

}
