package servicesImplementations;

import enumeration.Cell;
import services.IEditMap;
import utilis.CellPath;

public class EditMap extends Map implements IEditMap{

	
	 public EditMap() {
		 super();
	}
	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {		
		
		CellPath [][] tab = CellPath.initialise(this.width(), this.height());
		path(x1, y1, x2, y2,tab);
		if(tab[x2][y2].color== 1)
			return true;
		return false;
	}

	@Override
	public boolean isReady() {
		
		int nb_E=0;
		int nb_S=0;
		int w = this.width(), h =this.height();
		for(int i=0; i< w; i++)
			for(int j=0; j< h; j++)
			{
				if(this.map[i][j]== Cell.IN) 
					nb_E+=1;
				if(this.map[i][j]== Cell.OUT) 
					nb_S+=1;
				if(nb_E >1 || nb_S > 1) return false;
				
				if(this.map[i][j]== Cell.DNO || this.map[i][j]== Cell.DNC) {
					
					if(i-1 >= 0)
						if(this.map[i-1][j] != Cell.WLL) return false;
					if(i+1 < w)
						if(this.map[i+1][j] != Cell.WLL) return false;
					
					if(j-1 >= 0)
						if(this.map[i][j-1] != Cell.EMP) return false;
					if(j+1 < h)
						if(this.map[i][j+1] != Cell.EMP) return false;
						
				}
                
				if(this.map[i][j]== Cell.DWO || this.map[i][j]== Cell.DWC) {
					
					if(i-1 >= 0)
						if(this.map[i-1][j] != Cell.EMP) return false;
					if(i+1 < h)
						if(this.map[i+1][j] != Cell.EMP) return false;
					
					if(j-1 >= 0)
						if(this.map[i][j-1] != Cell.WLL) return false;
					if(j+1 < h)
						if(this.map[i][j+1] != Cell.WLL) return false;
					
			
						
				}
				if(nb_E + nb_S !=2) return false;
				
			}
		return true;
	}

	@Override
	public void setNature(int x, int y, Cell nat) {
	   super.map[x][y]=nat;	
	}
	
	public void path(int x1, int y1, int x2, int y2, CellPath tab[][]) {
	    tab[x1][y1].color=1;
		 {
			if(x1-1 >= 0)
				if( tab[x1-1][y1].color!=1 && this.map[x1-1][y1]!=Cell.WLL ) {
					tab[x1-1][y1].xp=x1;
					tab[x1-1][y1].yp=y1;
				path(x1-1, y1, x2, y2,tab);
				}
			if(x1+1 < this.height()) {
				if(tab[x1+1][y1].color!=1 && this.map[x1+1][y1]!=Cell.WLL ) { 
					tab[x1+1][y1].xp=x1;
					tab[x1+1][y1].yp=y1;
					path(x1+1, y1, x2, y2,tab);
					}}
			if(y1-1 >= 0)
				if(tab[x1][y1-1].color!=1 &&  this.map[x1][y1-1]!=Cell.WLL) {
					tab[x1][y1-1].xp=x1;
					tab[x1][y1-1].yp=y1;
					path(x1, y1-1, x2, y2,tab);}
			
			if(y1+1 <  this.width())
				if(tab[x1][y1+1].color!=1 && this.map[x1][y1+1]!=Cell.WLL) {
					tab[x1][y1+1].xp=x1;
					tab[x1][y1+1].yp=y1;
					path(x1, y1+1, x2, y2,tab);
					}	
		}
		
	}

}
