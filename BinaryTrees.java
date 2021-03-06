import java.io.*;

public class GenBinTree<T>{
public Node<T> root;

   public GenBinTree()
     {
         root = null;
     }

 /*
  Add operation
 */
   public void add(T d)
     {
         if (root==null){                                                       //Comparing root with the null
             root=new Node(d);
             System.out.println(d +" Added as root to the binary tree");
         }
     }

 //To Perform Insertion(Add operation)
    public void add(String s,T d)
     {
         Node ptr=root;
         char temp;
         int i=0;
         try{

            if(!find(d)){
            	                                                                  //Calculating the length of the string and finding the path that needs to traverse
            	                                                                  //Like if "LLR" then it calculates the string length as 3-1= 2
            	                                                                  //so only two values 0,1 positions i.e L and L are calculated in while loop

            	while(i<(s.length()-1)){

        	 temp=s.charAt(i);

             if(temp=='L')
             {
                 ptr=ptr.left;                                                  //traversing to the left
             }else{
                 ptr=ptr.right;                                                 //traversing to the right
             }
             i++;
         }

         //In the above while loop we are calculated the path now we need to find the position where it needs to be added
         //Here string length is again calculated and then 3-1=2 so its pointing to the 3rd position i.e the letter "R"
         //and then perform the add operation on that side
         if(i==s.length()-1){
             if(s.charAt(i)=='L')
             {
                 if(ptr.left==null){
                 ptr.left=new Node(d);
                 }else{
                 throw new IOException(" Error : Cannot override");             //Throws an exception when it tries to add the node on an already existing node on left
                 }
             }else{
                  if(ptr.right==null){
                 ptr.right=new Node(d);
                 }else{
                 throw new IOException(" Error : Cannot override");             //Throws an exception when it tries to add the node on an already existing node on right
                 }
             }
             System.out.println(d +" Added to the Binary Tree");
         }
            }else{
             throw new IOException(" Error \"Duplicate data\": data already present in Binary tree");//Already existing data so its an error
         }
         }catch(NullPointerException e){
         System.out.println("Add " +s +"," +d +" Error : Path " +s +" not present in Binary tree");
         }catch(IOException e){
         System.out.println("Add " +s +"," +d +e.getMessage());
         }
         }




//To count the number of nodes present
    public int countNodes()
     {
         return countNodes(root);
     }

     private int countNodes(Node ptr)
     {
         if (ptr == null)                                                       //When there is no Node then return 0
             return 0;
         else
         {
             int l = 1;                                                         //  1 because there will be at least 1 node present i.e root
             l += countNodes(ptr.left);                                         //Traversing so as to count the number of left nodes
             l += countNodes(ptr.right);                                        //Traversing so as to count the number of right nodes
             return l;
         }
     }




//To find the node
     public boolean find(T d)
     {
         return find(root,d);
     }

     private boolean find(Node ptr,T d)
     {
         if (ptr == null)
             return false;
         else
         {
             if(ptr.data==d){
                return true;
             }
             if(find(ptr.left,d)){                                              //Traversing for the left node to find the value
                return true;
             }
             if(find(ptr.right,d)){                                             //Traversing for the right node to find the value
                return true;
             }
             return false;
         }
     }



//To perform Remove operation
     public void remove(T d)
     {
        try{
            if(root==null){
             throw new IOException(" Error:Binary tree is empty");
            }
            if(root.data==d){
             throw new IOException(" Error:Cannot remove root of Binary tree");
            }
            if(!remove(root,root,d)){
                throw new IOException(" Error:Node not found in Binary tree in the leaf");
            }
        }catch(Exception e){
         System.out.println("Remove " +d +e.getMessage());
     }
     }


     private boolean remove(Node prev,Node ptr,T d)
     {
         if (ptr == null)
             return false;
         else
         {
             if((ptr.data==d) && (ptr.left == null) && (ptr.right == null)){
                 if(prev.left==ptr){
                     prev.left=null;
                     System.out.println("removed leaf with data: " +d);
                      return true;
                 }
                 if(prev.right==ptr){
                     prev.right=null;
                     System.out.println("removed leaf with data: " +d);
                     return true;
                 }
                return false;
             }
             if(remove(ptr,ptr.left,d)){
                return true;
             }
             if(remove(ptr,ptr.right,d)){
                return true;
             }
             return false;
         }
     }



/*
  To perform Swap of the children
*/
    public void swap(T d)
    {
      swap(root,d);
    }

