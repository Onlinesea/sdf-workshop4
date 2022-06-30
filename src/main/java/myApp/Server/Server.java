package myApp.Server;

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
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Server {
    //Initialise the main method
    public static void main(String[] args) throws IOException{

        String serverPort = "8080";
        boolean stop = false ;
        getCookie randomCookie = new getCookie();
        String cookieFile = "D:/VisaWorkshop/Workshopwk1/workshop4/src/main/java/myApp/Server/cookielist.txt";
           
            // Inititlising new server   

            ServerSocket server = new ServerSocket(Integer.parseInt(serverPort));

            // Printing to confirm the server set up and waiting for connection

            System.out.printf("Cookie Server started at port %s \n", serverPort);

            //Initialising a threadPool for the thread to be submitted

            ExecutorService threadPool = Executors.newFixedThreadPool(2); 

            while(true){

            //Print to show waiting for connection to come in//

            System.out.println("Waiting for client connection");

            // Initialising new server socket

            Socket sock = server.accept();
            System.out.println("Client connected");         

            //Initialise CookieHandler as a single thread and executable
            ClientCookieHandler thr = new ClientCookieHandler(sock,cookieFile);

            //Submit the thread to the threadPool for execution

            threadPool.submit(thr);

            //Print to show that the thread is submitted to threadPool

            System.out.println("Submitted to the threadPool");
            

        }
    }

    
}
