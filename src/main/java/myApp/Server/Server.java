package myApp.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Server {
    //Initialise the main method
    public static void main(String[] args) {

        String serverPort = "8080";
        boolean stop = false ;
        
        try {            ServerSocket server = new ServerSocket(Integer.parseInt(serverPort));

            // Printing to confirm the server set up and waiting for connection
            System.out.printf("Cookie Server started at port %s \n", serverPort);
            while(!stop){
            // Inititlising new server


            // Initialising new server socket
            Socket sock = server.accept();
            System.out.println("Client connected");

            // Initialise the inputstream that is coming in from the socket
            InputStream is = sock.getInputStream(); 

            // Initialise a reader to read the inputstream that is coming in 
            DataInputStream dis = new DataInputStream(is);

            // Initialise the outputStream of the socket 
            OutputStream os = sock.getOutputStream();

            //Initialise the writer for the outputstream
            DataOutputStream dos = new DataOutputStream(os);            

            //Initialise CookieHandler for the multithreading

            // Store dis.readUTF() into a string
            String requestFromClient = dis.readUTF();
            System.out.println(requestFromClient);
            // Output the string is read by bufferedReader
            if("get-cookie".equals(requestFromClient.toLowerCase().trim())){
                System.out.println(requestFromClient);
                dos.writeUTF("cookie-text> "+ getCookie());
            }else if(requestFromClient.equals("stop")){
                stop = true;
            }else{
                dos.writeUTF("Invalid command");
            }
            
            is.close();
            os.close();
            sock.close();
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

    public static String getCookie(){
        String randomCookie ="";
        File cookieFile = new File("D:/VisaWorkshop/Workshopwk1/workshop4/src/main/java/myApp/Server/cookielist.txt");
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
