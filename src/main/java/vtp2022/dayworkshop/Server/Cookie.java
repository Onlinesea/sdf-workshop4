package vtp2022.dayworkshop.Server;

import java.io.*;
import java.util.*;

public class Cookie {

    public static String getRandomCookie (String path){
        File cookieFile = new File(path);
        List<String> cookies = new LinkedList<>();
        String randomCookie="";
        Scanner scanner ;
        try{
            //scanner = new Scanner(source)
            scanner= new Scanner(cookieFile);
            while(scanner.hasNextLine()){
                cookies.add(scanner.nextLine());
            }
            scanner.close();
            //To check if all the cookies are being scanned in 
            System.out.println(cookies);
            //Random number generator 
            Random rand = new Random();
            //randomCookie = cookies.get(random number generated from (0 to the size of the cookie))
            randomCookie = cookies.get(rand.nextInt(cookies.size()));
            //Print to check what is the randomCookie that is generated 
            System.out.println(randomCookie);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        //Return the String of cookie that is generated.
        return randomCookie;
    }
    
}
