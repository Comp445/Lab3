import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.*;

public class test {


		  public static void main (String args[]) {

		   /* int i = 4;
		    int j = 3;

		    System.out.println("i is " + i);
		    System.out.println("j is " + j);
		  
		    int k = i % j;
		    System.out.println("i%j is " + k);*/
			  InputStream is = null;
			  BufferedReader br = null;
			  try {
			  is = new FileInputStream("input.txt");
				br = new BufferedReader (new InputStreamReader(is));
				
				Scanner in = new Scanner(System.in);
				String str = in.nextLine();
				String[] starr = str.split(",");
				double xl = Double.parseDouble(starr[0]);
				double xr = Double.parseDouble(starr[1]);
				double yl = Double.parseDouble(starr[2]);
				double yr = Double.parseDouble(starr[3]);
				double zl = Double.parseDouble(starr[4]);
				double zr = Double.parseDouble(starr[5]);
				
				String line;
				
				while((line = br.readLine()) != null) {
					double X = Double.parseDouble(line.substring(1,9));
					double Y = Double.parseDouble(line.substring(11,19));
					double Z = Double.parseDouble(line.substring(21,29));
					if(X > xl && X < xr && Y > yl && Y< yr && Z > zl && Z < zr) {
						System.out.println(line);
					}
				}
				
				
		  } catch(Exception ex) {
			  ex.printStackTrace();
		  }

		}

}