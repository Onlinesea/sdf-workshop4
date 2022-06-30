package myApp.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetworkIO {
    private InputStream is;
    private DataInputStream dis;
    private OutputStream os;
    private DataOutputStream dos;

    public NetworkIO(Socket socket) throws IOException{
        this.is=socket.getInputStream();
        this.dis=new DataInputStream(is);
        this.os=socket.getOutputStream();
        this.dos = new DataOutputStream(os);
        
    }

    public void write(String message) throws IOException{
        dos.writeUTF(message);
        dos.flush();
    }

    public String read()throws IOException{
        return dis.readUTF();
    }
    
    public void closeAll()throws IOException{
        dis.close();
        dos.close();
        os.close();
        is.close();

    }
    
}
