package logic.algorithm;

import java.util.TreeSet;
import java.util.Comparator;

import datamodel.Grid;
import datamodel.GridElement;
import static datamodel.GridElementAlgoState.*;

public class Dijkstra implements Algorithm {

	public final static int INFINITE = Integer.MAX_VALUE;

	private Grid grid;
	private TreeSet<GridElement> reachableKnodes;
	private GridElement startKnode, endKnode;

	public void init(Grid grid) {

		this.grid = grid;
		startKnode = grid.getStartElement();
		endKnode   = grid.getEndElement();

		if(startKnode == null)
			throw new IllegalStateException(
					"No start knode specified for dijkstra algorithm");

		if(endKnode   == null)
			throw new IllegalStateException(
					"No target knode specified for dijkstra algorithm");

		for(GridElement v : grid.getKnodes()) {
			v.setDistance(INFINITE);
			v.setAlgoState(NONE);
		}

		startKnode.setDistance(0);
		startKnode.setAlgoState(LOOKED_AT);

		// for all neighbors
		for(GridElement neighbor :
				grid.getNeighborsFrom(startKnode).getNeighbors() ) {
			reachableKnodes.add(neighbor);
			neighbor.setDistance(neighbor.getWeight());
			neighbor.setAlgoState(PATH);
		}

		reachableKnodes = new TreeSet<GridElement>(getComparator());
	}

	protected Comparator<GridElement> getComparator() {
		return new Comparator<GridElement>(){
			public int compare(GridElement o1, GridElement o2) {
				return o1.getDistance() - o2.getDistance();
			}
		};
	}

	public boolean doNextStep() {

		if(!reachableKnodes.isEmpty() && endKnode.getAlgoState() != LOOKED_AT) {

			GridElement nearest = reachableKnodes.first();
			reachableKnodes.remove(0);
			nearest.setAlgoState(LOOKED_AT);

			for(GridElement neighbor :
					grid.getNeighborsFrom(nearest).getNeighbors() ) {

				if(neighbor.getAlgoState() != LOOKED_AT) {
					if(neighbor.getDistance() == INFINITE) {
						reachableKnodes.add(neighbor);
					} else if(nearest.getDistance() + neighbor.getWeight() < neighbor.getDistance()) {
						neighbor.setDistance(nearest.getDistance() + neighbor.getWeight());
						neighbor.setAlgoState(PATH);
					}
				}
			}

			return true;
		} else {
			return false;
		}
	}

	public boolean isSolved() {
		return endKnode.getAlgoState() == LOOKED_AT;
	}
}
