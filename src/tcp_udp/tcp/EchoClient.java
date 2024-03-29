/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp_udp.tcp;

/**
 *
 * @author 71367370
 */
import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        
         System.out.println("TCP client online");
        String hostName;
        int portNumber;
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Insert the ip adress: ");
        hostName = r.readLine();
        System.out.println("Insert the port number: ");
        portNumber = Integer.parseInt(r.readLine());
        
        
        //Criptografia
        final String secretKey = "ssshhhhhhhhhhh!!!!";
        Cripto AES = new Cripto();
        String originalString = "ciaociao";
       
       // String decryptedString = AES.decrypt(encryptedString, secretKey) ;
     
       // System.out.println(originalString);
        
        //System.out.println(decryptedString);
        
        
 /*       if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }*/

        try (
            Socket echoSocket = new Socket(hostName, portNumber);
            PrintWriter out =
                new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in))
        ) {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                
                String encryptedString = AES.encrypt(userInput, secretKey) ;
                System.out.println("Messagge Encripted! " + encryptedString);
            	out.println(encryptedString);
                String message =  in.readLine();
            	System.out.println("Server: " + message);
                String decriptMessage = Cripto.decrypt(message, secretKey);
                System.out.println("Message Decrypted!  "+ decriptMessage);
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
    }
}