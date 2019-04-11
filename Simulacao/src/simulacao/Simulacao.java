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
        /*
        Timer timer = new Timer();        
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                carros[0].drive();
            }
        };
        
        timer.schedule(task, 0L, 20L);*/
        
        f.setSize(800, 800);
        f.add(ui);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
