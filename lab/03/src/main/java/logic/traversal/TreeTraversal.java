package logic.traversal;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import datamodel.tree.Tree;
import datamodel.tree.TreeNode;

/**
 * Abstract definition of a TreeTraversal. It uses a thread to
 * implement the producer-consumer design pattern. If the next element
 * in the iteration is requested, the thread starts. If it was found,
 * the thread is stoped.
 */
public abstract class TreeTraversal extends Thread implements Iterable<TreeNode>, Iterator<TreeNode> {

	/**
	 * the tree to iterate over.
	 */
	private Tree tree;

	/**
	 *
	 */
	private Queue<TreeNode> nextNodes;

	/**
	 * if the next treenode is availiable
	 */
	private boolean request;

	/**
	 * if the first element was inserted in queue
	 */
	private boolean started;
	
	/**
	 * Constructor.
	 * @param tree the tree to iterate over. 
	 */
	public TreeTraversal(Tree tree) {
		this.tree = tree;
		nextNodes = new LinkedList<TreeNode>();
		this.request = false;
		this.started = false;
		this.start();
	}


	/**
	 * Visits the left subtree of a node if it exists.
	 * The currentNode pointer is left were it were.
	 */
	protected synchronized void visitLeftSubtree() {
		if(tree.hasLeftNode()) {
			tree.moveToLeftNode();
			traverse();
			tree.moveToParentNode();
		}
	}


	/**
	 * Visits the right subtree of a node if it exists.
	 * The currentNode pointer is left were it were.
	 */
	protected synchronized void visitRightSubtree() {
		if(tree.hasRightNode()) {
			tree.moveToRightNode();
			traverse();
			tree.moveToParentNode();
		}
	}


	/**
	 * Visits the current node.
	 * The currentNode pointer is left were it were.
	 */
	protected synchronized void visitCurrentNode() {
		while(request == true) {
			try {
				wait();
			} catch(InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		nextNodes.offer(tree.getCurrentNode());
		started = true;
		request = true;
		notifyAll();
	}


	/**
	 * traverses over a tree with a concrete strategy and adds nodes to
	 * a vector.
	 */
	protected abstract void traverse();


	@Override
	public void run() {
		traverse();
	}

	@Override
	public synchronized boolean hasNext() {
		while(started == false && request == false) {
			try {
				wait();
			} catch(InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		return !nextNodes.isEmpty();
	}

	public synchronized void request() {
		while(request == false) {
			try {
				wait();
			} catch(InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		request = false;
		notifyAll();
	}
	
	public synchronized TreeNode consume() {
		return nextNodes.poll();
	}
	
	@Override
	public synchronized TreeNode next() {
		request();
		return consume();
	}

	public void remove() {
		throw new UnsupportedOperationException("Removing is not supported yet");
	}

	/**
	 * returns a vector to iterate over.
	 */
	@Override
	public Iterator<TreeNode> iterator() {
		return this;
	}
}
