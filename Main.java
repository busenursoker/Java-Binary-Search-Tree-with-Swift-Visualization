package binarysearch;

public class Main {

	public static void main(String[] args) {
		BST tree = new BST();
		tree.insert(new TreeNode(2));
		tree.insert(new TreeNode(3));
		tree.insert(new TreeNode(5));
		tree.insert(new TreeNode(4));
		tree.insert(new TreeNode(7));
		tree.insert(new TreeNode(8));
		tree.insert(new TreeNode(9));
		tree.insert(new TreeNode(1));
		tree.delete(2);
		tree.draw();
		
	}
}
