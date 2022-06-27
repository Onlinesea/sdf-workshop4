package vtp2022.dayworkshop;

import java.net.*;
import java.io.*;


public class ClientXH {
    //initialising socket and output streams 
//    private Socket s =null;
//    private DataInputStream in = null;
//    private DataOutputStream out = null;
    public static void main(String[] args) throws IOException{
        Socket s = new Socket("localhost", 45628);
        Console cons = System.console();
        String request = cons.readLine(">");
        
        try (OutputStream os = s.getOutputStream()){
            BufferedOutputStream bos= new BufferedOutputStream(os);  // Determine how the data should be transmitted
            DataOutputStream dos= new DataOutputStream(bos);
            dos.writeUTF(request);                            //Write the data to the server
            dos.flush();                                             //Flush the output stream to send the data
        }
        
        s.close();
    }
}
