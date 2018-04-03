package com.company.Complete_Traversal;


import com.company.Single_Target_Search.Points;

/* Used for my "branchingHeuristic", which takes too long to run */
public class SimplePoint {
	/* Data */
	public int x;
	public int y;
    public boolean inPath;
    
	/* Constructor */
	public SimplePoint(int x, int y){
		this.x = x;
		this.y = y;
		this.inPath = false;
	}
	
	public SimplePoint(int x, int y, boolean visited){
		this.x = x;
		this.y = y;
		this.inPath = visited;
	}
	
	/* Converts from Point to SimplePoint */
	public SimplePoint(Points p){
		x = p.x;
		y = p.y;
		this.inPath = false;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == this) {
			return true;
		}

		if (!(obj instanceof SimplePoint)) {
			return false;
		}

		SimplePoint pos = (SimplePoint) obj;

        return pos.x == x && pos.y == y && pos.inPath == inPath;
    }
	
	@Override
	public int hashCode(){
		return 3*x + 5*y;
	}
}
