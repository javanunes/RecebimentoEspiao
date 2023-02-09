/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recebimento;

/**
 *
 * @author ricardo
 */
public class Recebimento implements Runnable{

    // Se o seu computador for Windows, ponha algo +ou- assim: "c:\\temp\\nudes.jpg";
    public final static String ARQUIVOIMAGEMINICIALMEUCOMPUTADOR = "/tmp/o.jpg";
    
    public static void main(String[] args) {
        // TODO code application logic here
        Recebimento chamadoJanelaRecebimentoImagem = new Recebimento();
        chamadoJanelaRecebimentoImagem.run();
    }

    @Override
    public void run() {
         Imagem janelaImagem = new Imagem(ARQUIVOIMAGEMINICIALMEUCOMPUTADOR);
         janelaImagem.setVisible(true);
         janelaImagem.acionarConexaoRecebimentoImagensTCP();
    }
    
}
