package simulacao;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

public class Carro {
    private Color cor;
    private Timer timer;
    private static Semaforo[] semaforos = {
        new Semaforo(0, true),
        new Semaforo(1, false),
        new Semaforo(2, true),
        new Semaforo(3, false)
    };
    private int x, y, sentido, velocidade;
    private boolean virar;
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
        this.sentido = sentido;
        cor = Carro.getCorAleatoria();
        virar = Math.random() >= 0.5;
        switch(sentido) {
            case SENTIDO_DIREITA:
                x = -COMPRIMENTO / 2;
                y = 425;
                break;
            case SENTIDO_CIMA:
                x = 425;
                y = 800 + COMPRIMENTO / 2;
                break;
            case SENTIDO_ESQUERDA:
                x = 800 + COMPRIMENTO / 2;
                y = 375;
                break;
            case SENTIDO_BAIXO:
                x = 375;
                y = -COMPRIMENTO / 2;
                break;
        }
        velocidade = 3;
        
        timer = new Timer();        
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                atualiza();
            }
        };
        
        timer.schedule(task, 0L, 50L);
    }
    
    public static Semaforo[] getSemaforos() {
        return semaforos;
    }
    
    public static int getSentidoAleatorio() {
        return (int) (Math.random() * 4);
    }
    
    public static Color getCorAleatoria() {
        Color[] cores = {
            Color.blue, Color.cyan, Color.green, Color.red, Color.magenta,
            Color.orange, Color.pink, Color.yellow, Color.white, Color.gray
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
    
    public boolean terminou() {
        if(sentido == SENTIDO_DIREITA && x > 800 + COMPRIMENTO / 2) return true;
        if(sentido == SENTIDO_CIMA && y < -COMPRIMENTO / 2) return true;
        if(sentido == SENTIDO_ESQUERDA && x < -COMPRIMENTO / 2) return true;
        if(sentido == SENTIDO_BAIXO && y > 800 + COMPRIMENTO / 2) return true;
        return false;
    }        
   
    
    public void atualiza() {
        switch(sentido) {
            case SENTIDO_DIREITA:
                if(x > 350 + COMPRIMENTO / 2 && virar) {
                    virar = false;
                    x = 350 + COMPRIMENTO / 2;
                    sentido = SENTIDO_BAIXO;
                } else if(semaforos[sentido].getEstado() == Semaforo.FECHADO && x > semaforos[sentido].getX() - COMPRIMENTO && x < semaforos[sentido].getX() - COMPRIMENTO / 2) {
                    velocidade = 0;
                } else velocidade = 5;
                x += velocidade; 
                break;
            case SENTIDO_CIMA:
                if(y < 450 - COMPRIMENTO / 2 && virar) {
                    virar = false;
                    y = 450 - COMPRIMENTO / 2;
                    sentido = SENTIDO_DIREITA;
                } else if(semaforos[sentido].getEstado() == Semaforo.FECHADO && y < semaforos[sentido].getY() + COMPRIMENTO && y > semaforos[sentido].getY() + COMPRIMENTO / 2) {
                    velocidade = 0;
                } else velocidade = 5;
                y -= velocidade; 
                break;
            case SENTIDO_ESQUERDA:
                if(x < 450 - COMPRIMENTO / 2 && virar) {
                    virar = false;
                    x = 450 - COMPRIMENTO / 2;
                    sentido = SENTIDO_CIMA;
                } else if(semaforos[sentido].getEstado() == Semaforo.FECHADO && x < semaforos[sentido].getX() + COMPRIMENTO && x > semaforos[sentido].getX() + COMPRIMENTO / 2) {
                    velocidade = 0;
                } else velocidade = 5;
                x -= velocidade; 
                break;
            case SENTIDO_BAIXO:
                if(y > 350 + COMPRIMENTO / 2 && virar) {
                    virar = false;
                    y = 350 + COMPRIMENTO / 2;
                    sentido = SENTIDO_ESQUERDA;
                } else if(semaforos[sentido].getEstado() == Semaforo.FECHADO && y > semaforos[sentido].getY() - COMPRIMENTO && y < semaforos[sentido].getY() - COMPRIMENTO / 2) {
                    velocidade = 0;
                } else velocidade = 5;
                y += velocidade; 
                break;
        }
    }
    
    public void finaliza() {
        timer.cancel();
    }
}