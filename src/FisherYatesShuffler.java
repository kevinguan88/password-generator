//Author: Kevin Guan
//Randomly shuffles the order of elements in a given array by implementation of the Fisher-Yates algorithm
//Time Complexity: O(n)

import java.util.Random;

public class FisherYatesShuffler {

    //Fisher-Yates shuffle algorithm that takes an array as an argument
    //Begins at last index of array, randomly selects an unshuffled element and swaps places
    //Decrements until entire array is shuffled
    public int[] shuffle(int[] array) {
        int lastIndex = array.length - 1;
        Random generator = new Random();
        //don't need for loop as then end point will always be 0
        //can achieve same effect with while loop with less overhead
        while (lastIndex > 0) {
            int randomIndex = generator.nextInt(0, lastIndex);
            int randomValue = array[randomIndex];
            array[randomIndex] = array[lastIndex];
            array[lastIndex] = randomValue;
            lastIndex -= 1;
        }
        return array;
    }

    //char variant of shuffle method
    public char[] shuffle(char[] array) {
        int lastIndex = array.length - 1;
        Random generator = new Random();
        while (lastIndex > 0) {
            int randomIndex = generator.nextInt(0, lastIndex);
            char randomValue = array[randomIndex];
            array[randomIndex] = array[lastIndex];
            array[lastIndex] = randomValue;
            lastIndex -= 1;
        }
        return array;
    }

}
