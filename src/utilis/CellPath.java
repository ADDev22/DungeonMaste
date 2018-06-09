package utilis;

public class CellPath {
	public Integer  color;
	public  int xp;
	public Integer yp;
	public CellPath() {
		color=new Integer(0);
	}
	
	
public static CellPath[][] initialise(int w, int h){
	CellPath [][] tab = new CellPath[w][h];
	for(int i=0;i<w;i++) {
		 for(int j=0;j<h;j++) {
			tab[i][j]= new CellPath();
			 }
	}
return tab;
}
public static CellPath[][] initi1(CellPath[][] tab){

	for(int i=0;i<tab.length;i++) {
		 for(int j=0;j<tab[0].length;j++) {
			tab[i][j].color=1;
			 
			 }
	}
	
return tab;
}
}
