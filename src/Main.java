import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Starting indexing process");
		kdnode root = new  kdnode(1);
		kdindex kdi = new kdindex();
		kdSearch kds = new  kdSearch();
		
		InputStream is = null;
		BufferedReader br = null;
		System.out.println(System.currentTimeMillis()/1000);
		
		try {
			is = new FileInputStream("input.txt");
			br = new BufferedReader (new InputStreamReader(is));
			
			String line = null;
			while((line = br.readLine())!= null) {
				kdi.insert(line, root);
			}
			System.out.println(System.currentTimeMillis()/1000);
		
			
			System.out.println("Enter the Range :");
			String st;
			/*
			 * Commenting range query part;
			 *  st = in .nextLine();
			String[] starr = st.split(",");
			double xl = Double.parseDouble(starr[0]);
			double xr = Double.parseDouble(starr[1]);
			double yl = Double.parseDouble(starr[2]);
			double yr = Double.parseDouble(starr[3]);
			double zl = Double.parseDouble(starr[4]);
			double zr = Double.parseDouble(starr[5]);*/
			 long startTime = System.currentTimeMillis();
			//kds.rangeSearch(xl,xr,yl,yr,zl,zr,root);
			 long endTime = System.currentTimeMillis();
			System.out.println("Execution Time: " + ((endTime - startTime) / 1000.0)  + " seconds");
			//for(String str : kdSearch.alr) {
			//	System.out.println(str);
			//}
			
			System.out.println("Enter the Nearest neighbour Point :");
			st = in.nextLine();
			String[] star = st.split(",");
			double nx = Double.parseDouble(star[0]);
			double ny = Double.parseDouble(star[1]);
			double nz = Double.parseDouble(star[2]);
			
			startTime = System.currentTimeMillis();
			kdnode fnode = kdi.findNode(nx,ny,nz, root);
			
			String str = kds.nearestNbg(nx, ny, nz, fnode, root);
			 endTime = System.currentTimeMillis();
			 
			System.out.println(str);
			System.out.println("Execution Time: " + ((endTime - startTime) / 1000.0)  + " seconds");
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}

		
		
	}

}
