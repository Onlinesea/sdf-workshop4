package vtp2022.dayworkshop.Client;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientApp {
    public static void main(String[] args) {
        //To tell the user that this is cookie client
        System.out.println("Cookie Client");
        //Take in args split by ":"
        String[]arr = args[0].split(":");
        boolean stop = false;
        //Initialise try and catch
        try{
            while(!stop){
                //Initialise the socket to connect. For client side will need a IP address to receive the data 
                //and a port to connect with the server
                Socket sock = new Socket(arr[0], Integer.parseInt(arr[1]));
                
                //Getting the InputStream from the socket 
                InputStream is = sock.getInputStream();

                //Converting the InputSteam into DataInputStream 
                DataInputStream dis = new DataInputStream(is);

                //Getting OutputStream from the socket 
                OutputStream os = sock.getOutputStream();

                //Converting the OutputStream into DataOutputStream 
                DataOutputStream dos = new DataOutputStream(os);
                //All above is to inititalise the DataOutputStream and DataInputStream 

                //Initialise console for the user to key in
                Console cons = System.console();
                //Reading the input string 
                String input =cons.readLine("Send command to server > ");
                //Using DataInputStream to write this data
                dos.writeUTF(input);
                //After wrting the data, flush -> which is send the data to the server 
                //flush is a way to send the data to a destination?
                dos.flush();

                //dos is writing to server -> out of system 
                //dis is receiving from server -> into the system
                //Using dis to read the information coming in
                String response = dis.readUTF(); // reading from the data input read.UTF can convert into a string ? or can read int?

                if(response.contains("cookie-text")){
                    System.out.println(response);
                    String[] cookieValue = response.split(" ", 2);
                    System.out.printf("Cookie from server >> %s\n", cookieValue[1]);
                }
                else if(response.contains("stop")){
                    stop = true;
                }
                is.close();
                os.close();
                sock.close();
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }catch (UnknownHostException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }







    }
}
