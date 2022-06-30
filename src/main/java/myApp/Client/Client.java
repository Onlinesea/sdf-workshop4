package myApp.Client;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import myApp.Server.Server;

/**
 * Hello world!
 *
 */
public class Client
{
    public static void main( String[] args )
    {
        try {
            //Initialising the sock connection 
            Socket sock = new Socket("localhost", 8080);

            //Initialising InputStream that is from the socket
            InputStream is = sock.getInputStream();

            //Initialising DataInputStream to read the data
            DataInputStream dis = new DataInputStream(is);

            //Inititalising Outputstream to the socket
            OutputStream os = sock.getOutputStream();
            
            //Initialising DataOutputStream to write the data out of the socket 
            DataOutputStream dos = new DataOutputStream(os);

            //Getting the input from the system 
            Console cons = System.console();
            String input = cons.readLine("Send command to the server > ");

            //Writing the data to the socket to the outputStream 
            dos.writeUTF(input);

            //Server will send a response back 
            String response = dis.readUTF();

            //if the reponse contain cookie-text 
            if(response.contains("cookie-text")){
                System.out.println(response);
                String[] cookieValue = response.split(" ", 2);
                System.out.printf("Cookie from the server > %s\n", cookieValue[1]);
            }
            /*else if ( response.contains("stop")){
                stop = true;
            }
            */

            is.close();
            os.close();
            sock.close();
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }

    
        
        }
}
