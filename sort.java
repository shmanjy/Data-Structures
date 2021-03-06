import java.util.Random;

import java.util.*;

import java.util.Scanner;


public class sort {



 /**
     * Simple insertion sort.

* @param a an array of Comparable items.

  */

public static <AnyType extends Comparable<? super AnyType>>

	    void insertionSort( AnyType [ ] a )

   {

   int j;


 for( int p = 1; p < a.length; p++ )

	        {

 AnyType tmp = a[ p ];

         for( j = p; j > 0 && tmp.compareTo( a[ j - 1 ] ) < 0; j-- )

                a[ j ] = a[ j - 1 ];

           a[ j ] = tmp;

        }
	    }


	 /**
	     * Quicksort algorithm.

     * @param a an array of Comparable items.

     */


public static <AnyType extends Comparable<? super AnyType>>

  void quicksort( AnyType [ ] a )

   {

quicksort( a, 0, a.length - 1 );

  }


private static final int CUTOFF = 3;


	/**

  * Method to swap to elements in an array.

   * @param a an array of objects.

  * @param index1 the index of the first object.

  * @param index2 the index of the second object.

  */


public static <AnyType> void swapReferences( AnyType [ ] a, int index1, int index2 )

  {

AnyType tmp = a[ index1 ];

    a[ index1 ] = a[ index2 ];

      a[ index2 ] = tmp;

  }


 /**

* Return median of left, center, and right.

     * Order these and hide the pivot.

  */


private static <AnyType extends Comparable<? super AnyType>>
    AnyType median3( AnyType [ ] a, int left, int right )

   {

 int center = ( left + right ) / 2;

  if( a[ center ].compareTo( a[ left ] ) < 0 )

  swapReferences( a, left, center );

   if( a[ right ].compareTo( a[ left ] ) < 0 )

         swapReferences( a, left, right );

  if( a[ right ].compareTo( a[ center ] ) < 0 )

     swapReferences( a, center, right );


     // Place pivot at position right - 1

   swapReferences( a, center, right - 1 );

   return a[ right - 1 ];
    }


 /**

* Internal quicksort method that makes recursive calls.

    * Uses median-of-three partitioning and a cutoff of 10.

  * @param a an array of Comparable items.

  * @param left the left-most index of the subarray.

   * @param right the right-most index of the subarray.

   */


private static <AnyType extends Comparable<? super AnyType>>
    void quicksort( AnyType [ ] a, int left, int right )

   {

 if( left + CUTOFF <= right )

   {

AnyType pivot = median3( a, left, right );


               // Begin partitioning

     int i = left, j = right - 1;

  for( ; ; )

 {

 while( a[ ++i ].compareTo( pivot ) < 0 ) { }

           while( a[ --j ].compareTo( pivot ) > 0 ) { }

             if( i < j )

         swapReferences( a, i, j );

             else

  break;

  }


 swapReferences( a, i, right - 1 );
// Restore pivot


quicksort( a, left, i - 1 );
  // Sort small elements

     quicksort( a, i + 1, right );
   // Sort large elements

 }

 else
// Do an insertion sort on the subarray

       insertionSort( a, left, right );

   }


/**
     * Internal insertion sort routine for subarrays

 * that is used by quicksort.

  * @param a an array of Comparable items.

 * @param left the left-most index of the subarray.

   * @param right the right-most index of the subarray.

    */


private static <AnyType extends Comparable<? super AnyType>>
    void insertionSort( AnyType [ ] a, int left, int right )

   {

  for( int p = left + 1; p <= right; p++ )

      {

AnyType tmp = a[ p ];

   int j;


 for( j = p; j > left && tmp.compareTo( a[ j - 1 ] ) < 0; j-- )

             a[ j ] = a[ j - 1 ];

          a[ j ] = tmp;

  }

  }




public static <AnyType extends Comparable<? super AnyType>>//Initally the max value which is present is entered by the user
    void bucketSort(AnyType [ ] array, int max){

		int mv = max+1;//using that value we are creating an array "count" with size max+1
int []count = new int[mv];

	int num = array.length;//and the num variable contains the max length of the initial unsorted array


int index  =0;

int [] sort = new int[num];// creating a new sort array with the initial size




 for(int i=0;i<num;i++){//Taking each value in the unsorted array and storing their repeatance value in respective value in index

 	 Integer temp = (Integer) array[i];

    	count[temp]++;

  }


 for(int i=0; i<mv; i++){// Now we are copying the index which are not zero to the new sorted array and that number is repeated depending on the
                         //number of times its sotred in count array.


int times = count[i];

    	if(times!=0){


 for(int j=0; j< times; j++){


sort[index]=i;

    		index+=1;

		  }

      	}

   }

  }





 public static void main( String [ ] args )
    {




 System.out.println("Enter the Max value:");

    Scanner sc = new Scanner(System.in);

		int max= sc.nextInt();

// creating a random values for an array whose upper limit is given by the max value by the user
		Random rand = new Random();


		Integer [] array= new Integer [max];


		for(int i=0;i<max;i++){

      	int j = rand.nextInt(max) +1;

	array[i] = j;

   }

// calculating the running time for bucket sort
long start = System.currentTimeMillis();

       bucketSort(array, max);

     long stop = System.currentTimeMillis();

     long diff = stop- start;

 System.out.println("The time taken for Bucket Sort is : " + diff);


// calculating the running time for insertion sort
        long start1 = System.currentTimeMillis();

      insertionSort(array);

  long stop1 = System.currentTimeMillis();

       long diff1 = stop1- start1;

      System.out.println("The time taken for Insertion Sort is : " + diff1);

// calculating the running time for quick sort
       long start2 = System.currentTimeMillis();

      quicksort(array);

  long stop2 = System.currentTimeMillis();

  long diff2 = stop2- start2;

     System.out.println("The time taken for Quick Sort is : " + diff2);


	}

   }
