package simulacao;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class UI extends JPanel {
    private Carro[] carros;
    private Graphics g;
    
    public UI() {
        this(null);
    }
    
    public UI(Carro[] carros) {
        this.carros = carros;
    }
    
    public Carro[] getCarros() {
        return this.carros;
    }
    
    public void setCarros(Carro[] carros) {
        this.carros = carros;
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
        
        for(int i = 0; i < carros.length; i++) {
            desenhaCarro(carros[i]);
        }
        
        JPanel that = this;
        Timer timer = new Timer();        
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                that.repaint();
            }
        };
        
        timer.schedule(task, 0L, 20L);
    }
    
    public void desenhaCarro(Carro carro) {
        int x, y, w, h;
        if(carro.getSentido() % 2 == 0) {
            x = carro.getX() - Carro.COMPRIMENTO / 2;
            y = carro.getY() - Carro.LARGURA / 2;
            w = Carro.COMPRIMENTO;
            h = Carro.LARGURA;
        } else {
            x = carro.getX() - Carro.LARGURA / 2;
            y = carro.getY() - Carro.COMPRIMENTO / 2;
            w = Carro.LARGURA;
            h = Carro.COMPRIMENTO;
        }
        g.setColor(carro.getCor());
        g.fillRect(x, y, w, h);
        g.setColor(Color.black);
        g.drawRect(x, y, w, h);
    }
}
