package binarysearch;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BST 
{
	public TreeNode root;
	
	public void insert(TreeNode node) {
		root = insertHelper (root,node);
	}
	private TreeNode insertHelper(TreeNode root,TreeNode node) {
		int data=node.data;
		
		if(root==null) {// base case adds root
			root = node;
			return root;	
		}
		else if(data<root.data) {
			root.left=insertHelper(root.left,node);// smaller data goes to left
		}
		else {
			root.right = insertHelper(root.right,node);// bigger data goes to right
		}
		return root;
	}
	
	
	public void delete(int data) {
		 root = deleteHelper(root, data);
		}	
	private TreeNode deleteHelper(TreeNode root,int data) {
		if(root == null) {
			return root;
		}
		if (data< root.data) {
			root.left = deleteHelper(root.left,data);
		}
		else if (data> root.data) {
			root.right = deleteHelper(root.right,data);
		}
		else {
			if(root.left == null && root.right == null) { //leaf node
				root = null;
			}
			else if(root.right != null) { //find a successor to replace this node
				root.data = successor(root);
				root.right = deleteHelper(root.right, root.data);
			}
			else { //find a predecessor to replace this node
				root.data = predecessor(root);
				root.left = deleteHelper(root.left, root.data);
			}
		}
		return root;
	}
	private int successor(TreeNode root) {//find least value on the right
		root = root.right;
		while(root.left != null){
			root = root.left;
		}
		return root.data;
	}
	private int predecessor(TreeNode root) {//find greatest value on the left
		root = root.left;
		while(root.right != null){
			root = root.right;
		}
		return root.data;
	}
	
	
	    public void draw() {
	        JFrame frame = new JFrame("BST Panel");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().add(new TreePanel(root));
	        frame.setSize(400, 400);
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	    }

	    private static class TreePanel extends JPanel {
	        private TreeNode root;
	        private int nodeRadius = 20;

	        public TreePanel(TreeNode root) {
	            this.root = root;
	        }

	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            Graphics2D g2d = (Graphics2D) g;
	            
	            g2d.setColor(new Color(203, 209, 143)); 
	            g2d.fillRect(0, 0, getWidth(), getHeight());

	            
	            drawTree(g2d, getWidth() / 3, 30, root, 0, getWidth() / 4);
	        }
	        private void drawTree(Graphics2D g2d, int x, int y, TreeNode node, int depth, int xOffset) {
	            if (node != null) {
	                int verticalGap = 50;

	                // Draw lines first
	                if (node.left != null) {
	                    int newX = x - xOffset;
	                    int newY = y + verticalGap;
	                    g2d.setColor(new Color(34, 139, 34));  // Set the color for lines
	                    g2d.drawLine(x, y, newX, newY);
	                    drawTree(g2d, newX, newY, node.left, depth + 1, xOffset / 2);
	                }

	                if (node.right != null) {
	                    int newX = x + xOffset;
	                    int newY = y + verticalGap;
	                    g2d.setColor(new Color(34, 139, 34));  // Set the color for lines
	                    g2d.drawLine(x, y, newX, newY);
	                    drawTree(g2d, newX, newY, node.right, depth + 1, xOffset / 2);
	                }

	                // Draw ellipses and text after lines
	                g2d.setColor(new Color(58, 107, 53));  // Set the color for nodes
	                g2d.fill(new Ellipse2D.Double(x - nodeRadius, y - nodeRadius, 2 * nodeRadius, 2 * nodeRadius));

	                g2d.setColor( Color.WHITE);  // Set the color for text
	                g2d.drawString(Integer.toString(node.data), x - 5, y + 5);
	            }
	        }

	    }
	}

