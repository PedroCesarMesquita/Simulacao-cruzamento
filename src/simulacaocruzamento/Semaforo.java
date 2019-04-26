package simulacaocruzamento;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

public class Semaforo {
    private Color cor;
    private int x, y, sentido, carrosParados;  
    public static final int COMPRIMENTO = 20, LARGURA = 12;
    public static final int 
            SENTIDO_DIREITA  = 0,
            SENTIDO_CIMA     = 1,
            SENTIDO_ESQUERDA = 2,
            SENTIDO_BAIXO    = 3;

    public Semaforo(int sentido) {
        this(sentido, true);
    }
    
    public Semaforo(int sentido, boolean aberto) {
        this.cor = aberto ? Color.green : Color.red;
        this.sentido = sentido;
        x = (sentido == 0 || sentido == 3 ? 350 : 450) + (sentido % 2 == 0 ? LARGURA / 2 : COMPRIMENTO / 4) * (sentido < 2 ? -1 : 1);
        y = (sentido > 1 ? 350 : 450) + (sentido % 2 == 1 ? LARGURA / 2 : COMPRIMENTO / 4) * (sentido == 0 || sentido == 3 ? -1 : 1);
         
        Timer timerVerde = new Timer();        
        TimerTask taskVerde = new TimerTask() {
            @Override
            public void run() {
                setCor(Color.green);
                carrosParados = 0;
            }
        };
        
        Timer timerAmarelo = new Timer();
        TimerTask taskAmarelo = new TimerTask() {
            @Override
            public void run() {
                setCor(Color.yellow);
            }
        };
        
        Timer timerVermelho = new Timer();
        TimerTask taskVermelho = new TimerTask() {
            @Override
            public void run() {
                setCor(Color.red);
            }
        };
        
        timerVerde.schedule(taskVerde, aberto ? 0L : 5000L, 10000L);
        timerAmarelo.schedule(taskAmarelo, aberto ? 4000L : 9000L, 10000L);
        timerVermelho.schedule(taskVermelho, aberto ? 5000L : 0L, 10000L);
        
        carrosParados = 0;
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
    
    public int getCarrosParados() {
        return this.carrosParados;
    }
    
    public void setCarrosParados(int carrosParados) {
        this.carrosParados = carrosParados;
    }
}
