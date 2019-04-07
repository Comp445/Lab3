import java.util.*;

public class kdSearch {

	static ArrayList<kdnode> leafnodes = new ArrayList<kdnode>();
	static ArrayList<String> alr = new ArrayList<String>();
	public void rangeSearch(double xl, double xr, double yl, double yr, double zl, double zr, kdnode node) {
		System.out.println("Initiating range search");
		System.out.println(xl);
		System.out.println(xr);
		System.out.println(yl);
		System.out.println(yr);
		System.out.println(zl);
		System.out.println(zr);
		
		if(node.status) {
			for(String sr : node.al) {
				double X = Double.parseDouble(sr.substring(1,9));
				double Y = Double.parseDouble(sr.substring(11,19));
				double Z = Double.parseDouble(sr.substring(21,29));
			//	System.out.println(X);
				//System.out.println(Y);
				//System.out.println(Z);
				if(X > xl && X < xr && Y > yl && Y< yr && Z > zl && Z < zr) {
					alr.add(sr);
				}
				
			}
			
		} else {
			char sep = node.checkSeperator();
			//double sepVal = node.Sep;
			if(sep == 'X') {
				double X = node.Sep;
				if(X < xl && X < xr) {
					rangeSearch(xl,xr,yl,yr,zl,zr,node.right);
				}else if(X >= xl && X >= xr) {
					rangeSearch(xl,xr,yl,yr,zl,zr,node.left);
				}else {
					rangeSearch(xl,xr,yl,yr,zl,zr,node.left);
					rangeSearch(xl,xr,yl,yr,zl,zr,node.right);
				}
			} else if(sep == 'Y') {
				double Y = node.Sep;
				if(Y < yl && Y < yr) {
					rangeSearch(xl,xr,yl,yr,zl,zr,node.right);
				}else if(Y >= yl && Y >= yr) {
					rangeSearch(xl,xr,yl,yr,zl,zr,node.left);
				}else {
					rangeSearch(xl,xr,yl,yr,zl,zr,node.left);
					rangeSearch(xl,xr,yl,yr,zl,zr,node.right);
				}
			} else if(sep == 'Z') {
				double Z = node.Sep;
				if(Z < zl && Z < zr) {
					rangeSearch(xl,xr,yl,yr,zl,zr,node.right);
				}else if(Z >= zl && Z >= zr) {
					rangeSearch(xl,xr,yl,yr,zl,zr,node.left);
				}else {
					rangeSearch(xl,xr,yl,yr,zl,zr,node.left);
					rangeSearch(xl,xr,yl,yr,zl,zr,node.right);
				}
			}
		
		
		}
		
		
	}
	
