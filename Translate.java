/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translate;
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Translate {

public static final String SERVER = "dict.us.dict.org";
public static final int PORT = 2628;
public static final int TIMEOUT = 30000;
    
public static void main(String[] args) {
Socket socket = null;int max=0;int maxnum=0;
String[] ar=new String[3];

Scanner scan=new Scanner(System.in);
for(int i=0;i<3;i++){
    System.out.println("enter english word then press enter");
    ar[i]=scan.nextLine();
if(ar[i].length()>max){maxnum=i;max=ar[i].length();
}
}
String arg =ar[maxnum];
try {
socket = new Socket(SERVER, PORT);
System.out.println("Socket created...");
socket.setSoTimeout(TIMEOUT);
OutputStream out = socket.getOutputStream();
System.out.println("Input Stream created...");
Writer writer = new OutputStreamWriter(out, "UTF-8");
writer = new BufferedWriter(writer);
InputStream in = socket.getInputStream();
System.out.println("Output Stream created...");
BufferedReader reader = new BufferedReader(
new InputStreamReader(in, "UTF-8"));

defineara(arg, writer, reader);
definerus(arg, writer, reader);

writer.write("quit\r\n");
writer.flush();
  } catch (IOException ex) {
System.err.println(ex);
} finally { // dispose
if (socket != null) {
try {
socket.close();
} catch (IOException ex) {
// ignore
}
}
}
}
static void defineara(String word, Writer writer, BufferedReader reader)
throws IOException, UnsupportedEncodingException {
   // writer.write("SHOW DB"+"\r\n");
writer.write("DEFINE fd-eng-ara " + word + "\r\n");
System.out.println("ARABIC");
System.out.println("DEFINE * " + word + "\r\n");
writer.flush();
System.out.println("Reading...");
for (String line = reader.readLine(); line != null; line = reader.readLine()) {
System.out.println(line);
if (line.startsWith("250 ")) { // OK
System.out.println("OK");
return;
} else if (line.startsWith("552 ")) { // no match
System.out.println("No definition found for " + word);
return;
}
else if (line.matches("\\d\\d\\d .*")) continue;
else if (line.trim().equals(".")) continue;
else System.out.println(line);
}  
    }  


static void definerus(String word, Writer writer, BufferedReader reader)
throws IOException, UnsupportedEncodingException {
   // writer.write("SHOW DB"+"\r\n");
writer.write("DEFINE fd-eng-rus " + word + "\r\n");
System.out.println("russian");
System.out.println("DEFINE * " + word + "\r\n");
writer.flush();
System.out.println("Reading...");
for (String line = reader.readLine(); line != null; line = reader.readLine()) {
System.out.println(line);
if (line.startsWith("250 ")) { // OK
System.out.println("OK");
return;
} else if (line.startsWith("552 ")) { // no match
System.out.println("No definition found for " + word);
return;
}
else if (line.matches("\\d\\d\\d .*")) continue;
else if (line.trim().equals(".")) continue;
else System.out.println(line);
}  
    }  
}
