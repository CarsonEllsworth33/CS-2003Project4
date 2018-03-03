package main;

import heaps.*;
import trees.*;
import sortingAlgrms.SortsClass;
import java.util.Random;
//import java.util.LinkedList;
//import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class SortingManager {

	private int ListSize = 30000;
	private Comparable[] Sorted = new Comparable[ListSize];
	private Comparable[] unSorted = new Comparable[ListSize];
	private double startTime;
	private double endTime;
	private double avrgTime;
	
	
	private void ArrayGenerator() {
		Random rand = new Random();
		for (int i = 0; i < ListSize; i++) {
			Sorted[i] = i;
		}
		for (int i = 0; i < ListSize; i++) {
			unSorted[i] = rand.nextInt();
		}
	}

	private double sort(Comparable[] L1,boolean random) {
		Comparable[] L2 = L1.clone();
		startTime = System.currentTimeMillis();
		// this is where you change the current sorting algorithm
		SortsClass.quickSort(L2, 0, this.ListSize-1,random);
		endTime = System.currentTimeMillis();
		avrgTime = endTime - startTime;
		return avrgTime;
	}

	public static void main(String... strings) {
		SortingManager sortManager = new SortingManager();
		sortManager.ArrayGenerator();
		// this is where the file name is changed
		String fileName = String.format("AssignmentDataPt1/RandomPivotQuickSort/ComparativeQuickSortRT%d.txt", sortManager.unSorted.length);
		File file = new File(fileName);
		try {
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (file.exists()) {
				try {
					int count = 1;
					double[] runtimeArray = new double[10];
					double runTime;
					PrintWriter pw = new PrintWriter(file);
					for (; count <= 10; count++) {
						sortManager.ArrayGenerator();
						System.out.println("generator done");
						runTime = sortManager.sort(sortManager.Sorted, true);
						String message = String.format("%nsorted, %s, random pivot %s",true, true);
						pw.println(message);
						message = String.format("Run, %d%nSize of Array, %d%nTime(ms) to finish, %.2f", count,
								sortManager.unSorted.length, runTime);
						runtimeArray[count - 1] = runTime;
						pw.println(message);
						
						runTime = sortManager.sort(sortManager.Sorted, false);
						message = String.format("%nsorted, %s, random pivot %s",true, false);
						pw.println(message);
						message = String.format("Run, %d%nSize of Array, %d%nTime(ms) to finish, %.2f", count,
								sortManager.unSorted.length, runTime);
						runtimeArray[count - 1] = runTime;
						pw.println(message);
						
						
						runTime = sortManager.sort(sortManager.unSorted, true);
						message = String.format("%nsorted, %s, random pivot %s",false, true);
						pw.println(message);
						message = String.format("Run, %d%nSize of Array, %d%nTime(ms) to finish, %.2f", count,
								sortManager.unSorted.length, runTime);
						runtimeArray[count - 1] = runTime;
						pw.println(message);
						
						
						runTime = sortManager.sort(sortManager.unSorted, false);
						message = String.format("%nsorted, %s, random pivot %s",false, false);
						pw.println(message);
						message = String.format("Run, %d%nSize of Array, %d%nTime(ms) to finish, %.2f", count,
								sortManager.unSorted.length, runTime);
//						runtimeArray[count - 1] = runTime;
						pw.println(message);
						
						
						System.out.printf("Run %d done%n", count);

					}
//					runTime = 0;
//					for (double i : runtimeArray) {
//						runTime += i;
//					}
//					pw.println(String.format("%nTotal avrg time, %.2f%n", (runTime / runtimeArray.length)));
					pw.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				System.out.println("file is Bad");
			}

		} finally {
			System.out.println("\n\nCompleted");
		}
	}
}
