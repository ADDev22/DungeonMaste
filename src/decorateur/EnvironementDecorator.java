package decorateur;

import enumeration.Cell;
import errors.PostConditionError;
import errors.PreConditionError;
import services.Containable;
import services.Environement;
import services.Map;


public class EnvironementDecorator  implements Environement{

	public Environement delegate;
	
	public EnvironementDecorator(Environement delegate) {
	
		this.delegate=delegate;
	}

	@Override
	public Containable  CellContent(int col, int row) throws PreConditionError {
		return delegate.CellContent(col, row);
	}

	@Override
	public Containable  setCellContent(int col, int row, Containable  m) throws PreConditionError, PostConditionError {
		return delegate.setCellContent(col, row, m);
	}

	@Override
	public int height() {
		return delegate.height();
	}

	@Override
	public int width() {
		return delegate.width();
	}

	@Override
	public Cell cellNature(int x, int y) throws PreConditionError {
		return delegate.cellNature(x, y);
	}

	@Override
	public void init(int w, int h) {
		delegate.init(w, h);
	}

	@Override
	public void openDoor(int x, int y) throws PostConditionError, PreConditionError {
		delegate.openDoor(x, y);
	}

	@Override
	public void closeDoor(int x, int y) throws PostConditionError, PreConditionError {
		delegate.closeDoor(x, y);
	}

	public void checkInvariants() {
		
	}

	@Override
	public void init(Map delegate) throws PostConditionError {
		this.delegate.init(delegate);
		
	}
}