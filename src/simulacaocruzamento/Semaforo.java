package simulacaocruzamento;

import java.util.Timer;
import java.util.TimerTask;

public class Semaforo {
    private boolean estado;
    private int x, y, sentido, carrosParados;
    public static final boolean FECHADO = false, ABERTO = true;   
    public static final int COMPRIMENTO = 20, LARGURA = 12;
    public static final int 
            SENTIDO_DIREITA  = 0,
            SENTIDO_CIMA     = 1,
            SENTIDO_ESQUERDA = 2,
            SENTIDO_BAIXO    = 3;
    
    
    public Semaforo(int sentido) {
        this(sentido, true);
    }
    
    public Semaforo(int sentido, boolean estado) {
        this.estado = estado;
        this.sentido = sentido;
        x = (sentido == 0 || sentido == 3 ? 350 : 450) + (sentido % 2 == 0 ? LARGURA / 2 : COMPRIMENTO / 4) * (sentido < 2 ? -1 : 1);
        y = (sentido > 1 ? 350 : 450) + (sentido % 2 == 1 ? LARGURA / 2 : COMPRIMENTO / 4) * (sentido == 0 || sentido == 3 ? -1 : 1);
         
        Timer timerAbre = new Timer();        
        TimerTask abre = new TimerTask() {
            @Override
            public void run() {
                setEstado(Semaforo.ABERTO);
                carrosParados = 0;
            }
        };
        
        Timer timerFecha = new Timer();
        TimerTask fecha = new TimerTask() {
            @Override
            public void run() {
                setEstado(Semaforo.FECHADO);
            }
        };
        
        if(estado == Semaforo.ABERTO) {
            timerAbre.schedule(abre, 11000L, 10000L);
            timerFecha.schedule(fecha, 5000L, 10000L);
        } else {
            timerAbre.schedule(abre, 6000L, 10000L);
            timerFecha.schedule(fecha, 10000L, 10000L);
        }
        
        carrosParados = 0;
    }
    
    public boolean getEstado() {
        return this.estado;
    }
    
    public void setEstado(boolean estado) {
        this.estado = estado;
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
