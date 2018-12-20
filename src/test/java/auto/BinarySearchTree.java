package auto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearchTree {


    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("//ASSRTT1/dsd/sdfdfe/dfdsrf/essZdfrsfs");
        list.add("//ASSRTT2/dsd/sdfdfe/dfdsrf/essZdfrsfs");
        list.add("//ASSRTT3/dsd/sdfdfe/dfdsrf/essZdfrsfs");
        list.add("//ASSRTT4/dsd/sdfdfe/dfdsrf/essZdfrsfs");
        list.add("//ASSRTT5/dsd/sdfdfe/dfdsrfessZdfrsfs");
        list.add("//ASSRTT6/dsd/sdfdfe/dfdsrfessZdfrsfs");
        list.add("//ASSRTT7/dsd/sdfdfe/dfdsrfessZdfrsfs");
        list.add("//ASSRTT8/dsd/sdfdfe/dfdsrfessZdfrsfs");
        list.add("//ASSRTT9/dsd/sdfdfe/dfdsrfessZdfrsfs");
        list.add("//ASSRTT10 dsd/sdfdfe/dfdsrfessZdfrsfs");
        Collections.sort(list);
        //Collections.reverse(list);
        String searchValue = "//ASSRTT7/dsd/sdfdfe/dfdsrfessZdfrsfs";
        int indexOfElement = Collections.binarySearch(list, searchValue);
        System.out.println("Element " + searchValue + " was found at index " + indexOfElement);
        //System.out.println("Here is the value = " +list.get(Collections.binarySearch(list, searchValue)));
        System.out.println("Here is the value = " +list.get(indexOfElement));
        
        
        
//        for (Integer integer : list) {
//            System.out.println(integer);
//        }
    }
}
