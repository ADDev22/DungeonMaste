package main;

import enumeration.Cell;
import servicesImplementations.EditMap;
import utilis.CellPath;

public class TestEditMap {
	
	public static void main(String args[]) {
	EditMap emap = new EditMap();
	emap.init(4, 4);
	 emap.setNature(3, 2, Cell.EMP);
	 emap.setNature(3, 2, Cell.WLL);
	 System.out.println(emap.isReachable(0, 0, 3, 3));
	System.out.println(emap.cellNature(2, 3));
	emap.isReachable(0, 0, 3, 3);
	 int x=3,y=3;
	 
	 CellPath [][] tab = CellPath.initialise(emap.width(), emap.height());
	 emap.path(0, 0, 3, 3, tab);
 
	 for(int i=0;i<emap.width();i++) {
		 for(int j=0;j<emap.height();j++) {
			System.out.println(tab[i][j].color);
			System.out.println( "("+i+", "+j+")" );
			 }
	}
	
	 while(true)
	 {
		System.out.println( "("+x+", "+y+")" );
		 x=tab[x][y].xp;
		 y=tab[x][y].yp;
		 if( x==0) if(y==0) break;
	 }

	          
	}
	}

	

