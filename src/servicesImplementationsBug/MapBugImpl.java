package servicesImplementationsBug;

import enumeration.Cell;
import services.Map;

public class MapBugImpl implements Map{

	public MapBugImpl() {};
	protected int height;
	protected int width;
	protected Cell map[][]; 
	@Override
	public int height() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public int width() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public Cell cellNature(int x, int y) {
		// TODO Auto-generated method stub
		return map[x][y];
	}

	@Override
	public void init(int w, int h) {
        this.height=h;
        this.width=w;	
        this.map =new Cell[w][h];
        for(int i=0; i<w; i++)
        	for(int j=0; j<h; j++)
        		this.map[i][j]=Cell.EMP;
	}

	@Override
	public void openDoor(int x, int y) {
      if( map[x][y] == Cell.DNC ) map[x][y]=Cell.DNO;
      if( map[x][y] == Cell.DWC ) map[x][y]=Cell.DWO;
      return; 
	}

	@Override
	public void closeDoor(int x, int y) {
		  if( map[x][y] == Cell.DNO ) map[x][y]=Cell.DNC;
	      if( map[x][y] == Cell.DWO ) map[x][y]=Cell.DWC;
	      return;
	}

}
