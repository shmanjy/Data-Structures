import java.util.Scanner;
import java.io.*;
public class LinkLL{

    public static void main(String []args){
        System.out.println("Linked list Output:");
        Linklist[]a=new Linklist[3];
        a[0]=new Linklist();
        a[1]=new Linklist();
        a[2]=new Linklist();
        String str=new String();
        int num=0;
        try{
            File file=new File("******.txt");
            Scanner scan =new Scanner(file);
            while(scan.hasNextLine()){
                str=scan.next();
            switch(str){
                case "A" :
                num=scan.nextInt();
                a[Linklist.nxtadd++].add(num);
                System.out.println("Added " +num +" to queue L" +Linklist.nxtadd);
                Linklist.nxtadd=Linklist.nxtadd%3;
                break;
                case "C" :
                 num=scan.nextInt();
                if(a[0].delete(num)){
                System.out.println("Deleted " +num);
                }else if(a[1].delete(num)){
                System.out.println("Deleted " +num);
                }else if(a[2].delete(num)){
                System.out.println("Deleted " +num);
                }else{
                System.out.println("Error" +num +"not found" +num);
                }
                break;
                case "P" :
                a[0].print();
                a[1].print();
                a[2].print();
                break;
                case "R" :
                 int A= Integer.MAX_VALUE;
                 int B=Integer.MAX_VALUE;
                 int C=Integer.MAX_VALUE;
                if(a[0].Start!=null){
                 A=(a[0].Start.data);
                }
                if(a[1].Start!=null){
                 B=(a[1].Start.data);
                }
                if(a[2].Start!=null){
                 C=(a[2].Start.data);
                }
                if((a[0].Start==null) && (a[1].Start==null) && (a[2].Start==null)){
                    System.out.println("Remove called on empty queues");
                }else{
                if((A<=B)&&(A<=C)){
                System.out.println("Removed " +A);
                a[0].delete(A);
                }else if((B<A)&&(B<=C)){
                System.out.println("Removed " +B);
                a[1].delete(B);
                }else{
                System.out.println("Removed " +C);
                a[2].delete(C);
                }

                }
                break;
            }
        }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }
}


public class Node {

	    public int data;
	    public Node link;

	    /*  Constructor  */
	    public Node(int d)
	    {
	        link = null;
	        data = d;
	    }
	    /*  Constructor  */
	    public Node(int d,Node n)
	    {
	        data = d;
	        link = n;
	    }
	}

public class Linklist{
	    public Node Start,End;
	    public static int nxtadd=0;
	    public Linklist()
	    {
	        Start = null;
	        End=null;
	    }
	    public void print(){
	        Node temp=Start;
	         System.out.print("[");
	         if(Start==null){
	            System.out.println(" ]");
	         }else{
	        while(temp!=End){
	            System.out.print( temp.data +" ");
	            temp=temp.link;
	        }
	        System.out.println(temp.data +"]");
	         }
	    }
	    public void add(int d){
	        if(Start==null)
	        {
	            Start= new Node(d);
	            End=Start;
	        }else if(Start.data>d){
	         Start= new Node(d,Start);
	        }else{
	            Node ptr=Start,prev=Start;
	            while(prev!=End){
	                if(ptr.data>d)
	                {
	                prev.link=new Node(d,ptr);
	                break;
	                }
	                prev=ptr;
	                ptr=ptr.link;
	            }
	            if(prev==End){
	               End.link=new Node(d,ptr);
	               End=End.link;
	            }
	        }
	    }
	    public boolean delete(int d){
	        if(Start==null)
	        {
	            System.out.println("list empty");
	            return false;
	        }else if(Start.data==d){
	         if(Start==End)
	         {
	             Start=null;
	             End=null;
	         }else{
	             Start=Start.link;
	         }
	         return true;
	        }else{
	            Node ptr=Start,prev=Start;
	            while(ptr!=End){
	                if(ptr.data==d)
	                {
	                prev.link=ptr.link;
	                return true;
	                }
	                prev=ptr;
	                ptr=ptr.link;
	            }
	         if(ptr==End){
	         if(End.data==d)
	         {
	             prev.link=null;
	             End=prev;
	             return true;
	         }
	         return false;
	        }

	        }
	      return false;
	    }

	}
