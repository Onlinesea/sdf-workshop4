package vtp2022.dayworkshop;

import java.net.*;
import java.io.*;
import java.nio.file.*;

/*Description of what the server will do:
 * 1. Setting up the server for the client to conenct
 * 2. Read the input from the client 
 * 3. Output to client a string 
 * 4. Close the server
 */

public class ServerXH {
    public static void main(String [] args) throws IOException{
        ServerSocket ss = new ServerSocket(45628);
        System.out.println("Server set up"); 
        Socket s = ss.accept();
        System.out.println("clients connected");
        
            InputStream is = s.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(s.getInputStream());     // get the request from the socket 
            DataOutputStream reply = new DataOutputStream(s.getOutputStream());    // ?
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line  = dis.readUTF();
            if(line.trim().equalsIgnoreCase("get-cookie")){
                System.out.printf(Givecookie());
            }else{

            }
            if(line.trim().equalsIgnoreCase("close")){
                s.close();
                ss.close();
            }
            
        
        s.close();
        ss.close(); 

    }
    public static String Givecookie(){
        String cookie = "";
        try{
        int index = ((int)(Math.random()*27)+1);
        cookie = Files.readAllLines(Paths.get("C:/Users/funny/sdf-workshop4/src/main/java/vtp2022/dayworkshop/cookiesList.txt")).get(index); 
        System.out.println(cookie);
        }catch(IOException e){
            System.out.println(e);
        }
        return cookie;
    }
}
