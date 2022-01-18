/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication22;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 
 * @author yazan
 *
 */
public class SocketClient {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        
        
        Socket socket = null;
        ObjectOutputStream o = null;
        ObjectInputStream i = null;
        Scanner scan=new Scanner(System.in);
        System.out.println("enter a word to be reverted");
        String s=scan.nextLine();
           
            socket = new Socket("127.0.0.1", 67);
            
            o = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending word to Server");
            
            o.writeObject(s);
            
            i = new ObjectInputStream(socket.getInputStream());
            String message = (String) i.readObject();
            System.out.println("Reverted word: " + message);
           
            i.close();
            o.close();
            
    }
}