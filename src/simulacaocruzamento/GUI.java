/*
    Copyright (C) 2019 PedroCesarMesquita

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

package simulacaocruzamento;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 * Inferface gráfica.
 * @author PedroCesarMesquita
 */
public class GUI extends JPanel {
    private Carro[] carros;
    private Semaforo[] semaforos;
    private Graphics g;
    
    /**
     * Construtor sem parâmetros.
     * Inicializa vetores de carros e semáforos como null.
     */
    public GUI() {
        this(null, null);
    }
    
    /**
     * Construtor com definição de carros.
     * Inicializa vetor de semáforos como null.
     * @param carros    carros a serem desenhados
     */
    public GUI(Carro[] carros) {
        this(carros, null);
    }
    
    /**
     * Construtor com definição de semáforos.
     * Inicializa vetor de carros como null.
     * @param semaforos semáforos a serem desenhados
     */
    public GUI(Semaforo[] semaforos) {
        this(null, semaforos);
    }
    
    /**
     * Construtor com definição de carros e semáforos.
     * @param carros    carros a serem desenhados
     * @param semaforos semáforos a serem desenhados
     */
    public GUI(Carro[] carros, Semaforo[] semaforos) {
        super();
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
        
        timer.schedule(task, 0L, 10L);
    }
    
    /**
     * Retorna o vetor de carros a serem desenhados.
     * @return  vetor de carros a serem desenhados
     */
    public Carro[] getCarros() {
        return this.carros;
    }
    
    /**
     * Define o vetor de carros a serem desenhados.
     * @param carros    vetor de carros a serem desenhados
     */
    public void setCarros(Carro[] carros) {
        this.carros = carros;
    }
    
    /**
     * Retorna o vetor de semáforos a serem desenhados.
     * @return  vetor de semáforos a serem desenhados
     */
    public Semaforo[] getSemaforos() {
        return this.semaforos;
    }
    
    /**
     * Define o vetor de semáforos a serem desenhados.
     * @param semaforos vetor de semáforos a serem desenhados
     */
    public void setSemaforos(Semaforo[] semaforos) {
        this.semaforos = semaforos;
    }
    
    /**
     * Desenha ruas, carros e semáforos no JPanel.
     * @param graphics  gráficos
     */
    @Override
    public void paintComponent(Graphics graphics) {        
        super.paintComponent(g = graphics);
        
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
    
    /**
     * Desenha um carro.
     * @param carro carro a ser desenhado
     */
    public void desenhaCarro(Carro carro) {
        int comprimento = carro.getComprimento(), largura = carro.getLargura();
        if(carro.getSentido() % 2 == 0) {
            g.setColor(carro.getCor());
            g.fillRect(carro.getX() - comprimento / 2, carro.getY() - largura / 2, comprimento, largura);
            g.setColor(Color.black);
            g.drawRect(carro.getX() - comprimento / 2, carro.getY() - largura / 2, comprimento, largura);
        } else {
            g.setColor(carro.getCor());
            g.fillRect(carro.getX() - largura / 2, carro.getY() - comprimento / 2, largura, comprimento);
            g.setColor(Color.black);
            g.drawRect(carro.getX() - largura / 2, carro.getY() - comprimento / 2, largura, comprimento);
        }
    }
    
    /**
     * Desenha um semáforo.
     * @param semaforo  semáforo a ser desenhado
     */
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
        g.setColor(semaforo.getCor());
        g.fillRect(x, y, w, h);
        g.setColor(Color.black);
        g.drawRect(x, y, w, h);
    }
}
