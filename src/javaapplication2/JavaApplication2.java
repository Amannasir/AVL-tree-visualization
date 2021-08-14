
package javaapplication2;

/**
 *
 * @author Aman
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JTextField;

class Node {
	Node left, right;
	int height;
	int data;
        JLabel label;

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
        
        
	public Node()
	{
		left= null;
		right= null;
		data= 0;
		height= 0;
	}
	public Node(int x)
	{
		left= null;
		right= null;
		data= x;
		height= 0;
	}
        
        
}





class Balance{
	public int height(Node objNode)
	{
		if(objNode == null){
			return -1;
		}
		else{
			return objNode.height;
		}
	}
	public int maxValue(int leftSide, int rightSide)
	{
		if(leftSide > rightSide){
			return leftSide;
		}
		else{
			return rightSide;
		}
	}

	public Node singleRotateWithRightChild(Node objNode1)
	{
		Node objNode2 = objNode1.right;
		objNode1.right = objNode2.left;
		objNode2.left = objNode1;
		objNode1.height = maxValue(height(objNode1.left), height(objNode1.right)) + 1;
		objNode2.height = maxValue(height(objNode2.right), objNode1.height) + 1;
		return objNode2;
	}
	public Node singleRotateWithLeftChild(Node objNode2)
	{
		Node objNode1 = objNode2.left;
		objNode2.left = objNode1.right;
		objNode1.right = objNode2;
		objNode2.height = maxValue(height(objNode2.left), height(objNode2.right)) + 1;
		objNode1.height = maxValue(height(objNode1.left),objNode1.height) + 1;
		return objNode1;
	}
	public Node doubleRotateWithRightChild(Node objNode1)
	{
		objNode1.right = singleRotateWithLeftChild(objNode1.right);
		return singleRotateWithRightChild(objNode1);
	}
	public Node doubleRotateWithLeftChild(Node objNode3)
	{
		objNode3.left = singleRotateWithRightChild(objNode3.left);
		return singleRotateWithLeftChild(objNode3);
	}
}










class AVLTree extends Balance{
	private Node root;
        

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
        
	public AVLTree()
	{
		root = null;
	}
	public boolean checkEmpty()
	{
            if (root == null)
            {
            return true;
            }else 
            {
            return false;
            }
		
	}
	public void makeEmpty()
	{
		root = null;
                
	}
	public void insert(int data)
	{
		root = insert(data, root);
	}
	
	private Node insert(int x, Node objNode)
	{
		if(objNode == null){
			objNode = new Node(x);
		}
		else if(x < objNode.data)
		{
			objNode.left = insert(x, objNode.left);
                        
			if(height(objNode.left)- height(objNode.right) == 2)
			{
				if(x < objNode.left.data){
					objNode = singleRotateWithLeftChild(objNode);
				}
				else{
					objNode = doubleRotateWithLeftChild(objNode);
				}
			}
		}
		else if(x > objNode.data)
		{
			objNode.right = insert(x, objNode.right);
			if(height(objNode.right) - height(objNode.left) == 2)
			{
				if(x > objNode.right.data){
					objNode = singleRotateWithRightChild(objNode);
				}
				else{
					objNode = doubleRotateWithRightChild(objNode);
				}
			}
		}
		else{
			
		objNode.height = maxValue(height(objNode.left), height(objNode.right)) + 1;
		}
		return objNode;
	}
	
	public int totalNodes()
	{
		return totalNodes(root);
	}
	private int totalNodes(Node objNode)
	{
		if(objNode == null)
		{
			return 0;
		}
		else{
			int n = 1;
			n += totalNodes(objNode.left);
			n += totalNodes(objNode.right);
			return n;
		}
	}
	public boolean search(int nmbr)
	{
		return search(root, nmbr);
	}
	private boolean search(Node objNode, int nmbr)
	{
		boolean found = false;
		while((objNode != null) && !found)
		{
			int value = objNode.data;
			if(nmbr < value){
				objNode = objNode.left;
			}
			else if(nmbr > value){
				objNode = objNode.right;
			}
			else{
				found = true;
				break;
			}
			found = search(objNode, nmbr);
		}
		return found;
	}
	public void inOrder()
	{
		 inOrder(root);
	}
	private void inOrder(Node objNode)
	{
		if(objNode != null){
			inOrder(objNode.left);
			System.out.print(objNode.data + " ");
			inOrder(objNode.right);
		}
	}
	public void preOrder()
	{
		preOrder(root);
	}
	private void preOrder(Node objNode)
	{
		if(objNode != null){
			System.out.print(objNode.data + " ");
		    preOrder(objNode.left);
		    preOrder(objNode.right);
		}
	}
	public void postOrder()
	{
		postOrder(root);
	}
	private void postOrder(Node objNode)
	{
		if(objNode != null){
		    postOrder(objNode.left);
		    postOrder(objNode.right);
		    System.out.print(objNode.data + " ");
		}
	}
}


class MyFrame{
       private JFrame objJFrame ;
       private JLabel lblInsert ;
        private JButton btnInsert;
        
       private JLabel lblSearch;
       private JButton btnSearch;
       
       private JTextField txtValue;
       private JTextField txtSearch;
       
       private JButton btnCountNodes ;
       private JLabel btnCountNodesValue ;
       
       private JButton btnCheckEmpty ;
       private JLabel  btnCheckEmptyValue ;
       
       private JButton btnClearTree ;
        private JLabel btnClearTreeValue ;
        
        JFrame frame;
        
        int Leftmargin = 50;
        int Rightmargin = 50;
        int y = 30;
        
        public MyFrame( AVLTree objTree){
            objJFrame = new JFrame ("\t\t\t AVL TREE ");
            objJFrame.setSize(300,200);
            objJFrame.setVisible(true);
            objJFrame.setLayout(new GridLayout(6,3));
            
            
        lblInsert = new  JLabel("Insert a number ");
        txtValue = new JTextField();
        btnInsert = new JButton("Insert");
        
        objJFrame.add(lblInsert) ;
        objJFrame.add(txtValue) ;
        objJFrame.add(btnInsert) ;
        
        
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = (JPanel) frame.getContentPane();
        panel.setLayout(null);
        
                  

        frame.setSize(500, 600);
        frame.setVisible(true);
        
         
        
        btnInsert.addActionListener(new ActionListener(){
           
              public void actionPerformed(ActionEvent event) {  
                  
               int value =   Integer.parseInt( txtValue.getText().toString());
                  
                  
                  
               objTree.insert(value);  
                
                
                
                Node InsertNode =  objTree.getRoot();
                 
                JLabel label = new JLabel(Integer.toString(value));
                panel.add(label);
                Dimension size = label.getPreferredSize();
                
                if(objTree.checkEmpty() == true)
                {
                
                 label.setBounds(50,20 , 150 ,200);
                 System.out.println("Root Node");
                 label.setForeground(Color.RED);
               
                
                    objTree.insert(value);  
                }else 
                {
                
                    objTree.insert(value);  
                
                
                
                y = y + 20;
                if(objTree.getRoot().getData() < value)
                {
                   
                    
                     Rightmargin =  Rightmargin + 30;
                    label.setBounds(Rightmargin, y, 500 ,200);
                     System.out.println("right");
                 label.setForeground(Color.BLUE);
                   
                    
    
                }else 
                {
                
                   Leftmargin =  Leftmargin - 10;
                    
                    label.setBounds(Leftmargin, y, 600 ,200);
                     System.out.println("left");
               
                
                }
                }
                
                  
                  
                  
                  
                  
              }

              
        });
        
        lblSearch = new JLabel ("Search number  ");
        txtSearch= new JTextField();
        btnSearch = new JButton("Search");
                
        objJFrame.add(lblSearch);
        objJFrame.add(txtSearch);
        objJFrame.add(btnSearch);
       
        
        
        
        btnSearch.addActionListener(new ActionListener(){
           
              public void actionPerformed(ActionEvent event) {      
      
                  int value =  Integer.parseInt(txtSearch.getText().toString());
                  boolean foundOrnot = objTree.search(value);
                  if(foundOrnot == true)
                  {}
                  
                  
                  
              }

              
        });
        
        
        
        btnCountNodes = new JButton("Count Node");
        btnCheckEmpty = new JButton("Check Empty");
        btnClearTree = new JButton ("Clear Tree") ;
        objJFrame.add(btnCountNodes);
        objJFrame.add(btnCheckEmpty);
        objJFrame.add(btnClearTree);
        
        btnCountNodes.addActionListener(new ActionListener(){
             // @Override
              public void actionPerformed(ActionEvent event) {      
             btnCountNodesValue = new JLabel(Integer.toString( objTree.totalNodes()));
             objJFrame.add(btnCountNodesValue) ;
              objJFrame.setVisible(true);
              }

               /* @Override
                public void actionPerformed(ActionEvent ae) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }*/
        });
        
        btnCheckEmpty.addActionListener(new ActionListener(){
             // @Override
              public void actionPerformed(ActionEvent event) {    
                  if(objTree.checkEmpty()== true){
                      btnCheckEmptyValue = new JLabel("Empty");
                      objJFrame.add(btnCheckEmptyValue) ;
                  }else {
                      btnCheckEmptyValue = new JLabel(" Not Empty");
                      objJFrame.add(btnCheckEmptyValue) ;
                  }
             
              objJFrame.setVisible(true);
              }    
        });
        
        btnClearTree.addActionListener(new ActionListener(){
           
              public void actionPerformed(ActionEvent event) {      
              btnClearTreeValue = new JLabel(" Tree Cleared " );
              objTree.makeEmpty();
              objJFrame.add(btnClearTreeValue) ;
              objJFrame.setVisible(true);
              }

              
        });
        
        
                
        }

  
}





public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        
       
        
     
        do    
        {
            new MyFrame(objAVLTree);
            System.out.print("\nPost order : ");
            objAVLTree.postOrder();
            System.out.print("\nIn order : ");
            objAVLTree.preOrder();
            System.out.print("\nPre order : ");
            objAVLTree.inOrder();
            System.out.println("\nDo you want to continue (Y/N) \n");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');  
    }
    
}
