package auto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearchTree {


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(93);
        list.add(102);
        list.add(7);
        list.add(49);
        list.add(75);
        list.add(1);
        list.add(54);
        list.add(63);
        list.add(81);
        list.add(32);
        Collections.sort(list);
        //Collections.reverse(list);
        int searchValue = 32;
        int indexOfElement = Collections.binarySearch(list, searchValue);
        System.out.println("Element " + searchValue + " was found at index " + indexOfElement);
        System.out.println("Here is the value = " +list.get(Collections.binarySearch(list, searchValue)));
        
        
//        for (Integer integer : list) {
//            System.out.println(integer);
//        }
    }
}
