//Splits an integer into a given number of random values that add up to the original integer value

import java.util.Arrays;
import java.util.Random;

public class IntSplitter {

    public void main(String[] args) {

    }

    //takes value to split and the quantity of parts to split it into
    public int[] split(int value, int parts) {
        int[] array = new int[parts];
        Random generate = new Random();
        for (int i = 0; i < parts; i++) {
            //the max value we can add is (current value) - (the # of total parts) + (the # of parts added)
            int partVal = generate.nextInt(1, value - parts + i);
            array[i] = partVal;
            //the value of the added part is subtracted to generate a new max value to add based on the remaining value
            value = value - partVal;
        }
        //last value is added to the last index
        array[parts -1] += value;
        return array;
    }


}
