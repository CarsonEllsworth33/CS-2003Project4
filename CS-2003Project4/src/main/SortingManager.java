package main;
import heaps.*;
import trees.*;
import sortingAlgrms.SortsClass;
import java.util.Random;
//import java.util.LinkedList;
//import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
public class SortingManager {
	
	private int ListSize = 10000;
	private static Comparable[] L1;
	private double startTime;
	private double endTime;
	private double avrgTime;
	
	private void ArrayGenerator(){
		L1 = new Comparable[ListSize];
		Random rand = new Random();
		for(int i = 0; i < ListSize; i++) {
			L1[i] = rand.nextInt();
		}	
		ListSize += 10000;
	}
	
	private double sort() {
		startTime = System.currentTimeMillis();
		SortsClass.selectionSort(L1, L1.length);
		endTime = System.currentTimeMillis();
		avrgTime = endTime - startTime;
		return avrgTime;
	}
	
	public static void main(String...strings ) {
		SortingManager sortManager = new SortingManager();
		String fileName = "";
		File file = new File(fileName);
		if(file.exists()&&file.canRead()&&file.canWrite()) {
			sortManager.ArrayGenerator();
			sortManager.sort();
		}
		else {
			System.out.println("file is Bad");
		}
	}
}
