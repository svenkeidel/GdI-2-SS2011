package logic.algorithm;

import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.Vector;

import org.apache.log4j.Logger;

import datamodel.Grid;
import datamodel.GridElement;
import static datamodel.GridElementAlgoState.*;

public class Dijkstra implements Algorithm {
	private static final Logger logger = Logger.getLogger(Dijkstra.class);

	public final static int INFINITE = Integer.MAX_VALUE;

	private Grid grid;
	private Vector<GridElement> reachableKnodes;
	private GridElement startKnode, endKnode;
	private Comparator<GridElement> comparator;

	public void init(Grid grid) {
		logger.info("Initialize Dijkstra");

		this.grid = grid;
		this.startKnode = grid.getStartElement();
		this.endKnode   = grid.getEndElement();
		this.reachableKnodes = new Vector<GridElement>();
		this.comparator = getComparator();

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
			//neighbor.setAlgoState(PATH);
		}
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

			Collections.sort(reachableKnodes, comparator);
			GridElement nearest = reachableKnodes.get(0);
			nearest.setAlgoState(LOOKED_AT);

			reachableKnodes.remove(nearest);
			logger.debug("delete Knode ("+nearest.getRow()+", "+nearest.getColumn()+")");
			for(GridElement e : reachableKnodes)
				logger.debug("("+e.getRow()+", "+e.getColumn()+").e = "+e.getDistance());
			logger.debug("");

			for(GridElement neighbor :
					grid.getNeighborsFrom(nearest).getNeighbors() ) {

				if(neighbor.getAlgoState() != LOOKED_AT) {
					if(neighbor.getDistance() == INFINITE)
						reachableKnodes.add(neighbor);

					if(nearest.getDistance() + neighbor.getWeight() < neighbor.getDistance()) {
						neighbor.setDistance(nearest.getDistance() + neighbor.getWeight());
						//neighbor.setAlgoState(PATH);
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
