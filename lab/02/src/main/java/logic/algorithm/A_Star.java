package logic.algorithm;

import java.util.Comparator;

//import org.apache.log4j.Logger;

import datamodel.GridElement;

public class A_Star extends Dijkstra {
	// private static final Logger logger = Logger.getLogger(A_Star.class);

	public static final int INFINITE = Integer.MAX_VALUE;
	public static final int MINUS_INFINITE = Integer.MIN_VALUE;
	public static double tolerance = A_Star_Tolerance.VERYLOW.getValue();

	protected Comparator<GridElement> getComparator() {
		return new Comparator<GridElement> () {
			public int compare(GridElement o1, GridElement o2) {
				//logger.debug("("+o1.getRow()+","+o1.getColumn()+")");
				//logger.debug("o1 distance:"+o1.getDistance()+"\to1 linear:"+o1.getLinearDistance());
				//logger.debug("("+o2.getRow()+","+o2.getColumn()+")");
				//logger.debug("o2 distance:"+o2.getDistance()+"\to2 linear:"+o2.getLinearDistance());
				//logger.debug(new Long(Math.round(((double)o1.getDistance() + o1.getLinearDistance())
					 //- ((double)o2.getDistance() + o2.getLinearDistance()))).intValue());
				//logger.debug("");
				/*if(o1.getDistance() == INFINITE && o1.getDistance() == INFINITE)
					return new Long(Math.round(o1.getLinearDistance() - o2.getLinearDistance())).intValue();

				if(o1.getDistance() == INFINITE)
					return INFINITE;

				if(o2.getDistance() == INFINITE)
					return MINUS_INFINITE;*/
				
				//logger.debug("("+GridWindow.get_A_Star_Tol()+")");
				return new Long(Math.round(((double)o1.getDistance() + o1.getLinearDistance()*tolerance)
					 - ((double)o2.getDistance() + o2.getLinearDistance()*tolerance))).intValue();
			}
		};
	}
	
	public static void setTolerance(double tol){
		tolerance = tol;
	}
}
