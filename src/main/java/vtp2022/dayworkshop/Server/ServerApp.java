package vtp2022.dayworkshop.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

    public static void main(String[] args) {
        //retrieve server port number from the first argument 
        String serverPort = args[0];
        //retrieve cookie file from the second argument or writing down the path to get the cookiesList  
        String cookieFilePath = "C:/Users/funny/sdf-workshop4/src/main/java/vtp2022/dayworkshop/cookiesList.txt"; //args[1];
        boolean stop = false;

       
        //try catch 
        try{ 
           
            System.out.printf("Cookie Server Started at %s\n", serverPort);

            //Instantiate the server socket class along with the port number that is passed down 
            ServerSocket server = new ServerSocket(Integer.parseInt(serverPort));
            //Server will con
            while(!stop){
            //Waiting for connection from the client side 
            Socket sock = server.accept();
            //Get the input and output stream in bytes 
            InputStream is = sock.getInputStream();
            //Converting the InputStream to DataInputStream
            DataInputStream dis = new DataInputStream(is);

            //Direct the output to the sockets
            OutputStream os = sock.getOutputStream();
            //Converting the OutputStream to DataOutputStream for DataOutputStream
            DataOutputStream dos = new DataOutputStream(os);

          
            //for request from client, read the information that is pass to the socket using DataInputStream 
            String requestFromClient = dis.readUTF();
            //Print out what is the request from client 
            System.out.printf("Recieved request from client : %s\n", requestFromClient);
            
            //if the request contains get-cookie then generate a random cookie
            if(requestFromClient.equals("get-cookie")){
                System.out.printf("file -> %s\n", cookieFilePath);

                String randomCookie = Cookie.getRandomCookie(cookieFilePath);
                System.out.println(randomCookie);
                //Data output stream to write (opposite from the load and save in shopping cart)
                //dos no need to flush and close?
                dos.writeUTF("cookie-text " + randomCookie);
                //dos write to the client what you want they to receive 
            }if(requestFromClient.equals("stop")){
                stop=true;
            }
            else{
                dos.writeUTF("Invalid command !");
            }
            
            //Close both InputStream and OutputStream
            is.close();
            os.close();

            sock.close();
        }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}

