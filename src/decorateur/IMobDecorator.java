package decorateur;

import services.Environement;
import services.IMob;

public class IMobDecorator   implements IMob {
	
	public IMobDecorator(IMob delegate) {
		super();
		this.delegate = delegate;
	}

	protected IMob delegate;

	public int row() {
		return delegate.row();
	}

	public int col() {
		return delegate.col();
	}

	public void init(Environement env, int col, int row) {
		delegate.init(env, col, row);
	}

	@Override
	public Environement env() {
	  return delegate.env();
	}

}
