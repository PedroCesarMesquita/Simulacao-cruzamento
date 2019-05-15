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
import java.util.Timer;
import java.util.TimerTask;

/**
 * Semáforo.
 * @author PedroCesarMesquita
 */
public class Semaforo {
    
    private Color cor;
    private int x, y, sentido, carrosParados;  

    /**
     * Constantes que definem as dimensões do semáforo.
     */
    public static final int COMPRIMENTO = 20, LARGURA = 12;

    /**
     * Chaves para os sentidos que o semáforo pode operar.
     */
    public static final int 
            SENTIDO_DIREITA  = 0,
            SENTIDO_CIMA     = 1,
            SENTIDO_ESQUERDA = 2,
            SENTIDO_BAIXO    = 3;

    /**
     * Construtor sem parâmetros.
     * Define sentido como direita e sinal inicial como aberto(verde) por padrão.
     */
    public Semaforo() {
        this(0, true);
    }
    
    /**
     * Construtor com definição do sentido do semáforo.
     * Define sinal inicial como aberto(verde) por padrão.
     * @param sentido   sentido em que o semáforo opera
     */
    public Semaforo(int sentido) {
        this(sentido, true);
    }
    
    /**
     * Construtor com definição do sentido e sinal do semáforo.
     * @param sentido   sentido em que o semáforo opera
     * @param aberto    sinal inicial do semáforo
     */
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
    
    /**
     * Retorna a cor atual do semáforo.
     * @return  cor atual do semáforo
     */
    public Color getCor() {
        return this.cor;
    }
    
    /**
     * Define a cor atual do semáforo.
     * @param cor   cor atual do semáforo
     */
    public void setCor(Color cor) {
        this.cor = cor;
    }
    
    /**
     * Retorna a posição horizontal do semáforo.
     * @return  posição horizontal do semáforo
     */
    public int getX() {
        return this.x;
    }
    
    /**
     * Define a posição horizontal do semáforo.
     * @param x posição horizontal do semáforo
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Retorna a posição vertical do semáforo.
     * @return  posição vertical do semáforo
     */
    public int getY() {
        return this.y;
    }
    
    /**
     * Define a posição vertical do semáforo.
     * @param y posição vertical do semáforo
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Retorna o sentido em que o semáforo opera.
     * @return  sentido em que o semáforo opera
     */
    public int getSentido() {
        return this.sentido;
    }
    
    /**
     * Define o sentido em que o semáforo opera.
     * @param sentido   sentido em que o semáforo opera.
     */
    public void setSentido(int sentido) {
        this.sentido = sentido >= 0 && sentido < 4 ? sentido : 0;
    }
    
    /**
     * Retorna o número de carros parados pelo semáforo.
     * @return  número de carros parados pelo semáforo.
     */
    public int getCarrosParados() {
        return this.carrosParados;
    }
    
    /**
     * Define o número de carros parados pelo semáforo.
     * @param carrosParados número de carros parados pelo semáforo.
     */
    public void setCarrosParados(int carrosParados) {
        this.carrosParados = carrosParados;
    }
}
