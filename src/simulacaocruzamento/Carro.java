package simulacaocruzamento;

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
    private int x, y, sentido, velocidadeX, velocidadeY;
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
        
        
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                atualiza();
            }
        };
        
        timer.schedule(task, 0L, 8L);
    }
    
    public static Semaforo[] getSemaforos() {
        return semaforos;
    }
    
    public static int getSentidoAleatorio() {
        return (int) (Math.random() * 4);
    }
    
    public static Color getCorAleatoria() {
        Color[] cores = {
            Color.white, Color.cyan, Color.blue, Color.gray, Color.yellow,
            Color.pink, Color.magenta, Color.red, Color.green
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
    
    public int getVelocidadeX() {
        return this.velocidadeX;
    }
    
    public void setVelocidade(int vx) {
        this.velocidadeX = vx;
    }
    
    public int getVelocidadeY() {
        return this.velocidadeY;
    }
    
    public void setVelocidadeY(int vy) {
        this.velocidadeY = vy;
    }
    
    public boolean terminou() {
        if(sentido == SENTIDO_DIREITA && x > 800 + COMPRIMENTO / 2) return true;
        if(sentido == SENTIDO_CIMA && y < -COMPRIMENTO / 2) return true;
        if(sentido == SENTIDO_ESQUERDA && x < -COMPRIMENTO / 2) return true;
        return sentido == SENTIDO_BAIXO && y > 800 + COMPRIMENTO / 2;
    }
    
    public void atualiza() {
        if(verificaSemaforo()) {
            velocidadeX = (int)  Math.cos((double) sentido * Math.PI / 2.0);
            velocidadeY = (int) -Math.sin((double) sentido * Math.PI / 2.0);
            if(virar && x >= 375 && x <= 425 && y >= 375 && y <= 425) {
                sentido = (sentido + 3) % 4;
                x += (int)  Math.cos((double) sentido * Math.PI / 2.0) * (COMPRIMENTO - LARGURA);
                y += (int) -Math.sin((double) sentido * Math.PI / 2.0) * (COMPRIMENTO - LARGURA);
                virar = false;
            }
        }
        else if(velocidadeX != 0 || velocidadeY != 0) {
            velocidadeX = velocidadeY = 0;
            semaforos[sentido].setCarrosParados(semaforos[sentido].getCarrosParados() + 1);
        }
        x += velocidadeX;
        y += velocidadeY;
    }

    public boolean verificaSemaforo() {
        if(semaforos[sentido].getCor() == Color.red || semaforos[sentido].getCor() == Color.yellow) {
            if(sentido == SENTIDO_DIREITA)
                return (x >= semaforos[sentido].getX() - COMPRIMENTO / 2) || (x < semaforos[sentido].getX() - (int) (((double) semaforos[sentido].getCarrosParados() * 1.25) * COMPRIMENTO + COMPRIMENTO));
            if(sentido == SENTIDO_CIMA)
                return (y <= semaforos[sentido].getY() + COMPRIMENTO / 2) || (y > semaforos[sentido].getY() + (int) (((double) semaforos[sentido].getCarrosParados() * 1.25) * COMPRIMENTO + COMPRIMENTO));
            if(sentido == SENTIDO_ESQUERDA)
                return (x <= semaforos[sentido].getX() + COMPRIMENTO / 2) || (x > semaforos[sentido].getX() + (int) (((double) semaforos[sentido].getCarrosParados() * 1.25) * COMPRIMENTO + COMPRIMENTO));
            return (y >= semaforos[sentido].getY() - COMPRIMENTO / 2) || (y < semaforos[sentido].getY() - (int) (((double) semaforos[sentido].getCarrosParados() * 1.25) * COMPRIMENTO + COMPRIMENTO));
        }
        return true;
    }
    
    @Override
    protected void finalize() {
        timer.cancel();
        timer = null;
        cor = null;
    }
}