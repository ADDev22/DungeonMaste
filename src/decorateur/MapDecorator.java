package decorateur;

import enumeration.Cell;
import services.Map;

public class MapDecorator extends AbstractDecorator implements Map  {
	
	protected Map delegate;

	

	protected Map getDelegate() {
		return (Map)delegate;
	}
	
	public MapDecorator(Map delegate) {
		super();
		this.delegate = delegate;
	}

	public int height() {
		return delegate.height();
	}

	public int width() {
		return delegate.width();
	}

	public Cell cellNature(int x, int y) {
		return delegate.cellNature(x, y);
	}

	public void init(int w, int h) {
		delegate.init(w, h);
	}

	public void openDoor(int x, int y) {
		delegate.openDoor(x, y);
	}

	public void closeDoor(int x, int y) {
		delegate.closeDoor(x, y);
	}

	@Override
	protected void checkInvariants() {
		
	}


}
