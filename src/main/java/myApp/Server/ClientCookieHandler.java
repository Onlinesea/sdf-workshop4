package myApp.Server;

import java.io.IOException;
import java.net.Socket;

public class ClientCookieHandler implements Runnable {
    String cookiefile;
    Socket sock;

    public ClientCookieHandler(Socket sock, String cookiefile){
        this.sock=sock;
        this.cookiefile=cookiefile;
    }
    @Override
    public void run(){
        try {
            //Print to show that it is running the client thread
            System.out.println("Starting a new client thread");
            NetworkIO nio = new NetworkIO(sock);
            getCookie cookie = new getCookie();

            String requestFromClient = nio.read();
            System.out.println(requestFromClient);
            
            if("get-cookie".equals(requestFromClient.toLowerCase().trim())){

                //To check the request that is being written
                System.out.println(requestFromClient);

                //Replace dos.write with nio.write 
                nio.write("cookie-text> "+ cookie.getRandomCookie(cookiefile));

            }else if(requestFromClient.equals("stop")){
                boolean stop = true;
            }else{
                nio.write("Invalid command");
            }
            
            nio.closeAll();
            System.out.println("Exiting Thread");
            
    } catch (IOException e) {
        e.printStackTrace();
    }
        
        
    }
    
}
