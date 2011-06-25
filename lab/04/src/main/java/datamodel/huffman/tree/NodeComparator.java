package datamodel.huffman.tree;

import java.util.Comparator;

public class NodeComparator implements Comparator<TreeNode> {

	@Override
	public int compare(TreeNode o1, TreeNode o2) {
		return o1.getValue() - o2.getValue();
	}
}
