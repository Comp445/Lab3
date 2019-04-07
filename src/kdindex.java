import java.util.*;
public class kdindex {
	//kdnode root = new kdnode(1);
	
	public void insert(String st, kdnode node) {
		if(node.status) {
			if(! node.fullOccupancy())
			node.Add(st);
			else {
				char sep = node.checkSeperator();
				node.status = false;
				kdnode Lnode = new kdnode(node.level+1);
				kdnode Rnode = new kdnode(node.level+1);
				
				node.left = Lnode;
				node.right = Rnode;
			
					if(sep == 'X') {
						/* node.al.sort((x,y)->x.substring(1, 9).compareTo(y.substring(1, 9)));
						 double spt = Double.parseDouble(node.al.get(15000).substring(1, 9));*/
						double spt = node.sum/30000;
						node.Sep = spt;
						 
						// kdnode Lnode = new kdnode(node.level+1);
						 Lnode.assignLimits(node.XL, node.XR, node.YL, node.YR, node.ZL, node.ZR);
						 
						// kdnode Rnode = new kdnode(node.level+1);
						 Rnode.assignLimits(node.XL, node.XR, node.YL, node.YR, node.ZL, node.ZR);
						 
						 for(String str : node.al) {
							 
							 if(Double.parseDouble(str.substring(1,9))< spt) {
								 Lnode.Add(str);
								
							 }else
								 Rnode.Add(str);
							 
						 }
						
						 Lnode.XR = spt;
						 Rnode.XL = spt;
						 node.al.clear();
						 					 
					} else if(sep == 'Y') {
						/*node.al.sort((x,y)->x.substring(11, 19).compareTo(y.substring(11, 19)));
						double spt = Double.parseDouble(node.al.get(15000).substring(11, 19)); */
						
						double spt = node.sum/30000;
						node.Sep = spt;
						
					//	 kdnode Lnode = new kdnode(node.level+1);
						 Lnode.assignLimits(node.XL, node.XR, node.YL, node.YR, node.ZL, node.ZR);
						 
					//	 kdnode Rnode = new kdnode(node.level+1);
						 Rnode.assignLimits(node.XL, node.XR, node.YL, node.YR, node.ZL, node.ZR);
						 
						 for(String str : node.al) {
							 
							 if(Double.parseDouble(str.substring(11,19))< spt) {
								 Lnode.Add(str);
							 }else
								 Rnode.Add(str);
							 
						 }
						 Lnode.YR = spt;
						 Rnode.YL = spt;
						 node.al.clear();
					} else {
						
						/*node.al.sort((x,y)->x.substring(21, 29).compareTo(y.substring(21, 29)));
						double spt = Double.parseDouble(node.al.get(15000).substring(21, 29));*/
						
						double spt = node.sum/30000;
						node.Sep = spt;
						 
						// kdnode Lnode = new kdnode(node.level+1);
						 Lnode.assignLimits(node.XL, node.XR, node.YL, node.YR, node.ZL, node.ZR);
						 
						// kdnode Rnode = new kdnode(node.level+1);
						 Rnode.assignLimits(node.XL, node.XR, node.YL, node.YR, node.ZL, node.ZR);
						 
						 for(String str : node.al) {
							 
							 if(Double.parseDouble(str.substring(21,29))< spt) {
								 Lnode.Add(str);
							 }else
								 Rnode.Add(str);
							 
						 }
						 Lnode.ZR = spt;
						 Rnode.ZL = spt;
						 node.al.clear();
					}
					
					insert(st, node);
			}
			
		}else {
			char sep = node.checkSeperator();
			if (sep == 'X') {
				if(node.Sep > Double.parseDouble(st.substring(1, 9))){
					
					insert(st, node.left);
				}else
					insert(st, node.right);
			} else if( sep == 'Y') {
				if(node.Sep > Double.parseDouble(st.substring(11, 19))){
					insert(st, node.left);
				}else
					insert(st, node.right);
			} else {
				if(node.Sep > Double.parseDouble(st.substring(21, 29))){
					
					insert(st, node.left);
				}else
					insert(st, node.right);
			}
		}
	}
	
	
	public kdnode findNode(double X, double Y, double Z, kdnode node){
		
		
	
		/*double X = Double.parseDouble(str.substring(1, 9));
		double Y = Double.parseDouble(str.substring(11, 19));
		double Z = Double.parseDouble(str.substring(21, 29));*/
		
	/*	System.out.println("level :" +node.level);
		System.out.println("Sep : "+node.Sep);
		System.out.println(node.XL+" "+node.XR);
		System.out.println(node.YL+" "+node.YR);
		System.out.println(node.ZL+" "+node.ZR);
		System.out.println(node.al.indexOf(str)); */
		
		if(node.status) {
		/*	System.out.println("THis is the node");
			System.out.println("**********");
			System.out.println();
			System.out.println("level :" +node.level);
			System.out.println("Sep : "+node.Sep);
			System.out.println(node.XL+" "+node.XR);
			System.out.println(node.YL+" "+node.YR);
			System.out.println(node.ZL+" "+node.ZR);
			System.out.println(node.al.indexOf(str));*/
			return node;
		}else {
			System.out.println("Note a leaf node, moving down");
			
			char spt = node.checkSeperator();
			kdnode newnode;
			if(spt == 'X') {
				if(X < node.Sep) {
					newnode = node.left;
				}else
					newnode = node.right;
			} else if(spt == 'Y') {
				if(Y < node.Sep) {
					newnode = node.left;
				}else
					newnode = node.right;
			}else {
				if(Z < node.Sep) {
					newnode = node.left;
				}else
					newnode = node.right;
			}
			
			return findNode(X,Y,Z,newnode);
		}
		
	}

}



 class kdnode{
	boolean status;
	int level;
	double sum = 0.0;
	double Sep = 0.0;
	double XL,XR;
	double YL,YR;
	double ZL,ZR;
	kdnode left;
	kdnode right;
	ArrayList<String> al = new ArrayList<String>();
	kdnode(int lvl){
		this.level = lvl;
		this.status = true;
		if(lvl == 1) {
			XL = 0.0;
			YL = 0.0;
			ZL = 0.0;
			XR = YR = ZR = 1000.0;
			
		}
	}
	kdnode(){
		this.status = true;
	}
	boolean fullOccupancy() {
		if(al.size() < 30000) 
		return false;
		else {
			
		return true;
		}
	}
	
	char checkSeperator() {
		int level = this.level;
		if(level%3 == 1) {
			return 'X';
		}else if(level%3 == 2) {
			return 'Y';
			}
		else
			return 'Z';
		}
	void Add(String str) {
		if(this.checkSeperator() == 'X') {
			this.sum = this.sum+Double.parseDouble(str.substring(1,9));
		}else if(this.checkSeperator() == 'Y') {
			this.sum = this.sum+Double.parseDouble(str.substring(11,19));
		}else
			this.sum = this.sum+Double.parseDouble(str.substring(21,29));
		
		this.al.add(str);
		
	}
	
	void assignLimits(double xl, double xr, double yl, double yr, double zl, double zr) {
		this.XL = xl;
		this.XR = xr;
		this.YL = yl;
		this.YR = yr;
		this.ZL = zl;
		this.ZR = zr;
	}
	
	}
