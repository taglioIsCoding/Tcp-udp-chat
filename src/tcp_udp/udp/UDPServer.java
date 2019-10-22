/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp_udp.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import tcp_udp.tcp.Cripto;
/**
 * 
 * 
 * 
 * 
 *
 */
public class UDPServer {
	
	public static void main(String[] args) {
		DatagramSocket serSocket =null;
		try {
			serSocket = new DatagramSocket(8899);//costruisce il socket
			
			byte[] receive = new byte[1024];
			byte[] sendData = new byte[1024];
			
			System.out.println("UDP server online");
			
                        final String secretKey = "bhbbhihibuhubu";
                        
			while(true){
				//costriusce un pacchetto di dati vuoto, senza dati, pronto per riceverne
				DatagramPacket dp = new DatagramPacket(receive, receive.length);
				
				serSocket.receive(dp);//riceve i dati e li incapsula nella busta vuota
				String data = new String(dp.getData());
                                
                                System.out.println(data);
                                String decry = CriptoU.decrypt(data, secretKey);
                                
				System.out.println("Client: "+decry);
				
				//invia dati
				InetAddress address = dp.getAddress();
				int port = dp.getPort();
				
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String data1 = br.readLine();//legge i dati in console
                             
                                String encrypt = CriptoU.encrypt(data1, secretKey);
                                System.out.println(encrypt);
                                sendData =encrypt.getBytes();
				
				
				//Costruire una lettera DatagramPacket, che incapsula i dati da inviare, la lunghezza dei dati e le informazioni sul destinatario
				DatagramPacket dp1 = new DatagramPacket(sendData, sendData.length,address,port);
				serSocket.send(dp1);//Invia il messaggio di dati dp1 alla destinazione
				dp = null;
				receive = new byte[1024];
			}
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			serSocket.close();
		}
		
		
	}
	
}