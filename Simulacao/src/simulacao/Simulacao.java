package simulacao;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

public class Simulacao {
    public static void main(String[] args) {
        JFrame f = new JFrame("Simulação");        
        Carro[] carros = new Carro[4];
        for(int i = 0; i < 4; i++) {
            carros[i] = new Carro(i);
        }
        UI ui = new UI(carros);
        
        f.setSize(800, 800);
        f.add(ui);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
