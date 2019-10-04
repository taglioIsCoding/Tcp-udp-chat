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
import java.net.*;
import java.io.*;
 
public class EchoServer {
    
    public static void main(String[] args) throws IOException {
         
        /*
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }*/
        System.out.println("TCP server online");
        int portNumber;
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
         
        //int portNumber = Integer.parseInt(args[0]);
        
        System.out.println("Insert the port number: ");
        portNumber = Integer.parseInt(r.readLine());
        
        try (
            ServerSocket serverSocket =
                new ServerSocket(/*Integer.parseInt(args[0])*/portNumber);
            Socket clientSocket = serverSocket.accept();     
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);   
            BufferedReader in = new BufferedReader(
                //new InputStreamReader(clientSocket.getInputStream()));
                    new InputStreamReader(System.in));
            BufferedReader inC = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            System.out.println("Client: "+ inC.readLine());
            while ((inputLine = in.readLine()) != null) {
                //System.out.println("Client: "+ inC.readLine());
                out.println(inputLine);
                System.out.println("Client: "+ inC.readLine());
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}