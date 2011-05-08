package logic.algorithm;

import java.util.Comparator;

import datamodel.GridElement;

public class A_Star extends Dijkstra {

	protected Comparator<GridElement> getComparator() {
		return new Comparator<GridElement> () {
			public int compare(GridElement o1, GridElement o2) {
				return (o1.getDistance() + o1.getLinearDistance())
					 - (o2.getDistance() + o2.getLinearDistance());
			}
		};
	}
}