    private boolean swap(Node ptr,T d)
    {
        if (ptr == null)
            return false;                                                       //If the Node is null return false
        else
        {
            if(ptr.data==d){                                                    //checking if the Node data is same as the data on which the swap needs to be performed
               Node temp;
               temp=ptr.left;
               ptr.left=ptr.right;
               ptr.right=temp;
               System.out.println("Swaped the children of the Node containing data " +d);
               return true;//After swapping return true
            }
            if(swap(ptr.left,d)){                                               //Traversing for the entire left child till you find the node call it recursively
               return true;
            }
            if(swap(ptr.right,d)){                                              //Traversing for the entire right child till you find the node call it recursively
               return true;
            }
            return false;
        }
    }

/*
  To perform Rotate Right operation
*/
    public void rotate_right(T d) {
      root = rotate_right(root, d) ;
     }

    public Node rotate_right(Node ptr, T d) {
        Node prev;
        if(ptr != null)
        {
        	//Checking the condition so that we are performing on the node and
        	//for rotate right the node left child or pointer must not be null
          if(ptr.data == d && ptr.left != null) {


                prev = ptr.left;
                ptr.left = prev.right;
                prev.right = ptr;
                System.out.println("Right Rotation is performed on " + d);
                return prev;
           }
           else {
             if(ptr.data == d && ptr.left == null)
                    System.out.println("Right Rotation is not possible on the node "+d);
                }

                  ptr.left =rotate_right(ptr.left,d);

                  ptr.right=rotate_right(ptr.right,d);
        }

        return ptr;
    }


//To perform Rotate Left
    public void rotate_left(T d) {

        root = rotate_left(root, d) ;

     }

     public Node rotate_left(Node ptr, T d) {
         Node prev;
         if(ptr != null)
         {
        	//Checking the condition so that we are performing on the node and
         	//for rotate left the node right child or pointer must not be null
          if(ptr.data == d && ptr.right != null) {


                 prev = ptr.right;
                 ptr.right = prev.left;
                 prev.left = ptr;
                 System.out.println("Left Rotation is performed on " + d);
                 return prev;
            }
         else {
             if(ptr.data == d && ptr.right == null)
            	     System.out.println("Left Rotation is not possible on the node "+d);
              }

              ptr.right =rotate_right(ptr.right,d);

              ptr.left=rotate_right(ptr.left,d);
        }

         return ptr;
      }

//To perform the mirror operation
      public void mirror()
      {
          try{
             if(root==null){
              throw new IOException(" Error:Binary tree is empty");
             }
             mirror(root);
             }catch(Exception e){
          System.out.println("Mirror " +e.getMessage());
             }
      }

      private void mirror(Node ptr)
     {
         if (ptr != null)
         {
             mirror(ptr.left);
             mirror(ptr.right);
             Node temp;
             temp=ptr.left;
             ptr.left=ptr.right;
             ptr.right=temp;
         }
     }

//To print the tree in INORDER TRAVERSAL
     public void Print()
     {
         Print(root);
     }

     private void Print(Node ptr)
     {
         if (ptr != null)
         {

             Print(ptr.left);
             System.out.println(ptr.data +" ");
             Print(ptr.right);
         }
     }

}

public class BinaryTrees {

	public static void main(String[] args) {

		   GenBinTree<String> myTree = new GenBinTree<>();

        myTree.add("500");
	      myTree.add("L", "200");
	      myTree.add("R", "600");
	      myTree.add("LL", "150");
	      myTree.add("LR", "250");
        myTree.add("RR", "650");
        myTree.add("RL", "550");
        myTree.add("LLR", "175");
	      myTree.add("LLL", "100");
        myTree.Print();
        System.out.println(myTree.countNodes());
        myTree.remove("100");
        myTree.Print();
        myTree.find("250");
        myTree.Print();
        myTree.mirror();
        myTree.Print();
        myTree.rotate_left("150");
        myTree.Print();
        myTree.rotate_right("250");
	      myTree.Print();
	      myTree.swap("150");
        myTree.Print();
	}


}

public class Node<T>{
    public T data;
    public Node<T> left,right;

    public Node(T d){
       data=d;
       left =null;
       right=null;
    }
}
