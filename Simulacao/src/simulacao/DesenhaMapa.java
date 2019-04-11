package simulacao;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DesenhaMapa extends JPanel {
    public DesenhaMapa(Carro[] carros) {
        
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setClip(0, 0, 800, 800);

        g.setColor(new Color(0, 122, 0));
        g.fillRect(0, 0, 800, 800);
        
        g.setColor(new Color(200, 200, 200));
        g.fillRect(0, 350, 800, 100);
        g.fillRect(350, 0, 100, 800);
        
        g.setColor(new Color(0, 0, 0));
        g.drawLine(0, 350, 350, 350);
        g.drawLine(0, 450, 350, 450);
        g.drawLine(350, 0, 350, 350);
        g.drawLine(450, 0, 450, 350);
        g.drawLine(450, 350, 800, 350);
        g.drawLine(450, 450, 800, 450);
        g.drawLine(350, 450, 350, 800);
        g.drawLine(450, 450, 450, 800);
    }
}
