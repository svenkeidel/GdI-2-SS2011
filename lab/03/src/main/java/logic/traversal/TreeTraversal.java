package logic.traversal;

import java.util.Iterator;
import java.util.Stack;

import org.apache.log4j.Logger;

import datamodel.tree.Tree;
import datamodel.tree.TreeNode;

public abstract class TreeTraversal implements Iterable<TreeNode>, Iterator<TreeNode> {
	private static final Logger logger =
		Logger.getLogger(TreeTraversal.class);



	/**
	 * the tree to iterate over.
	 */
	protected Tree tree;

	protected Stack<Visited> visited;


	/**
	 * Constructor.
	 * @param tree the tree to iterate over. 
	 */
	public TreeTraversal(Tree tree) {
		this.tree = tree;
		this.visited = new Stack<Visited>();
		visited.push(new Visited());
	}


	/**
	 * Visits the left subtree of a node if it exists.
	 */
	protected TreeNode visitLeftSubtree() {
		if(!visited.peek().isVisitedLeft()) {

			if(tree.hasLeftNode()) {
				visited.peek().visitLeft();
				tree.moveToLeftNode();
				visited.push(new Visited());
				return visitLeftSubtree();
			} else {
				TreeNode result = visitCurrentNode();
				backtracking();
				return result;
			}

		} else {
			return null;
		}
	}

	/**
	 * Visits just the left node if it exists.
	 */
	protected TreeNode visitLeft() {
		if(!visited.peek().isVisitedLeft() && tree.hasLeftNode()) {

			visited.peek().visitLeft();
			tree.moveToLeftNode();
			visited.push(new Visited());
			return visitCurrentNode();

		} else {

			backtracking();
			return null;
		}
	}

	/**
	 * Visits the right subtree of a node if it exists.
	 */
	protected TreeNode visitRightSubtree() {
		if(!visited.peek().isVisitedRight()) {

			if(tree.hasRightNode()) {
				visited.peek().visitRight();
				tree.moveToRightNode();
				visited.push(new Visited());
				return visitRightSubtree();
			} else {
				TreeNode result = visitCurrentNode();
				backtracking();
				return result;
			}

		} else {
			return null;
		}
	}

	/**
	 * Visits just the right node if it exists.
	 */
	protected TreeNode visitRight() {
		if(!visited.peek().isVisitedRight() && tree.hasRightNode()) {

			visited.peek().visitRight();
			tree.moveToRightNode();
			visited.push(new Visited());
			return visitCurrentNode();

		} else {

			backtracking();
			return null;
		}
	}

	/**
	 * Visits the current node.
	 * The currentNode pointer is left were it were.
	 */
	protected TreeNode visitCurrentNode() {
		if(visited.peek().isVisitedCurrent()) {
			return null;
		} else {
			logger.debug("visit node "+tree.getCurrentNode());
			visited.peek().visitCurrent();
			return tree.getCurrentNode();
		}
	}

	protected void backtracking() {
		if(!visited.isEmpty()) {
			do {
				tree.moveToParentNode();
				visited.pop();
				logger.debug("backtracking to node "+ tree.getCurrentNode());
			} while (!visited.isEmpty() && visited.peek().isVisitedAll());
		}
	}

	@Override
	public boolean hasNext() {
		return visited.size() == 1 && visited.peek().isVisitedAll(); 
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Removing is not supported yet");
	}

	@Override
	public Iterator<TreeNode> iterator() {
		return this;
	}
}
