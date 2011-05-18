package logic.algorithm;

import static datamodel.GridElementAlgoState.LOOKED_AT;
import static datamodel.GridElementAlgoState.NONE;

import java.util.Vector;

import datamodel.Grid;
import datamodel.GridElement;

public class Moore implements Algorithm {

	private Grid grid;
	private GridElement startKnode, endKnode;
	public final static int INFINITE = Integer.MAX_VALUE;
	
	private Vector<GridElement> neighbors = new Vector<GridElement>();
	private Vector<GridElement> possible_neighbors;
	private GridElement focus_knode;
	
	
	public void init(Grid grid) {
		this.grid = grid;
		this.startKnode = grid.getStartElement();
		this.endKnode   = grid.getEndElement();
		
		if(startKnode == null)
			throw new IllegalStateException(
					"No start knode specified for moore algorithm");

		if(endKnode   == null)
			throw new IllegalStateException(
					"No target knode specified for moore algorithm");

		for(GridElement v : grid.getKnodes()) {
			v.setDistance(INFINITE);
			v.setAlgoState(NONE);
		}

		startKnode.setDistance(0);
		startKnode.setAlgoState(LOOKED_AT);
		startKnode.setPath(null);
		neighbors.add(startKnode);
	}

	
	
	public boolean doNextStep() {
		
		if (!neighbors.isEmpty() && endKnode.getAlgoState() != LOOKED_AT){
			
			focus_knode = neighbors.get(0);
			neighbors.remove(0);
			focus_knode.setAlgoState(LOOKED_AT);
			
			possible_neighbors = grid.getNeighborsFrom(focus_knode, false).getNeighbors();
			
			for(GridElement neighbor : possible_neighbors){
				
				if(neighbor.getDistance() == INFINITE){
					neighbors.add(neighbor);
					neighbor.setDistance(neighbor.getWeight() + focus_knode.getDistance());
					neighbor.setPath(focus_knode);
				}
			}
	return true;	
	}else
		return false;
	}
	
	public boolean isSolved() {
		return endKnode.getAlgoState() == LOOKED_AT;
	}
}
