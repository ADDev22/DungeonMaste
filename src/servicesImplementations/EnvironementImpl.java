package servicesImplementations;


import enumeration.Cell;
import errors.*;
import services.Containable;
import services.Environement;
import services.Map;

public class EnvironementImpl  implements Environement{

	public Containable  MapContent[][];
	private Map delegate;


	@Override
	public void init(int w, int h) {
		throw new Error("you should use the init with delegate");
		
	}

	
	@Override
	public Containable  CellContent(int col, int row) {
		return MapContent[col][row];
	}

	@Override
	public Containable setCellContent(int col, int row, Containable m) {
		Containable  mob=MapContent[col][row];
		MapContent[col][row]=m;
		return mob;
	}


	@Override
	public void init(Map delegate) {
		this.delegate=delegate;
		MapContent = new Containable[delegate.width()][delegate.height()]; 	
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
	public void openDoor(int x, int y) throws PostConditionError, PreConditionError {
		delegate.openDoor(x, y);
	}


	@Override
	public void closeDoor(int x, int y) throws PostConditionError, PreConditionError {
		delegate.closeDoor(x, y);
	}

	
	
}
