package servicesImplementations;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Queue;

import enumeration.Cell;
import services.EditMap;
import utilis.CellPath;

public class EditMapImpl extends MapImpl implements EditMap {

	CellPath[][] tab;

	public EditMapImpl() {
		super();
	}

	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {

		tab = CellPath.initialise(width(), height());
		return path(x1, y1, x2, y2);
	}

	@Override
	public boolean isReady() {

		int nb_E = 0;
		int nb_S = 0;
		int xIN = 0, yIN = 0;
		int xOUT = 0, yOUT = 0;
		int w = this.width(), h = this.height();
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if (this.map[i][j] == Cell.IN) {
					nb_E += 1;
					xIN = i;
					yIN = j;
				}
				if (this.map[i][j] == Cell.OUT) {
					nb_S += 1;
					;
					xOUT = i;
					yOUT = j;
				}
				if (nb_E > 1 || nb_S > 1)
					return false;

				if (this.map[i][j] == Cell.DNO || this.map[i][j] == Cell.DNC) {

					if (i - 1 >= 0)
						if (this.map[i - 1][j] != Cell.WLL)
							return false;
					if (i + 1 < w)
						if (this.map[i + 1][j] != Cell.WLL)
							return false;

					if (j - 1 >= 0)
						if (this.map[i][j - 1] != Cell.EMP)
							return false;
					if (j + 1 < h)
						if (this.map[i][j + 1] != Cell.EMP)
							return false;

				}

				if (this.map[i][j] == Cell.DWO || this.map[i][j] == Cell.DWC) {

					if (i - 1 >= 0)
						if (this.map[i - 1][j] != Cell.EMP)
							return false;
					if (i + 1 < h)
						if (this.map[i + 1][j] != Cell.EMP)
							return false;

					if (j - 1 >= 0)
						if (this.map[i][j - 1] != Cell.WLL)
							return false;
					if (j + 1 < h)
						if (this.map[i][j + 1] != Cell.WLL)
							return false;

				}
			}

		}
		if (nb_E + nb_S != 2)
			return false;
		return isReachable(xIN, yIN, xOUT, yOUT);
	}

	@Override
	public void setNature(int x, int y, Cell nat) {
		super.map[x][y] = nat;
	}

	public boolean path(int x1, int y1, int x2, int y2) {
		//System.out.println(x2);
		Queue<Point> files = new ArrayDeque<>();
		tab=CellPath.initialise(width(), height());
		files.add(new Point(x1, y1));
		tab[x1][y1].color=1;
		while(!files.isEmpty())
		{
			//System.out.println(files.size());
			Point cur = files.remove();
			if(cur.equals(new Point(x2, y2))) return true;
			//System.out.println("oj");
		if (cur.x-1 >= 0)
			if(tab[cur.x-1][cur.y].color!=1 && cellNature(cur.x-1, cur.y)!=Cell.WLL) {
				 files.add(new Point(cur.x-1, cur.y));
				 tab[cur.x-1][cur.y].color=1;
			}

		if (cur.x+1 < width())
			if(tab[cur.x+1][cur.y].color!=1 && cellNature(cur.x+1, cur.y)!=Cell.WLL) {
					files.add(new Point(cur.x+1, cur.y));
					tab[cur.x+1][cur.y].color=1;
			}

		if (cur.y - 1 >= 0)
			if(tab[cur.x][cur.y-1].color!=1 && cellNature(cur.x, cur.y-1)!=Cell.WLL) {
					files.add(new Point(cur.x, cur.y-1));
					tab[cur.x][cur.y-1].color=1;
			}
		
		if (cur.y + 1 < height())
			if(tab[cur.x][cur.y+1].color!=1 && cellNature(cur.x, cur.y+1)!=Cell.WLL) {
					files.add(new Point(cur.x, cur.y+1));
					tab[cur.x][cur.y+1].color=1;
			}
	   }
		return false;
}
}
