package decorateur;

import enumeration.Cell;
import services.IMap;

public class MapDecorator extends AbstractDecorator implements IMap  {
	
	protected IMap delegate;

	

	protected IMap getDelegate() {
		return (IMap)delegate;
	}
	
	public MapDecorator(IMap delegate) {
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