	public String nearestNbg(double nx, double ny, double nz, kdnode node, kdnode root) {
		
		
		String nearestStr = findNearestPoint(nx,ny,nz,node) ;
		double X = Double.parseDouble(nearestStr.substring(1,9));
		double Y = Double.parseDouble(nearestStr.substring(11,19));
		double Z = Double.parseDouble(nearestStr.substring(21,29));
		double dist =  distance(nx,ny,nz,X,Y,Z);
		double newDist = 0.0;
		
		System.out.println("Nearest point in same bucket :" + nearestStr);
		
		//checking distance between point and planes
		
		getleafnodes(root);
		
		if(dist > distance(nx,ny,nz,node.XL,ny,nz)) {
			
			System.out.println("The X plane on left is nearer to nearest point in the bucket, checking now");
			System.out.println(node.XL);
			for(kdnode cnode : leafnodes) {
				if(cnode.XR == node.XL && ny >= cnode.YL && ny <= cnode.YR && nz >= cnode.ZL && nz <= cnode.ZR) {
					String str = findNearestPoint(nx,ny,nz,cnode);
					 X = Double.parseDouble(str.substring(1,9));
					 Y = Double.parseDouble(str.substring(11,19));
					 Z = Double.parseDouble(str.substring(21,29));
					 newDist = distance(nx,ny,nz,X,Y,Z);
					 if (dist > newDist ) {
						 dist = newDist;
						 nearestStr = str;
					 }
				} 
			}
			
		}
		if(dist > distance(nx,ny,nz,node.XR,ny,nz)) {
			
			System.out.println("The X plane on the right is nearer to the nearest point in the bucket, checking now");
			System.out.println(node.XR);
			for(kdnode cnode : leafnodes) {
				if(cnode.XL == node.XR && ny >= cnode.YL && ny <= cnode.YR && nz >= cnode.ZL && nz <= cnode.ZR) {
					String str = findNearestPoint(nx,ny,nz,cnode);
					 X = Double.parseDouble(str.substring(1,9));
					 Y = Double.parseDouble(str.substring(11,19));
					 Z = Double.parseDouble(str.substring(21,29));
					 newDist = distance(nx,ny,nz,X,Y,Z);
					 if (dist > newDist ) {
						 dist = newDist;
						 nearestStr = str;
					 }
				} 
			}
			
		}
		
		if(dist > distance(nx,ny,nz,nx,node.YL,nz)) {
			
			System.out.println("The Y plane on the left is nearer to the nearest point in the bucket, checking now");
			System.out.println(node.YL);
			
			for(kdnode cnode : leafnodes) {
				if(cnode.YR == node.YL && nx >= cnode.XL && nx <= cnode.XR && nz >= cnode.ZL && nz <= cnode.ZR) {
					String str = findNearestPoint(nx,ny,nz,cnode);
					 X = Double.parseDouble(str.substring(1,9));
					 Y = Double.parseDouble(str.substring(11,19));
					 Z = Double.parseDouble(str.substring(21,29));
					 newDist = distance(nx,ny,nz,X,Y,Z);
					 if (dist > newDist ) {
						 dist = newDist;
						 nearestStr = str;
					 }
				} 
			}
			
		}
		
		if(dist > distance(nx,ny,nz,nx,node.YR,nz)) {
			
			System.out.println(" The Y plane on the right is nearer to the nearest point in the bucket, checking now");
			System.out.println(node.YR);
			
			for(kdnode cnode : leafnodes) {
				if(cnode.YL == node.YR && nx >= cnode.XL && nx <= cnode.XR && nz >= cnode.ZL && nz <= cnode.ZR) {
					String str = findNearestPoint(nx,ny,nz,cnode);
					 X = Double.parseDouble(str.substring(1,9));
					 Y = Double.parseDouble(str.substring(11,19));
					 Z = Double.parseDouble(str.substring(21,29));
					 newDist = distance(nx,ny,nz,X,Y,Z);
					 if (dist > newDist ) {
						 dist = newDist;
						 nearestStr = str;
					 }
				} 
			}
			
		}
		
		if(dist > distance(nx,ny,nz,nx,ny,node.ZL)) {
			
			System.out.println("The Z plane on the left is nearer to the nearest point in the bucket, checking now");
			System.out.println(node.ZL);
			for(kdnode cnode : leafnodes) {
				if(cnode.ZR == node.ZL && nx >= cnode.XL && nx <= cnode.XR && ny >= cnode.YL && ny <= cnode.YR) {
					String str = findNearestPoint(nx,ny,nz,cnode);
					 X = Double.parseDouble(str.substring(1,9));
					 Y = Double.parseDouble(str.substring(11,19));
					 Z = Double.parseDouble(str.substring(21,29));
					 newDist = distance(nx,ny,nz,X,Y,Z);
					 if (dist > newDist ) {
						 dist = newDist;
						 nearestStr = str;
					 }
				} 
			}
			
		}
		
		if(dist > distance(nx,ny,nz,nx,ny,node.ZR)) {
			
			System.out.println("The Z plane on the right is nearer to the nearest point in the bucket, checking now");
			System.out.println(node.ZR);
			for(kdnode cnode : leafnodes) {
				if(cnode.ZL == node.ZR && nx >= cnode.XL && nx <= cnode.XR && ny >= cnode.YL && ny <= cnode.YR) {
					String str = findNearestPoint(nx,ny,nz,cnode);
					 X = Double.parseDouble(str.substring(1,9));
					 Y = Double.parseDouble(str.substring(11,19));
					 Z = Double.parseDouble(str.substring(21,29));
					 newDist = distance(nx,ny,nz,X,Y,Z);
					 if (dist > newDist ) {
						 dist = newDist;
						 nearestStr = str;
					 }
				} 
			}
			
		}
		
		
		return nearestStr;
		
		
	}
	
	public Double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
		return (Math.pow((x1-x2), 2)+Math.pow((y1-y2), 2)+Math.pow((z1-z2), 2));
	}
	
	public void getleafnodes(kdnode node) {
		
		if(node.status) {
			leafnodes.add(node);
			return;
		}else {
			kdnode[] cnodes = {node.left,node.right};
			for(kdnode cnode : cnodes) {
				getleafnodes(cnode);
			}
			return;
		}
	}
	
	public String findNearestPoint(double nx, double ny, double nz, kdnode node) {
		
		boolean firstFlag = true;
		double dist = 0.0;
		String nearestStr = null;
		for(String str : node.al) {
			double X = Double.parseDouble(str.substring(1,9));
			double Y = Double.parseDouble(str.substring(11,19));
			double Z = Double.parseDouble(str.substring(21,29));
			if(firstFlag){
			dist = distance(nx,ny,nz,X,Y,Z);
			nearestStr = str;
			firstFlag = false;
			}else {
				double newDist = distance(nx,ny,nz,X,Y,Z);
				if(dist > newDist) {
					dist = newDist;
					nearestStr = str;
				}
			}
					
		}
		
		return nearestStr;
	}
	
}
