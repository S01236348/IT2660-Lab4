import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

/*

  1. If you were implementing a sort algorithm for a new language, which sort would you use?
  I would use insertion sort. (See next answer.)

  2. Was there a difference in the time it took for bubble and insertion sort to run? Does this make sense given the time complexities for these sorting algorithms?
  Even though insertion sort and bubble sort both have a Big O value of O(n^2),
  insertion sort ran much more quickly in practice. Insertion benefits from finishing a loop once an element 
  has been placed. Bubble sort iterates through the remainder of the array.

  3. Which sort algorithm has an easier implemenation (in terms of understanding) to you?  
  Although the code for bubble sort is technically longer, it makes more sense to me in that its only responsibility is to compare and possibly swap two neighboring elements,
  without needing to relocate either value at a specific place in the array.
  
  Note: When used as provided, the code resulted in an ordered version of integerList, even though it had been ordered within the method
  and stored in a new variable (bubbleSortedList). This caused the second sort algorithm to be provided with an already-sorted list, which 
  boosted its performance significantly. integerList = Lab4.getList() was added to eliminate the effect.

  */



    public static void main(String[] args) {
      System.out.println("Unsorted Array ---------------------------------------------------");
      ArrayList<Integer> integerList = Lab4.getList();
      Lab4.outputList(integerList);

      System.out.println("\n\nBubble sort results ----------------------------------------------");

      long startTime = System.nanoTime();
      ArrayList<Integer> bubbleSortedList = Lab4.bubbleSort(integerList);
      long endTime = System.nanoTime();
      long elapsedTime = endTime - startTime;
      System.out.println(elapsedTime);

      Lab4.outputList(bubbleSortedList);

      System.out.println("\n\nInsertion sort results -------------------------------------------");
      
      integerList = Lab4.getList(); // reload to restore unsorted version

      startTime = System.nanoTime();
      ArrayList<Integer> insertSortedList = Lab4.insertionSort(integerList);
      endTime = System.nanoTime();
      elapsedTime = endTime - startTime;
      System.out.println(elapsedTime);

      Lab4.outputList(insertSortedList);

    }
}

class Lab4 {
  public static ArrayList<Integer> insertionSort(ArrayList<Integer> integerList) {
    // Step 1 - Implement insertion sort algorithm here
    for (int i = 1; i < integerList.size(); i++){
      int currentElement = integerList.get(i);
      int k;
      for (k = i -1; k >= 0 && integerList.get(k) > currentElement; k--){
        integerList.set(k + 1, integerList.get(k));
      }
      integerList.set(k + 1, currentElement);
    }
    return integerList;
  }

  public static ArrayList<Integer> bubbleSort(ArrayList<Integer> integerList) {
    // Step 2 - Implement the bubble sort algorithm here
    boolean needNextPass = true;
    for (int k = 1; k < integerList.size() && needNextPass; k++){
      needNextPass = false;
      for (int i = 0; i < integerList.size() - k; i++){
        if(integerList.get(i) > integerList.get(i + 1)){
          int temp = integerList.get(i);
          integerList.set(i, integerList.get(i + 1));
          integerList.set(i + 1, temp);
          needNextPass = true;
        }
      }
    }
    return integerList;
  }

  public static ArrayList<Integer> getList() {
    ArrayList<Integer> integerList = new ArrayList<>();
    String line;
    try (BufferedReader br = new BufferedReader(new FileReader("integers.txt"))) {
        while ((line = br.readLine()) != null) {
            integerList.add(Integer.parseInt(line));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return integerList;
  }

  public static void outputList(ArrayList<Integer> integerList) {
    for (int i = 0; i < integerList.size(); i++) {
        System.out.print(integerList.get(i) + " ");
    }
  }
}