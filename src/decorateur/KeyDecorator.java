package decorateur;

import services.Environement;
import services.Key;
import servicesImplementations.KeyImpl;

public class KeyDecorator implements Key{

	protected Key delegate; 
	
	public KeyDecorator(Key delegate) {
	
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
	public void init(int door_col, int door_row, int col, int row, Environement env) {
		delegate.init(door_col, door_row, col, row, env);
	}

	@Override
	public int Door_col() {
		return delegate.Door_col();
	}

	@Override
	public int Door_row() {
		return delegate.Door_row();
	}

	@Override
	public void init(int door_col, int door_row, Environement env) {
		delegate.init(door_col, door_row, env);
		
	}

	
	
}
