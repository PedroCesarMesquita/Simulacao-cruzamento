package simulacao;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class UI extends JPanel {
    private Carro[] carros;
    private Semaforo[] semaforos;
    private Graphics g;
    
    public UI() {
        this(null, null);
    }
    
    public UI(Carro[] carros) {
        this(carros, null);
    }
    
    public UI(Semaforo[] semaforos) {
        this(null, semaforos);
    }
    
    public UI(Carro[] carros, Semaforo[] semaforos) {
        this.carros = carros;
        this.semaforos = semaforos;
        this.g = null;
        
        JPanel that = this;
        Timer timer = new Timer();        
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                that.repaint();
            }
        };
        
        timer.schedule(task, 0L, 15L);
    }
    
    public Carro[] getCarros() {
        return this.carros;
    }
    
    public void setCarros(Carro[] carros) {
        this.carros = carros;
    }
    
    public Semaforo[] getSemaforos() {
        return this.semaforos;
    }
    
    public void setSemaforos(Semaforo[] semaforos) {
        this.semaforos = semaforos;
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        g = graphics;
        
        super.paintComponent(g);
        
        g.setClip(0, 0, 800, 800);

        g.setColor(new Color(0, 122, 0));
        g.fillRect(0, 0, 800, 800);
        
        g.setColor(new Color(200, 200, 200));
        g.fillRect(0, 350, 800, 100);
        g.fillRect(350, 0, 100, 800);
        
        g.setColor(Color.black);
        g.drawLine(0, 350, 350, 350);
        g.drawLine(0, 450, 350, 450);
        g.drawLine(350, 0, 350, 350);
        g.drawLine(450, 0, 450, 350);
        g.drawLine(450, 350, 800, 350);
        g.drawLine(450, 450, 800, 450);
        g.drawLine(350, 450, 350, 800);
        g.drawLine(450, 450, 450, 800);
        
        g.setColor(Color.yellow);        
        for(int idx = 15; idx < 800; idx += 60) {
            if(idx >= 350 && idx < 450) idx = 405;
            else {
                g.fillRect(idx, 398, 20, 4);
                g.fillRect(398, idx, 4, 20);
            }
        }
        
        for(Carro carro : carros) {
            if(carro != null)
                desenhaCarro(carro);
        }
        
        for(Semaforo semaforo : semaforos) {
            desenhaSemaforo(semaforo);
        }
    }
    
    public void desenhaCarro(Carro carro) {
        if(carro.getSentido() % 2 == 0) {
            g.setColor(carro.getCor());
            g.fillRect(carro.getX() - Carro.COMPRIMENTO / 2, carro.getY() - Carro.LARGURA / 2, Carro.COMPRIMENTO, Carro.LARGURA);
            g.setColor(Color.black);
            g.drawRect(carro.getX() - Carro.COMPRIMENTO / 2, carro.getY() - Carro.LARGURA / 2, Carro.COMPRIMENTO, Carro.LARGURA);
        } else {
            g.setColor(carro.getCor());
            g.fillRect(carro.getX() - Carro.LARGURA / 2, carro.getY() - Carro.COMPRIMENTO / 2, Carro.LARGURA, Carro.COMPRIMENTO);
            g.setColor(Color.black);
            g.drawRect(carro.getX() - Carro.LARGURA / 2, carro.getY() - Carro.COMPRIMENTO / 2, Carro.LARGURA, Carro.COMPRIMENTO);
        }
    }
    
    public void desenhaSemaforo(Semaforo semaforo) {
        int x, y, w, h;
        if(semaforo.getSentido() % 2 == 0) {
            x = semaforo.getX() - Semaforo.LARGURA / 2;
            y = semaforo.getY() - Semaforo.COMPRIMENTO / 2;
            w = Semaforo.LARGURA;
            h = Semaforo.COMPRIMENTO;
        } else {
            x = semaforo.getX() - Semaforo.COMPRIMENTO / 2;
            y = semaforo.getY() - Semaforo.LARGURA / 2;
            w = Semaforo.COMPRIMENTO;
            h = Semaforo.LARGURA;
        }
        g.setColor(semaforo.getEstado() ? Color.green : Color.red);
        g.fillRect(x, y, w, h);
        g.setColor(Color.black);
        g.drawRect(x, y, w, h);
    }
}
