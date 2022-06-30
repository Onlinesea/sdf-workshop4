package myApp.Server;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class getCookie {
    String randomCookie;

    public String getRandomCookie(String cookiePath) {
            String randomCookie ="";
            File cookieFile = new File(cookiePath);
            System.out.println("ENTER GETCOOKIE");
            //Initialise a new scanner to read the file of cookielist
            try{
            Scanner sc = new Scanner(cookieFile);
            //Initialise the new cookieList in order to put the cookies inside 
            List<String> cookieList = new LinkedList<>();
            //Create a loop so while there is something
            while(sc.hasNextLine()){
                cookieList.add(sc.nextLine());
            }
            //Initialising a new random object 
            Random rand = new Random();
            //Use the random object to generate a random number from 0 to the number n(which is the size of the list)(not included)
            //then use the number as a index to get the corresponding cookie
            randomCookie = cookieList.get(rand.nextInt(cookieList.size()));
            sc.close();
       
            System.out.println(randomCookie);
            
         }catch(IOException e){
            e.printStackTrace();
        }
    
        return randomCookie;
    }

}
