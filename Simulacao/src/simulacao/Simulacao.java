package simulacao;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

public class Simulacao {
    public static void main(String[] args) {
        JFrame f = new JFrame("Simulação de cruzamento");        
        Carro[] carros = new Carro[20];
        int numCarros = 0;
        UI ui = new UI(carros, Carro.getSemaforos());
        
        Timer timer = new Timer();        
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                boolean b = true;
                for(int i = 0; i < carros.length; i++) {
                    if(carros[i] != null) {
                        if(carros[i].terminou())
                            carros[i] = null;
                    }
                    else if(b) {
                        carros[i] = new Carro();
                        b = false;
                    }
                    ui.setCarros(carros);
                }
            }
        };
        
        timer.schedule(task, 1000L, 1000L);
        
        f.setSize(800, 800);
        f.add(ui);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
