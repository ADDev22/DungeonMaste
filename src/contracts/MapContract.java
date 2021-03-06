package contracts;

import decorateur.MapDecorator;
import enumeration.Cell;
import errors.PostConditionError;
import errors.PreConditionError;
import services.Map;
import servicesImplementations.MapImpl;

public class MapContract extends MapDecorator implements Map {

	public MapContract(Map delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

	public MapContract() {
		super(new MapImpl());

	}
	
	// ===========OBSERVATORS==============

	@Override
	public int height() {
		//run
		return super.height();
		
	}

	@Override
	public int width() {
	    // run
		return super.width();
	}

	@Override
	public Cell cellNature(int x, int y) {
		//pre
		if(!(0<=x)) throw new PreConditionError("x<0");
		if(! (x< width()) )throw new PreConditionError("x > width()");
		if(!(0<=y)) throw new PreConditionError("y<0");
		if(! (y< height())) throw new PreConditionError("y > height()");
		
		return super.cellNature(x, y);
	}

	@Override
	public void init(int w, int h) {
		//pre
		if(!(0<=h)) throw new PreConditionError("h<0");
		if(!(0<=w)) throw new PreConditionError("w<0");
		
		//inv@pre
		super.checkInvariants();
		
		super.init(w, h);
		
		//inv@post
		super.checkInvariants();
		
		//post
		if( !(height()==h)) throw new PreConditionError("heigh() != h");
		if( !(width()==w)) throw new PreConditionError("width() !=w ");
		
		
	}

	@Override
	public void openDoor(int x, int y) {
	
		//pre
		if(! (cellNature(x, y) == Cell.DWC || cellNature(x, y) == Cell.DNC) )
			throw new PreConditionError("Nature de Cell devrait etre  DNC ou DWC");
		//inv@pre
		super.checkInvariants();
		
		//captures
		Cell natCell_atPre = cellNature(x, y);
		Cell[][]  cell_atPre= new Cell[width()][height()];
		
		for(int j=0;j<width();j++)
		for(int i=0; i < height();i++)
			
				cell_atPre[j][i]=cellNature(j, i);
		
		//run
		super.openDoor(x, y);
		
		//inv@post
		checkInvariants();
		
		//post
	    if(natCell_atPre == Cell.DNC) 
	    	if(! (cellNature(x, y)==Cell.DNO)) throw new PostConditionError("Nature du Cell devrait etre DNO");
	    if(natCell_atPre == Cell.DWC) 
	    	if(! (cellNature(x, y)==Cell.DWO)) throw new PostConditionError("Nature du Cell devrait etre DWO");
		
	    for(int j=0;j<width();j++) {
			   for(int i=0; i < height();i++)	  {
					 if(j!=x && i!=y) {
				   if( !(cell_atPre[j][i]==cellNature(j, i)) )
					   throw new PostConditionError("Nature des autres cases ne devrait pas changé");
				      }
				 }
		    }
	}

	@Override
	public void closeDoor(int x, int y) {
		
		//pre
				if(! (cellNature(x, y) == Cell.DWO || cellNature(x, y) == Cell.DNO) )
					throw new PreConditionError("Nature de Cell devrait etre  DNO ou DWO");
				//inv@pre
				super.checkInvariants();
				
				//captures
				Cell natCell_atPre = cellNature(x, y);
				Cell[][]  cell_atPre= new Cell[width()][height()];
				
				for(int j=0;j<width();j++)
				for(int i=0; i < height();i++)
					
						cell_atPre[j][i]=cellNature(j, i);
				
				//run
				super.closeDoor(x, y);
				
				//inv@post
				checkInvariants();
				
				//post
			    if(natCell_atPre == Cell.DNO) 
			    	if(! (cellNature(x, y)==Cell.DNC)) throw new PostConditionError("Nature du Cell devrait etre DNC");
			    if(natCell_atPre == Cell.DWO) 
			    	if(! (cellNature(x, y)==Cell.DWC)) throw new PostConditionError("Nature du Cell devrait etre DWC");
				
			   for(int j=0;j<width();j++) {
				   for(int i=0; i < height();i++)	  {
						 if(j!=x && i!=y) {
					   if( !(cell_atPre[j][i]==cellNature(j, i)) )
						   throw new PostConditionError("Nature des autres cases ne devrait pas changé");
					      }
					 }
   			    }
     }
}
