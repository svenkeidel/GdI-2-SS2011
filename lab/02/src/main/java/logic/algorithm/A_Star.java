package logic.algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;
import static datamodel.GridElementState.*;
import static datamodel.GridElementAlgoState.*;

import org.apache.log4j.Logger;

//import org.apache.log4j.Logger;

import datamodel.Grid;
import datamodel.GridElement;

public class A_Star extends Dijkstra {
	private static final Logger logger = Logger.getLogger(A_Star.class);

	private static double tolerance;

	public void init(Grid grid) {
		super.init(grid);

		if (Grid.getAutoStatus()){
			setTolerance(grid.getLowestWeight());
		}
	}

	protected Comparator<GridElement> getComparator() {
		return new Comparator<GridElement> () {
			public int compare(GridElement o1, GridElement o2) {
				return new Long(Math.round(
							((double)o1.getDistance() + o1.getLinearDistance()*tolerance)
						  - ((double)o2.getDistance() + o2.getLinearDistance()*tolerance)
						  )).intValue();
			}
		};
	}
	
	public static void setTolerance(double tol){
		tolerance = tol;
	}

	public boolean doNextStep() {

		if(!reachableKnodes.isEmpty() && endKnode.getAlgoState() != LOOKED_AT) {

			// polling retrieves and removes the head of this queue
			GridElement nearest = reachableKnodes.poll();
			nearest.setAlgoState(LOOKED_AT);

			Vector<Integer> weights = grid.getNeighborsFrom(nearest, true).getWayCosts();
			Vector<GridElement> neighbors = grid.getNeighborsFrom(nearest, true).getNeighbors();
			int neighborWeight;

			for(GridElement neighbor : neighbors) {
				neighborWeight = weights.get(neighbors.indexOf(neighbor));

				if(neighbor.getAlgoState() != LOOKED_AT) {
					
					if(neighbor.getDistance() == INFINITE){
						neighbor.setDistance(nearest.getDistance() + neighborWeight);
						neighbor.setPath(nearest);
						reachableKnodes.offer(neighbor);
						}
					
					if(nearest.getDistance() + neighborWeight < neighbor.getDistance()) {
						neighbor.setDistance(nearest.getDistance() + neighborWeight);
						neighbor.setPath(nearest);}
					
				}
			}

			return true;
		} else {
			return false;
		}
	}
}
