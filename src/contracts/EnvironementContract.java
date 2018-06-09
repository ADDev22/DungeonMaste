package contracts;

import decorateur.EnvironementDecorator;
import enumeration.Cell;
import errors.*;
import services.Containable;
import services.Environement;
import services.Key;
import services.Map;
import servicesImplementations.EnvironementImpl;

public class EnvironementContract extends EnvironementDecorator implements Environement {
	
	public EnvironementContract(Environement delegate) {
		super(delegate);
	}

	@Override
	public int height() {
		return super.height();
	}

	@Override
	public int width() {
		return super.width();
	}

	@Override
	public Cell cellNature(int x, int y) throws PreConditionError {
		return delegate.cellNature(x, y);
	}

	@Override
	public void init(int w, int h) {
		//this init method shouldn't be used
		super.init(w, h);		
	}

	@Override
	public void init(Map delegate) throws PreConditionError {
		if(delegate == null) throw new PreConditionError("Map is null");
		if(delegate instanceof MapContract==false && delegate instanceof EditMapContract ==false )
		throw new PreConditionError("the delegate should be a Map contract or an EditMap contract");
		super.init(delegate);
	}

	@Override
	public void openDoor(int x, int y) throws PostConditionError, PreConditionError {
		super.openDoor(x, y);
	}

	@Override
	public void closeDoor(int x, int y) throws PostConditionError, PreConditionError {
		
		// les autres pré et posts sont vérifiées par le contract du delegate.
		//pre 
				if(CellContent(x, y)!= null)
					throw new PreConditionError("cellcontent isn't null");
				
				super.closeDoor(x, y);
     }

	@Override
	public Containable CellContent(int col, int row) throws PreConditionError {
		
		if(!(0<=col)) throw new PreConditionError("x<0");
		if(! (col < width()) )throw new PreConditionError("x >= width()");
		if(!(0<=row)) throw new PreConditionError("y<0");
		if(! (row < height())) throw new PreConditionError("y >= height()");
		
		return super.CellContent(col, row);
		
	}

	@Override
	public Containable setCellContent(int col, int row, Containable m) throws PreConditionError, PostConditionError {
		
		//pré
		
		if(!(0<=col)) throw new PreConditionError("x<0");
		if(! (col < width()) )throw new PreConditionError("x >= width()");
		if(!(0<=row)) throw new PreConditionError("y<0");
		if(! (row < height())) throw new PreConditionError("y >= height()");
		
		//capture
		
		Containable content[][]=new Containable[width()][height()];
		for(int i=0;i<width();i++) {
		for(int j=0;j<height();j++) {
			content[i][j]=((EnvironementImpl)delegate).MapContent[i][j];	
		}
		}
		
		if(cellNature(col, row) == Cell.WLL || cellNature(col, row) == Cell.DWC || cellNature(col, row) == Cell.DNC || ( (CellContent(col, row) != null ) && m !=null )) { 
			if( (cellNature(col, row) == Cell.DWC || cellNature(col, row) == Cell.DNC) && m!= null && ! (m instanceof Key) )
			throw new PreConditionError("you can only put a key in a closed door");
			if(cellNature(col, row) == Cell.WLL || ((CellContent(col, row) != null ) && m !=null)) {
				throw new PreConditionError("cell isn't empty or is already occupied");			
			}
		
		}
		checkInvariants();
		Containable mob = super.setCellContent(col, row, m);
		checkInvariants();
		
		//post
		
		if(CellContent(col, row)!= m) {throw new PostConditionError("setcontent failed"); }
		
		for(int i=0;i<width();i++) {
		for(int j=0;j<height();j++) {
		if(i!=col && j!=row) {	
			if(content[i][j] !=((EnvironementImpl)delegate).MapContent[i][j]) { throw new PostConditionError("other cell contents has changed"); };	
		}
			}
		}
		return mob;
	}

	public void checkInvariants() {
		
	}
	
	
	
	
	
}
