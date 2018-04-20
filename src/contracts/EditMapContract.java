package contracts;

import enumeration.Cell;
import errors.InvariantError;
import errors.PostConditionError;
import errors.PreConditionError;
import services.IEditMap;

public class EditMapContract extends MapContract implements IEditMap {
     
	
	protected IEditMap delegate;
    public EditMapContract(IEditMap map) {
    	super(map);
		this.delegate=map;
	}
	@Override
	protected IEditMap getDelegate() {
		return this.delegate;
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
            //pre
		if(!(0<=x)) throw new PreConditionError("x<0");
		if(! (x<=height()) ) throw new PreConditionError("x > height()");
		if(!(0<=y)) throw new PreConditionError("y<0");
		if(! (y<=width()) ) throw new PreConditionError("y > width()");

        //inv@pre
		checkInvariants();
    
        	//captures
		Cell natCell_atPre = cellNature(x, y);
		Cell[][]  cell_atPre= new Cell[width()][height()];
		for(int i=0; i <width() ;i++)
			for(int j=0;j<height();j++)
				cell_atPre[i][j]=cellNature(i, j);
         //run
         getDelegate().setNature(x, y, nat);
		
        //inv@post
		checkInvariants();
		
		//post
          
          for(int i=0; i < width();i++) {
		 for(int j=0;j<height();j++) {
			 if(i!=x && j!=y) {
		   if( !(cell_atPre[i][j]==cellNature(i, j)) )
			   throw new PostConditionError("Nature des autres cases ne devrait pas changé");
		   }
					}
		}
        
	}

	public void checkVariants() {
		boolean b1=true,b2=true,b3=true,b4=true;
		boolean realIsReady =isReady();
		boolean isReachable;
		
        if(!(isReady())) throw new InvariantError("IsReady");
        
		int xIN=0,yIN=0, xOUT=0, yOUT=0;
		int countIn=0,countOut=0;
         for(int i=0; i <this.width();i++) {
					 for(int j=0;j<this.height();j++) {
					  if(super.cellNature(i, j)==Cell.IN) {xIN=i; yIN=j;countIn+=1;}
                      if(super.cellNature(i, j)==Cell.OUT) {xOUT=i; yOUT=j;countOut+=1;}
					 }
   			    }
         if(countIn != 1 &&  countIn != 1)  b1 = false;
         
         if( !(isReachable(xIN,yIN, xOUT, yOUT)) )  b2 = false;    
         
       
         for(int i=0; i <this.width();i++) {
         for(int j=0;j<this.height();j++) {
			  if(( i!= xIN && j != yIN) && (i!= xOUT && j != yOUT) ) {
				  if( (cellNature(i, j) == Cell.IN) ||( cellNature(i, j )==Cell.OUT ) )
					  b3=false;		  
			}
         }
         }
	 
	    for(int i=0; i <this.width();i++) {
	         for(int j=0;j<this.height();j++) {
	        	 
	        	 if(cellNature(i,j)== Cell.DNO || cellNature(i,j)== Cell.DNC) {
						
						if(i-1 >= 0)
							if(cellNature (i-1,j) != Cell.WLL) {b4=false; break;}
						if(i+1 < width())
							if(cellNature(i+1,j) != Cell.WLL) {b4=false; break;}
						
						if(j-1 >= 0)
							if(cellNature(i,j-1 )!= Cell.EMP) {b4=false; break;}
						if(j+1 < height())
							if(cellNature(i,j+1) != Cell.EMP) {b4=false; break;}
							
					}
	                
					if(cellNature (i,j )== Cell.DWO || cellNature(i,j)== Cell.DWC) {
						
						if(i-1 >= 0)
							if(cellNature(i-1,j) != Cell.EMP) {b4=false; break;}
						if(i+1 < height())
							if(cellNature (i+1,j)!= Cell.EMP) {b4=false; break;}
						
						if(j-1 >= 0)
							if(cellNature(i,j-1) != Cell.WLL) {b4=false; break;}
						if(j+1 < height())
							if(cellNature(i,j+1 ) != Cell.WLL) {b4=false; break;}
						
	         }
			
	         }
	         
	         if(b4==false ) break;
	         
	      }
	    
	    if(isReady() != (b1&&b2&&b3&&b4)) throw new InvariantError("IsReady");
	

 }
}