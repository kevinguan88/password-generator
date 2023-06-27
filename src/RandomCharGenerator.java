//generates an array of a specified type of character
import java.util.Arrays;
import java.util.Random;
public class RandomCharGenerator {
    public char[] generateUpper(char[] array){
        Random generator = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = Character.toUpperCase((char)(generator.nextInt(26) + 'a'));
        }
        return array;
    }


    public char[] generateLower(char[] array){
        Random generator = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = (char)(generator.nextInt(26) + 'a');
        }
        return array;
    }

    public char[] generateNumber(char[] array){
        Random generator = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = (char)(generator.nextInt(9) + '1');
        }
        return array;
    }

    //TODO: use predefined set of special chars instead
    public char[] generateSpecial(char[] array){
        Random generator = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = (char)(generator.nextInt(3) + '$');
        }
        return array;
    }
}

