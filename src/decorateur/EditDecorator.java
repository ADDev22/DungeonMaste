package decorateur;

import enumeration.Cell;
import services.EditMap;
import servicesImplementations.EditMapImpl;

public class EditDecorator extends MapDecorator implements EditMap {

	public EditDecorator(EditMap delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EditMapImpl getDelegate() {
		return (EditMapImpl) super.getDelegate();
	}
	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		return getDelegate().isReachable(x1, y1, x2, y2);
	}

	@Override
	public boolean isReady() {
	      return getDelegate().isReady();
	}

	@Override
	public void setNature(int x, int y, Cell nat) {
		getDelegate().setNature(x, y, nat);
		
	}

}
