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
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoClient{
    
    private final static int PortNumber = 10200;
    private String hostName;
    
    
    public String sendMessage(String message, String hostName){
    
        String ris = "";
        try {
            ris = excecute(message);
        } catch (IOException ex) {
            Logger.getLogger(EchoClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.hostName = hostName;
    
        return ris;
    }
    
    
    private String excecute(String message) throws IOException {
        
         System.out.println("TCP client online");
        String ris = "";

        try (
            Socket echoSocket = new Socket(hostName, PortNumber);
            PrintWriter out =
                new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
        ) {
            /*String userInput;
            while ((userInput = stdIn.readLine()) != null) {*/
                //System.out.println("Server: " + in.readLine());
            	out.println(message);
            	//System.out.println("Server: " + in.readLine());
                ris = in.readLine();
            }
         catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
        
        return ris;
    }
    
}
