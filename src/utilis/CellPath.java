package utilis;

public class CellPath {
	public int color;
	public  int xp;
	public int yp;
	
public static CellPath[][] initialise(int w, int h){
	CellPath [][] tab = new CellPath[w][h];
	for(int i=0;i<w;i++) {
		 for(int j=0;j<h;j++) {
			tab[i][j]= new CellPath();
			 
			 }
	}
	
return tab;
}
}
