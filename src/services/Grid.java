package services;

import java.awt.Point;
import java.util.ArrayList;

public interface Grid /* extends /* refine  EditMap */ {
	
	public Environement environement();
	public Point getin();
	public Point getOut();
	public ArrayList<Containable> containers();
	public ArrayList<Point> getDoorsWithKey();
	public ArrayList<Key> keys();
	public EditMap map();
	// ===========CONSTRUCTORS==============
	
	void init(int w, int h);
	void init(String file);
	
	// ===========OPERATORS==============
	
	/**
	 * pre : player != null && file -!= ""
	 * 
	 * post :
	 *     player.col() == getIn().x
	 *     player.Row() == getIn().y
	 *     
	 *     map().isReachable(getIn().x, getIn().y,  getOut().x, getOut().y)
	 *     
	 *     map.isReady()
	 *     
	 *     forall door in getDoorsWithKey() exists key  in keys(),
	 *            door.x = key.col() && door.y = key.row()
	 *            
	 *     forall key  in keys(),
	 *            map().isReachable(getIn().x, getIn().y,  key.x, key.y)
	 *    
	 */
	
	public void loadGrid(String fileName, Player p);
	
	/**
	 * pre : player != null
	 * 
	 * post :
	 *     player.col() == getIn().x
	 *     player.Row() == getIn().y
	 *     
	 *     map().isReachable(getIn().x, getIn().y,  getOut().x, getOut().y)
	 *     
	 *     map.isReady()
	 *     
	 *     forall door in getDoorsWithKey() exists key  in keys(),
	 *            door.x = key.col() && door.y = key.row()
	 *            
	 *     forall key  in keys(),
	 *            map().isReachable(getIn().x, getIn().y,  key.x, key.y)
	 *    
	 */
	public void generatedGrid( Player p);


	// ========INVARIANTS==================
	/**
	 *   getDoorsWithKey().size() == keys.size()
	 * 
	 * 
	 */




}
