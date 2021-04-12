/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cliente.servidor;

/**
 *
 * @author ydieh
 */
import java.io.*;
import static java.lang.Integer.parseInt;
import java.net.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Servidor {
    public static void main(String args[]) throws IOException {
        DataInputStream in; // 
        DataOutputStream out;
        int[] Vector = new int [50];
        //puerto de nuestro servidor
         int PUERTO = 9876;

        try {
            //Creamos el socket del servidor
            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado");
            int i=0;
            while(true){
                //Espero a que un cliente se conecte
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado");
                in = new DataInputStream(cliente.getInputStream());//recibimos msj
                out = new DataOutputStream(cliente.getOutputStream());//enviamos msj

                //Leo el mensaje que me envia
                String mensaje = in.readUTF();
               // System.out.println(mensaje);
                if(mensaje.equals("3")){
                    int c1=0;
                    int c2=0;
                    
                    for(int j=0;j<i;j++){
                        System.out.println(Vector[j]);
                        if(Vector[j]==1){
                            c1++;
                        }else
                            c2++;
                    }
                    if(c1>c2)
                        out.writeUTF("GANA LA OPCION 1");
                    else
                         out.writeUTF("GANA LA OPCION 2");
                }else{
                    Vector[i]= Integer.parseInt(mensaje);
                    out.writeUTF("VOTO RECIBIDO");
                    i++;
                }
                
               
                //Le envio un mensaje
             //   out.writeUTF("MENSAJE RECIBIDO ");

                
                //Cierro el socket
                cliente.close();
                System.out.println("Cliente desconectado");
           }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
} 

