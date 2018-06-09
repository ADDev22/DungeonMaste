package decorateur;

import java.awt.Point;
import java.util.ArrayList;

import services.Containable;
import services.EditMap;
import services.Environement;
import services.Grid;
import services.Key;
import services.Player;

public class GridDecorateur implements Grid {
	
	public Grid delegate;

	public GridDecorateur(Grid delegate)
	{
		this.delegate=delegate;
	}
	public Environement environement() {
		return delegate.environement();
	}

	public Point getin() {
		return delegate.getin();
	}

	public Point getOut() {
		return delegate.getOut();
	}

	public void init(int w, int h) {
		delegate.init(w, h);
	}

	public void loadGrid(String fileName, Player p) {
		delegate.loadGrid(fileName,p);
	}

	public void generatedGrid(Player p) {
		delegate.generatedGrid(p);
	}
	@Override
	public ArrayList<Containable> containers() {
		return delegate.containers();
	}
	@Override
	public EditMap map() {
		return delegate.map();
	}
	@Override
	public void init(String file) {
		delegate.init(file);
		
	}
	@Override
	public ArrayList<Point> getDoorsWithKey() {
		return delegate.getDoorsWithKey();
	}
	@Override
	public ArrayList<Key> keys() {
		 return delegate.keys();
	}


	

}
