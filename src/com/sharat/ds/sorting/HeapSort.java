package com.sharat.ds.sorting;

import java.util.Arrays;

public class HeapSort{
	
	private int a[];
	
	private int currentSize;
	
	public void sort(int a[]) {
		this.a = a;
		currentSize = this.a.length;
		
		for (int j = currentSize / 2 - 1; j >= 0; j--) {// make random array into heap
			trickleDown(j);
		}
		
		for (int j = currentSize - 1; j >= 0; j--) // remove from heap and
		{ // store at array end
			int biggestNode = remove();
			a[j] = biggestNode;
		}
	}
	
	public int remove() // delete item with max key
	{ // (assumes non-empty list)
		int root = a[0];
		a[0] = a[--currentSize];
		trickleDown(0);
		return root;
	} // end remove()

	public void trickleDown(int index) {
		int largerChild;
		int top = a[index]; // save root
		while (index < currentSize / 2) // not on bottom row
		{
			int leftChild = 2 * index + 1;
			int rightChild = leftChild + 1;
			// find larger child
			if (rightChild < currentSize && // right ch exists?
					a[leftChild] < a[rightChild])
				largerChild = rightChild;
			else
				largerChild = leftChild;
			// top >= largerChild?
			if (top >= a[largerChild])
				break;
			// shift child up
			a[index] = a[largerChild];
			index = largerChild; // go down
		} // end while
		a[index] = top; // root to index
	} // end trickleDown()
	
	public static void main(String[] args)
	{
		int[] unsortedArray = {8,7,1,9,6,3,7,5,0,3,5,2};
		System.out.println("Unsorted Array : " + Arrays.toString(unsortedArray));
		HeapSort hs = new HeapSort();
		hs.sort(unsortedArray);
		System.out.println("Sorted Array : " + Arrays.toString(unsortedArray));
	}
}