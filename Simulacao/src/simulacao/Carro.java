package simulacao;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

public class Carro {
    private Color cor;
    private int x, y, sentido, velocidade;
    public static final int COMPRIMENTO = 40, LARGURA = 30;
    public static final int
            SENTIDO_DIREITA  = 0,
            SENTIDO_CIMA     = 1,
            SENTIDO_ESQUERDA = 2,
            SENTIDO_BAIXO    = 3;       
    
    public Carro() {
        this(getSentidoAleatorio());
    }
    
    public Carro(int sentido) {
        cor = Carro.getCorAleatoria();
        this.sentido = sentido;
        switch(this.sentido) {
            case SENTIDO_DIREITA:
                x = -COMPRIMENTO;
                y = 425;
                break;
            case SENTIDO_CIMA:
                x = 425;
                y = 800 + COMPRIMENTO;
                break;
            case SENTIDO_ESQUERDA:
                x = 800 + COMPRIMENTO;
                y = 375;
                break;
            case SENTIDO_BAIXO:
                x = 375;
                y = -COMPRIMENTO;
                break;
        }
        velocidade = 3;
        
        Timer timer = new Timer();        
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                update();
            }
        };
        
        timer.schedule(task, 0L, 20L);
    }
    
    public static int getSentidoAleatorio() {
        return (int) (Math.random() * 4);
    }
    
    public static Color getCorAleatoria() {
        Color[] cores = {
            Color.blue, Color.cyan, Color.green, Color.red, Color.magenta,
            Color.orange, Color.pink, Color.yellow, Color.white
        };
        return cores[(int) (Math.random() * cores.length)];
    }
    
    public Color getCor() {
        return this.cor;
    }
    
    public void setCor(Color cor) {
        this.cor = cor;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getSentido() {
        return this.sentido;
    }
    
    public void setSentido(int sentido) {
        if(sentido >= 0 && sentido < 4) {
            this.sentido = sentido;
        }
    }
    
    public int getVelocidade() {
        return this.velocidade;
    }
    
    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }
    
    public void update() {
        switch(sentido) {
            case SENTIDO_DIREITA: this.x += velocidade; break;
            case SENTIDO_CIMA: this.y -= velocidade; break;
            case SENTIDO_ESQUERDA: this.x -= velocidade; break;
            case SENTIDO_BAIXO: this.y += velocidade; break;
        }
    }
}