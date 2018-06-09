package contracts;

import java.awt.Point;
import java.util.ArrayList;

import decorateur.GridDecorateur;
import errors.PostConditionError;
import errors.PreConditionError;
import services.Containable;
import services.EditMap;
import services.Environement;
import services.Grid;
import services.IMob;
import services.Key;
import services.Player;
import services.Treasure;
import servicesImplementations.KeyImpl;

public class GridContract extends GridDecorateur implements Grid {

	public GridContract(Grid deleage) {
		super(deleage);
	}
	
	@Override
	public Environement environement() {
		return delegate.environement();
	}

	@Override
	public Point getin() {
	 return delegate.getin();
	}

	@Override
	public Point getOut() {
	return delegate.getOut();
	}

	@Override
	public ArrayList<Point> getDoorsWithKey() {
		System.out.println(delegate.getDoorsWithKey().size());
		return delegate.getDoorsWithKey();
	}
	@Override
	public ArrayList<Key> keys() {
		System.out.println(delegate.keys().size());
		 return delegate.keys();
	}
	@Override
	public void init(int w, int h) {
        
		if(!(0 <= w && 0 <= h ) )
			throw new PreConditionError("Invalid precondition before init");
		delegate.init(w, h);
		
		if(super.environement() == null) throw new PostConditionError("env should no be null");
		
		}

	@Override
	public void init(String file) {
        
		if((file.equals("")))
			throw new PreConditionError("Invalid precondition before init");
		delegate.init(file);
		
		if(super.environement() == null) throw new PostConditionError("Bad File");
		
		}
	@Override
	public void loadGrid(String fileName,Player p) {
	if(!checkInvariant())
		throw new PreConditionError("Invariant Error");
		
		if(fileName.equals("") )
			throw new PreConditionError("filename should no be null");
		if(p == null )
			throw new PreConditionError("player should no be null");
		if(environement() == null )
			throw new PreConditionError("Here env should not be null");
		
		super.generatedGrid(p);
		
		if(!checkInvariant())
			throw new PreConditionError("Invariant Error");
		
		if(!map().isReady())
		throw new PostConditionError("Map should be Ready");
		if(!map().isReachable(getin().x, getin().y, getOut().x, getOut().y))
			throw new PostConditionError("In & Out should be reachable");
		int nbtTresors=0;
	for(Containable c : containers()) {
		if(c instanceof IMob)
			if(! map().isReachable(getin().x, getin().y,((IMob)c).col() ,((IMob)c).row())) 
				throw new PostConditionError("IMob should be rechable");
	        if(c instanceof Treasure) nbtTresors++;
		
	}
	if(!(nbtTresors >0))
		throw new PostConditionError("Game should contain at least one  Treasure");
	}

	@Override
	public void generatedGrid(Player p) {
		if(p == null )
			throw new PreConditionError("env should no be null");
		if(environement() == null )
			throw new PreConditionError("Here should not be null");
		
		super.generatedGrid(p);
		
		if(!map().isReady())
		throw new PostConditionError("Map should be Ready");
		if(!map().isReachable(getin().x, getin().y, getOut().x, getOut().y))
			throw new PostConditionError("In & Out should be reachable");
		
		if( !(p.Col() == getin().x ))
			throw new PreConditionError("player should be in IN");
		
		if( !(p.Row() == getin().y ))
			throw new PreConditionError("player should be in IN");
			
	for(Containable c : containers()) {
		if(c instanceof IMob)
			if(! map().isReachable(getin().x, getin().y,((IMob)c).col() ,((IMob)c).row())) 
				throw new PostConditionError("IMob should be rechable");
		
		for(Point door : getDoorsWithKey())
			for(Key key : keys()) {
			if (	((KeyImpl) key ).Door_col() == door.x
				   && 
				   ((KeyImpl) key ).Door_row() == door.y )
				break;
			else 
				throw new PostConditionError("Key of the door isn't available");
			}
		
		
	}
		
		
	}
	@Override
	public EditMap map() {
	return super.map();	
	}
  
	public boolean checkInvariant() {
		if(getDoorsWithKey()== null && keys() == null) return true;
		if(getDoorsWithKey()== null && keys() !=null) return false;
		if(getDoorsWithKey() !=null && keys() ==null) return false;
		return getDoorsWithKey().size() == keys().size();
	}
}
