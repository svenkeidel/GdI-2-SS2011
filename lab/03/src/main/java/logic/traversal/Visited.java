package logic.traversal;

/**
 * Saves the information if a the left or right node in a tree was
 * visited.
 */
public class Visited {
	private boolean left;
	private boolean right;
	private boolean current;

	public Visited() {
		this.left    = false;
		this.current = false;
		this.right   = false;
	}

	public void    visitLeft()        { left    = true; }
	public void    visitCurrent()     { current = true; }
	public void    visitRight()       { right   = true; }
	public boolean isVisitedLeft()    { return left;    }
	public boolean isVisitedCurrent() { return current; }
	public boolean isVisitedRight()   { return right;   }
	public boolean isVisitedAll()     { return left && current && right; }
}
