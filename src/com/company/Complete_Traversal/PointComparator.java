package com.company.Complete_Traversal;

import com.company.Single_Target_Search.Points;

import java.util.Comparator;

/* Used in "fancyHeuristic" to sort Points by increasing "x" value (ties are broken by increasing "y" values */
public class PointComparator implements Comparator<Points>{
	public int compare(Points p1, Points p2){
		if (p1.x == p2.x)
			return p1.y - p2.y;
		else
			return p1.x - p2.x;
	}
}
