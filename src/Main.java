import java.util.Scanner;

public class Main {

	static char[][] graph = null;
	static Point origin;
	static int n;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n=sc.nextInt();
		sc.nextLine();
		graph = new char[n][n];
		origin = new Point(n/2,n/2);
		
		for (int i=0;i<n;i++) {
			String line=sc.nextLine();
			for (int j=0;j<n;j++) {
				graph[j][i] = line.charAt(j);
			}
		}
		
		int count = 0;
		if (isSymetricH()) count++;
		if (isSymetricV()) count++;
		if (isSymetricD()) count++;
		if (isSymetricBD()) count++;
		
		System.out.println(count);
	}
	
	private static boolean isSymetricBD() {
		// TODO Auto-generated method stub
		for (int x=0;x<n;x++) {
			for (int y=x;y<n;y++) {
				char c1 = graph[x][y];
				Point loc = getSymLocBD(x,y);
				char c2 = graph[loc.x][loc.y];
				if (!isSymD(c1,c2)) return false;
			}
		}
		return true;
	}

	private static Point getSymLocBD(int x, int y) {
		// TODO Auto-generated method stub
		Point loc=transform(new Point(x,y));
		if (x>=0 && y>=0 || x<0 && y<0)
			loc = new Point(-loc.y,-loc.x);
		else 
			loc = new Point(loc.y,loc.x);
		loc = backTransform(loc);
		return loc;
	}

	private static boolean isSymetricD() {
		// TODO Auto-generated method stub
		for (int x=0;x<n;x++) {
			for (int y=0;y<n/2-x;y++) {
				char c1 = graph[x][y];
				Point loc = getSymLocD(x,y);
				char c2 = graph[loc.x][loc.y];
				if (!isSymD(c1,c2)) return false;
			}
		}
		return true;
	}

	private static boolean isSymD(char c1, char c2) {
		// TODO Auto-generated method stub
		if (c1=='(' || c1==')') return false;
		return c1==c2;
	}

	private static Point getSymLocD(int x, int y) {
		// TODO Auto-generated method stub
		Point loc=transform(new Point(x,y));
		if (x>=0 && y>=0 || x<0 && y<0)
			loc = new Point(loc.y,loc.x);
		else 
			loc = new Point(-loc.y,-loc.x);
		loc = backTransform(loc);
		return loc;
	}

	private static boolean isSymetricV() {
		// TODO Auto-generated method stub
		for (int x=0;x<n/2;x++) {
			for (int y=0;y<n;y++) {
				char c1 = graph[x][y];
				Point loc = getSymLocV(x,y);
				char c2 = graph[loc.x][loc.y];
				if (!isSymV(c1,c2)) return false;
			}
		}
		return true;
	}

	private static boolean isSymV(char c1, char c2) {
		// TODO Auto-generated method stub
		if (c1=='/') return c2=='\\';
		if (c1=='\\') return c2=='/';
		if (c1=='(') return c2==')';
		if (c1==')') return c2=='(';
		return c1==c2;
	}

	private static Point getSymLocV(int x, int y) {
		// TODO Auto-generated method stub
		Point loc=transform(new Point(x,y));
		loc = new Point(-loc.x,loc.y);
		loc = backTransform(loc);
		return loc;
	}

	private static boolean isSymetricH() {
		// TODO Auto-generated method stub
		for (int x=0;x<n;x++) {
			for (int y=0;y<n/2;y++) {
				char c1 = graph[x][y];
				Point loc = getSymLocH(x,y);
				char c2 = graph[loc.x][loc.y];
				if (!isSymH(c1,c2)) return false;
			}
		}
		return true;
	}

	private static boolean isSymH(char c1, char c2) {
		// TODO Auto-generated method stub
		if (c1=='/') return c2=='\\';
		if (c1=='\\') return c2=='/';
		return c1==c2;
	}

	private static Point getSymLocH(int x, int y) {
		// TODO Auto-generated method stub
		Point loc=transform(new Point(x,y));
		loc = new Point(loc.x,-loc.y);
		loc = backTransform(loc);
		return loc;
	}

	private static Point backTransform(Point point) {
		// TODO Auto-generated method stub
		return new Point(point.x+n/2,point.y+n/2);
	}

	private static Point transform(Point point) {
		// TODO Auto-generated method stub
		return new Point(point.x-n/2,point.y-n/2);
	}

}

class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
