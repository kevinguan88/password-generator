import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class PasswordGenerator {
    //have global data:
    //password length, special characters, numbers, uppercase, lowercase
    private int passwordLength;
    private ArrayList<String> optionsIncluded = new ArrayList<>();

    private boolean hasLowerCase;
    private char[] lowerArray;
    private boolean hasUpperCase;
    private char[] upperArray;
    private boolean hasNumbers;
    private char[] numArray;
    private boolean hasSpecialChar;
    private char[] specialArray;

    public PasswordGenerator() {
        setPasswordLength(8);
        setHasLowerCase(false);
        setHasUpperCase(false);
        setHasNumbers(false);
        setHasSpecialChar(false);
    }

    public PasswordGenerator(int passwordLength, boolean hasLowerCase, boolean hasUpperCase, boolean hasNumbers, boolean hasSpecialChar) {
        setPasswordLength(passwordLength);
        setHasLowerCase(hasLowerCase);
        setHasUpperCase(hasUpperCase);
        setHasNumbers(hasNumbers);
        setHasSpecialChar(hasSpecialChar);
    }

    public String generatePassword() {
        FisherYatesShuffler shuffler = new FisherYatesShuffler();
        setOptionsIncluded();

        IntSplitter splitter = new IntSplitter();
        int[] partsArray = splitter.split(this.passwordLength, this.optionCounter());
        assignAndFillParts(partsArray);
        char[] combinedArray = this.combineArrays();
        //shuffle combined array
        char[] passwordArray = shuffler.shuffle(combinedArray);
        String password = "";
        //converting the password array to a string
        for (char i : passwordArray) {
            password += i;
        }
        return password;
    }

    //helper method that combines multiple arrays into one
    private char[] combineArrays() {
        char[] passwordArray = new char[passwordLength];
        int passIndex = 0;
        for (int i = 0; i < this.optionsIncluded.size(); i++) {
            char[] workingArray = null;
            switch (this.optionsIncluded.get(i)) {
                case "lower":
                    workingArray = this.lowerArray;
                    break;
                case "upper":
                    workingArray = this.upperArray;
                    break;
                case "number":
                    workingArray = this.numArray;
                    break;
                case "special":
                    workingArray = this.specialArray;
                    break;
            }
            for (int j = 0; j < workingArray.length; j++) {
                passwordArray[passIndex] = workingArray[j];
                passIndex++;
            }
        }

        return passwordArray;

    }

    //helper method for generatePassword()
    //assigns each part of the password to a type of character and fills them with randomly generated values
    private void assignAndFillParts(int[] partsArray) {
        RandomCharGenerator generator = new RandomCharGenerator();
        for (int i = 0; i < partsArray.length; i++) {
            switch (this.optionsIncluded.get(i)) {
                case "lower":
                    this.lowerArray = new char[partsArray[i]];
                    this.lowerArray = generator.generateLower(lowerArray);
                    break;
                case "upper":
                    this.upperArray = new char[partsArray[i]];
                    this.upperArray = generator.generateUpper(upperArray);
                    break;
                case "number":
                    this.numArray = new char[partsArray[i]];
                    this.numArray = generator.generateNumber(numArray);
                    break;
                case "special":
                    this.specialArray = new char[partsArray[i]];
                    this.specialArray = generator.generateSpecial(specialArray);
                    break;
            }
        }
    }

    //helper method for generatePassword(), returns how many options are selected
    private int optionCounter() {
        int count = 0;
        if (this.hasLowerCase == true) {
            count += 1;
        }
        if (this.hasUpperCase == true) {
            count += 1;
        }
        if (this.hasNumbers == true) {
            count += 1;
        }
        if (this.hasSpecialChar == true) {
            count += 1;
        }
        return count;
    }

    //sets optionsIncluded array based on the value of the boolean data
    private void setOptionsIncluded() {
        if (this.hasLowerCase) {
            this.optionsIncluded.add("lower");
        }
        if (this.hasUpperCase) {
            this.optionsIncluded.add("upper");
        }
        if (this.hasNumbers) {
            this.optionsIncluded.add("number");
        }
        if (this.hasSpecialChar) {
            this.optionsIncluded.add("special");
        }
    }
    public int getPasswordLength() {
        return passwordLength;
    }

    public void setPasswordLength(int passwordLength) {
        this.passwordLength = passwordLength;
    }

    public boolean isHasLowerCase() {
        return hasLowerCase;
    }

    public void setHasLowerCase(boolean hasLowerCase) {
        this.hasLowerCase = hasLowerCase;
    }

    public boolean isHasUpperCase() {
        return hasUpperCase;
    }

    public void setHasUpperCase(boolean hasUpperCase) {
        this.hasUpperCase = hasUpperCase;
    }

    public boolean isHasNumbers() {
        return hasNumbers;
    }

    public void setHasNumbers(boolean hasNumbers) {
        this.hasNumbers = hasNumbers;
    }

    public boolean isHasSpecialChar() {
        return hasSpecialChar;
    }

    public void setHasSpecialChar(boolean hasSpecialChar) {
        this.hasSpecialChar = hasSpecialChar;
    }

}

