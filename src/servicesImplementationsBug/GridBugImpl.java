package servicesImplementationsBug;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import contracts.CowContract;
import contracts.EditMapContract;
import contracts.EnvironementContract;
import contracts.KeyContract;
import contracts.TreasureContract;
import enumeration.Cell;
import enumeration.Dir;
import services.Containable;
import services.Cow;
import services.EditMap;
import services.Environement;
import services.Grid;
import services.Key;
import services.Player;
import services.Treasure;
import servicesImplementations.CowImpl;
import servicesImplementations.EditMapImpl;
import servicesImplementations.EnvironementImpl;
import servicesImplementations.KeyImpl;
import servicesImplementations.TreasureImpl;
import utilis.CellPath;

public class GridBugImpl implements Grid{

	 
	
	 public GridBugImpl() {
		 super();
	}


	 public Environement env;
	 Point in;
	 Point out;
	 public EditMap map;
	 ArrayList<Containable> containers;
	@Override
	public void loadGrid(String fileName, Player p) {
	
		 try {
				
				FileReader f;
				f =new FileReader(new File(fileName));
				BufferedReader bf = new  BufferedReader(f);
				String []lc =bf.readLine().split(":");
				
				int x ,y;
				Cell cell;
				String cellType;
				String line="";
				while((line=bf.readLine()) !=null)
				{
					String [] toks = line.split(":");
					x=Integer.parseInt(toks[0]);
					y=Integer.parseInt(toks[1]);
					cellType=toks[2];
					cell=conv2Cell(cellType);
					if(cellType.equals("IN"))
						in=new Point(x, y);
					if(cellType.equals("OUT"))
						out=new Point(x, y);
					map.setNature(x, y, cell);
						
				}
				placedContainable(p);
				
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
	}
	@Override
	public void generatedGrid(Player p1) {
		 ArrayList<Point> pts =new ArrayList<>();
		Random rand_x = new Random();
		Random rand_y = new Random();
		
		in =new Point(rand_x.nextInt(map.width()), 0);
		out =new Point(rand_x.nextInt(map.width()), map.height()-1);
		Point tresor =new Point(rand_x.nextInt(map.width()), rand_y.nextInt(map.height()));
		pts.add(out);
		pts.add(tresor);
		
		 map.setNature(in.x, in.y, Cell.IN);
		 map.setNature(out.x, out.y, Cell.OUT);
		 
		 Cell [] cells =Cell.values();
		 cells[Cell.IN.ordinal()]=Cell.EMP;
		 cells[Cell.OUT.ordinal()]=Cell.EMP;
		 Point lastP= new Point();
		 Cell cur;
		 HashSet<Point> readyPts= new HashSet<>();
		 readyPts.add(in); readyPts.add(out);
		 int ierations = 0;
		 boolean b= isAllRecheable(pts);
		// System.out.println(b);
		 while(map.isReady() && isAllRecheable(pts) && ierations <=20000)
		 {
			Point p = new Point(rand_x.nextInt(map.width()), rand_y.nextInt(map.height()));
	         if(!readyPts.contains(p)) {
					 if(in!=p && out != p ) {
						 cur=cells[rand_x.nextInt(cells.length)];
						 if(cur == Cell.DNC || cur == Cell.DNO) {
							 if(p.x-1>=0 && !in.equals(new Point(p.x-1, p.y)) && !out.equals(new Point(p.x-1, p.y)) && !readyPts.contains(new Point(p.x-1, p.y)))
								 if(p.x+1< map.width() && !in.equals(new Point(p.x+1, p.y)) && !out.equals(new Point(p.x+1, p.y))  && !readyPts.contains(new Point(p.x+1, p.y)))
									 if(p.y-1>=0 && !in.equals(new Point(p.x, p.y-1)) && !out.equals(new Point(p.x, p.y-1))  && !readyPts.contains(new Point(p.x, p.y-1)))
										 if(p.y+1< map.height() && !in.equals(new Point(p.x, p.y+1)) && !out.equals(new Point(p.x, p.y+1)) && !readyPts.contains(new Point(p.x, p.y+1)))
				
										
								 {
									 map.setNature(p.x-1, p.y, Cell.WLL);
									 if(!isAllRecheable(pts)) {
										 map.setNature(p.x-1, p.y, Cell.EMP);
										 continue;
									 }
									 map.setNature(p.x+1, p.y, Cell.WLL);
									 if(!isAllRecheable(pts))
									 {
										 map.setNature(p.x+1, p.y, Cell.EMP);
										 map.setNature(p.x-1, p.y, Cell.EMP);
										continue;
									 }
									 
									 readyPts.add(new Point(p.x-1, p.y));
									 readyPts.add(new Point(p.x+1, p.y));
									 readyPts.add(new Point(p.x, p.y-1));
									 readyPts.add(new Point(p.x, p.y+1));
									 map.setNature(p.x, p.y, cur);
								 }
							 }
						 else if(cur == Cell.DWC || cur == Cell.DWO) {
							 if(p.x-1>=0 && !in.equals(new Point(p.x-1, p.y)) && !out.equals(new Point(p.x-1, p.y)) && !readyPts.contains(new Point(p.x-1, p.y)))
								 if(p.x+1< map.width() && !in.equals(new Point(p.x+1, p.y)) && !out.equals(new Point(p.x+1, p.y))&&!readyPts.contains(new Point(p.x+1, p.y)))
									 if(p.y-1>=0 && !in.equals(new Point(p.x, p.y-1)) && !out.equals(new Point(p.x, p.y-1))&&!readyPts.contains(new Point(p.x, p.y-1)))
										 if(p.y+1< map.height() && !in.equals(new Point(p.x, p.y+1)) && !out.equals(new Point(p.x, p.y+1))&& !readyPts.contains(new Point(p.x, p.y+1)))
				
										
								 {
											 map.setNature(p.x, p.y-1, Cell.WLL);
											 if(!isAllRecheable(pts)) {
												 map.setNature(p.x, p.y-1, Cell.EMP);
												 continue;
											 }
											 map.setNature(p.x, p.y+1, Cell.WLL);
											 if(!isAllRecheable(pts))
											 {
												 map.setNature(p.x, p.y+1, Cell.EMP);
												 map.setNature(p.x, p.y-1, Cell.EMP);
												continue;
											 }
									 readyPts.add(new Point(p.x-1, p.y));
									 readyPts.add(new Point(p.x+1, p.y));
									 readyPts.add(new Point(p.x, p.y-1));//map.setNature(p.x, p.y-1, Cell.WLL);
									 readyPts.add(new Point(p.x, p.y+1));//map.setNature(p.x, p.y+1, Cell.WLL);
									 map.setNature(p.x, p.y, cur);
								 }
							 }
						 lastP.x=p.x;
						 lastP.y=p.y;
			            }
			 }
	         ierations++;
	         if(!map.isReady()) map.setNature(lastP.x, lastP.y, Cell.EMP);
		 }	
	   // System.out.println(ierations);
	  // System.out.println(getDoors());
	   placedContainable(p1);
		   
	}
	private Cell conv2Cell(String cell)
	{
		if(cell.equals("EMP")) return Cell.EMP;
		if(cell.equals("DNC")) return Cell.DNC;
		if(cell.equals("DNO")) return Cell.DNO;
		if(cell.equals("DWC")) return Cell.DWC;
		if(cell.equals("DWO")) return Cell.DWO;
		if(cell.equals("IN"))  return Cell.IN;
		if(cell.equals("OUT")) return Cell.OUT;
		if(cell.equals("WLL")) return Cell.WLL;
		return Cell.EMP;
	}
	
	private boolean isAllRecheable(ArrayList<Point> pts ) {
		 for(Point p : pts) {
			if(!map.isReachable(in.x, in.y, p.x, p.y))
				return false;
			}
		 return true;
		
	}
   
	private Point getLocationContainaible() {
		Random rand_x = new Random();
		Random rand_y = new Random();
		
		Point p=null;
		int iter =0;
		while(iter <550)
		{
			p=new Point(rand_x.nextInt(map.width()), rand_y.nextInt(map.height()));
			if(map.isReachable(in.x, in.y, p.x, p.y) && env.CellContent(p.x, p.y) == null && 
					(map.cellNature(p.x, p.y) != Cell.DNC && map.cellNature(p.x, p.y) != Cell.DWC) ) {
				return p;
			}
			iter++;
		}
		return null;	
	}
	
	@Override
	public Environement environement() {
	 return env;
	}

	@Override
	public Point getin() {
		return in;
	}

	@Override
	public Point getOut() {
	return out;
	}

	@Override
	public void init(int w, int h) {
		map = new EditMapContract(new EditMapImpl());
		map.init(w, h);
		env=new EnvironementContract(new EnvironementImpl());
		env.init(map);
		containers=new ArrayList<>();
		
	}
	private ArrayList<Point> getDoors() {
		
		//System.out.println(x2);
		ArrayList<Point> doors =new ArrayList<>();
		Point[][]  parents=new Point[map.width()][map.height()];
		Queue<Point> files = new ArrayDeque<>();
		CellPath[][] tab=CellPath.initialise(map.width(), map.height());
		files.add(new Point(in.x, in.y));
		tab[in.x][in.y].color=1;
		while(!files.isEmpty())
		{
			//System.out.println(files.size());
			Point cur = files.remove();
			if(cur.equals(new Point(out.x, out.y))) break;
			//System.out.println("oj");
		if (cur.x-1 >= 0)
			if(tab[cur.x-1][cur.y].color!=1 && map.cellNature(cur.x-1, cur.y)!=Cell.WLL) {
				 files.add(new Point(cur.x-1, cur.y));
				 tab[cur.x-1][cur.y].color=1;
				 parents[cur.x-1][cur.y] =  new Point(cur);
			}

		if (cur.x+1 < map.width())
			if(tab[cur.x+1][cur.y].color!=1 && map.cellNature(cur.x+1, cur.y)!=Cell.WLL) {
					files.add(new Point(cur.x+1, cur.y));
					tab[cur.x+1][cur.y].color=1;
					parents[cur.x+1][cur.y] =  new Point(cur);
			}

		if (cur.y - 1 >= 0)
			if(tab[cur.x][cur.y-1].color!=1 && map.cellNature(cur.x, cur.y-1)!=Cell.WLL) {
					files.add(new Point(cur.x, cur.y-1));
					tab[cur.x][cur.y-1].color=1;
					parents[cur.x][cur.y-1] =  new Point(cur);
			}
		
		if (cur.y + 1 < map.height())
			if(tab[cur.x][cur.y+1].color!=1 && map.cellNature(cur.x, cur.y+1)!=Cell.WLL) {
					files.add(new Point(cur.x, cur.y+1));
					tab[cur.x][cur.y+1].color=1;
					parents[cur.x][cur.y+1] =  new Point(cur);
			}
	   }
		
	
		Point p=new Point(out);
		for(int i=0; i< parents.length; i++)
		{
			if(parents[p.x][p.y] == null) {break;}
			if(map.cellNature(p.x, p.y) == Cell.DNC || map.cellNature(p.x, p.y) == Cell.DWC)
				doors.add(p);
			p=parents[p.x][p.y];
		}
		return doors;
		
	}
	
	public void placedContainable(Player p1)
	{

		Point p=null;
		   
	    p1.init(env, in.x,in.y, Dir.E,10);
	    containers.add(p1);
	   
		   
		   ArrayList<Point> doors = getDoors();
		   if(doors.size()!=0) {
		      for(int i= 0; i< doors.size(); i++) {
		    	  KeyContract key = new KeyContract(new KeyImpl());
			    if( (p=getLocationContainaible()) == null) {
				   generatedGrid(p1);
				   return;
				  }
			   else {
		          key.init(doors.get(i).x, doors.get(i).y, p.x, p.y, env);
		          env.setCellContent(doors.get(i).x, doors.get(i).y, new KeyImpl());
		          containers.add(key);
			   }
			   }
		   }
		   Cow cow =new CowContract(new CowImpl());
		    p= getLocationContainaible();
		    if(p!=null) {
		    	cow.init(env, p.x, p.y, Dir.S,3);
		    	containers.add(cow);
		    	}
		    
		   
	}

	@Override
	public ArrayList<Containable> containers() {
		return containers;
	}

	@Override
	public EditMap map() {
		return map;
	}

	@Override
	public void init(String file) {
		FileReader f;
		try {
		f =new FileReader(new File(file));
		BufferedReader bf = new  BufferedReader(f);
		String []lc =bf.readLine().split(":");
		int width = Integer.parseInt(lc[0]);
		int height =Integer.parseInt(lc[1]);
		init(width, height);
		}
		catch(Exception e){
			
		}
		
	}
	@Override
	public ArrayList<Point> getDoorsWithKey() {
		ArrayList<Point> e= new ArrayList<Point>();
		e.add(new Point(1,2));
		return e;
	}
	@Override
	public ArrayList<Key> keys() {
		ArrayList<Key> e= new ArrayList<Key>();
		return e;
	}
}

