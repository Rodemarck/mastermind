package com.rodemarck.mastermind;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class c2 {
    static DatagramSocket aSocket = null;
    public static void main(String args[]){

        try{
            aSocket =  new DatagramSocket(6789);
            // create socket at agreed port
            byte[] buffer = new byte[1000];
            while(true){
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                new Thread(() -> {
                    String s = new String(request.getData());
                    System.out.println(s);

                    s = "O  q o servidor falou foi:"+s;
                    byte[] buff = s.getBytes();

                    DatagramPacket d = new DatagramPacket(buff,  s.length(), request.getAddress(), request.getPort());
                    try {
                        aSocket.send(d);
                    }catch(Exception e){

                    }
                }).start();

            }
        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        }catch (IOException e) {System.out.println("IO: " + e.getMessage());
        }finally {if(aSocket != null) aSocket.close();}
    }
}
