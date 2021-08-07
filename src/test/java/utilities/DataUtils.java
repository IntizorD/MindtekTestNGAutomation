package utilities;

import java.util.Random;

public class DataUtils {
    /*
    This method will generate random email.
    Ex:
    .generateEmail(); -> return "abc1234@gmail.com"
     */

    public static String generateEmail(){
        Random random = new Random();
        int emailId = random.nextInt(100000);
        String email = "abc"+emailId+"@gmail.com";
        return email;


    }

    /*
    This method will generate random number between 1 and provided parameter
    EX:
    .generateRandomNumber(30); ->return 5
     */

    public static int generateRandomNumber (int range){
        Random random = new Random();
        int randomNum = random.nextInt(range);
        return randomNum;
    }
/**
 * This method will remove $ sign from String
 * and will convert it to double.
 * Ex:
 * .remove$AndConvertToDouble($16.51); -> return double -> 16.51
 */
public static double remove$AndConvertToDouble(String amount){
    amount= amount.substring(1);
    return Double.parseDouble(amount);
}


}
