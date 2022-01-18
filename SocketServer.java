/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elliote63;import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

/**

 * @author yazan
 *
 */
public class SocketServer {
    
    
    private static ServerSocket server;
    
    private static int port = 67;
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        
        server = new ServerSocket(port);
        
        while(true){
            System.out.println("Waiting for client");
            
            Socket socket = server.accept();
            
            ObjectInputStream i = new ObjectInputStream(socket.getInputStream());
            
            String message = (String) i.readObject();
            System.out.println("Message Received: " + message);
            
            String s="";
            for(int flag=message.length()-1;flag>=0;flag--){
            s=s+message.charAt(flag);
            }
            ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
            
            o.writeObject(s);
            
            i.close();
            o.close();
            socket.close();
            
            
        }
       
        
       
    }
    
}
