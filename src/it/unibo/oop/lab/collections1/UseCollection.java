package it.unibo.oop.lab.collections1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Example class using {@link java.util.List} and {@link java.util.Map}.
 * 
 */
public final class UseCollection {

	private static final int TO_MS = 1_000_000;
	
    private UseCollection() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
    	final ArrayList<Integer> aList = new ArrayList<Integer>();
    	
    	for (int i = 1_000; i < 2_000; i++) {
    		aList.add(i);
    	}
    	
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
    	final LinkedList<Integer> lList = new LinkedList<Integer>(aList);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
    	final int temp = aList.get(aList.size()-1);
    	aList.set(aList.size()-1, aList.get(0));
    	aList.set(0, temp);
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
    	for (var i : aList) {
    		System.out.println(i);
    	}
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
    	long time = System.nanoTime();
    	for (int i = 0; i < 100_000; i++) {
    		aList.add(0, i);
    	}
    	time = System.nanoTime() - time;
    	System.out.println("Adding 100.000 elements as the first element in an ArrayList took "
    			+ time + "ns (" + time / TO_MS + "ms)");
    	time = System.nanoTime();
    	for (int i = 0; i < 100_000; i++) {
    		lList.addFirst(i);
    	}
    	time = System.nanoTime() - time;
    	System.out.println("Adding 100.000 elements as the first element in a LinkedList took "
    			+ time + "ns (" + time / TO_MS + "ms)");
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
    	time = System.nanoTime();
    	for (int i = 0; i < 1_000; i++) {
    		int middle = aList.get(aList.size() / 2 - 1);
    	}
    	time = System.nanoTime() - time;
    	System.out.println("Reading the element in the middle of an ArrayList 1.000 times took "
    			+ time + "ns (" + time / TO_MS + "ms)");
    	time = System.nanoTime();
    	for (int i = 0; i < 1_000; i++) {
    		int middle = lList.get(lList.size() / 2 - 1);
    	}
    	time = System.nanoTime() - time;
    	System.out.println("Reading the element in the middle of a LinkedList 1.000 times took "
    			+ time + "ns (" + time / TO_MS + "ms)");
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         * 
         * Africa -> 1,110,635,000
         * 
         * Americas -> 972,005,000
         * 
         * Antarctica -> 0
         * 
         * Asia -> 4,298,723,000
         * 
         * Europe -> 742,452,000
         * 
         * Oceania -> 38,304,000
         */
    	final Map<String, Long> worldMap = new HashMap<>();
    	worldMap.put("Africa", 1_110_635_000L);
    	worldMap.put("Americas", 972_005_000L);
    	worldMap.put("Antartica", 0L);
    	worldMap.put("Asia", 4_298_723_000L);
    	worldMap.put("Europe", 742_452_000L);
    	worldMap.put("Oceania", 38_304_000L);
        /*
         * 8) Compute the population of the world
         */
    	long worldPopulation = 0;
    	for (var i : worldMap.values()) {
    		worldPopulation += i;
    	}
    	System.out.println("World Popupation: " + worldPopulation);
    }
}