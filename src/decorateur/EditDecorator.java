package decorateur;

import enumeration.Cell;
import services.IEditMap;
import servicesImplementations.EditMap;

public class EditDecorator extends MapDecorator implements IEditMap {

	public EditDecorator(IEditMap delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EditMap getDelegate() {
		return (EditMap) super.getDelegate();
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
