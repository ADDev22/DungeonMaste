package servicesImplementations;

import services.Environement;
import services.IMob;

public class IMobImpl implements IMob {

	protected Environement env;
	protected int col;
	protected int  row;
	
	@Override
	public int row() {
		return row;
		
	}

	@Override
	public int col() {
	return col;
	}

	@Override
	public void init(Environement env, int col, int row) {
       this.env=env;
       this.col=col;
       this.row=row;
       env.setCellContent(col, row, this);
		
	}

	@Override
	public Environement env() {
		
		return env;
	}

}
