package servicesImplementations;

import enumeration.Cell;
import services.IMap;

public class Map implements IMap{

	public Map() {};
	private int height;
	private int width;
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
      map[x][y]=Cell.DNO;
		
	}

	@Override
	public void closeDoor(int x, int y) {
		 map[x][y]=Cell.DNC;
	}

}
