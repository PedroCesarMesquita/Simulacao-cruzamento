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
 * Carro.
 * @author PedroCesarMesquita
 */
public class Carro {
    private Color cor;
    private Timer timer;
    private int comprimento, largura;
    private static Semaforo[] semaforos = {
        new Semaforo(0, true),
        new Semaforo(1, false),
        new Semaforo(2, true),
        new Semaforo(3, false)
    };
    private int x, y, sentido, velocidadeX, velocidadeY;
    private boolean virar;

    /**
     * Chaves para os sentidos em que os carros podem estar.
     */
    public static final int
            SENTIDO_DIREITA  = 0,
            SENTIDO_CIMA     = 1,
            SENTIDO_ESQUERDA = 2,
            SENTIDO_BAIXO    = 3;
    
    /**
     * Construtor sem parâmetro, que define um sentido aleatório para o carro.
     */
    public Carro() {
        this((int) (Math.random() * 4));
    }
    
    /**
     * Construtor com definição de sentido.
     * @param sentido   sentido inicial do carro
     */
    public Carro(int sentido) {
        this.sentido = sentido;
        comprimento = 40;
        largura = 30;
        cor = Carro.getCorAleatoria();
        virar = Math.random() >= 0.5;
        switch(sentido) {
            case SENTIDO_DIREITA:
                x = -comprimento / 2;
                y = 425;
                break;
            case SENTIDO_CIMA:
                x = 425;
                y = 800 + comprimento / 2;
                break;
            case SENTIDO_ESQUERDA:
                x = 800 + comprimento / 2;
                y = 375;
                break;
            case SENTIDO_BAIXO:
                x = 375;
                y = -comprimento / 2;
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
    
    /**
     * Retorna os semáforos existentes.
     * @return  semáforos existentes.
     */
    public static Semaforo[] getSemaforos() {
        return semaforos;
    }
    
    /**
     * Retorna uma cor aleatória.
     * @return  cor aleatória.
     */
    public static Color getCorAleatoria() {
        Color[] cores = {
            Color.white, Color.cyan, Color.blue, Color.gray, Color.yellow,
            Color.pink, Color.magenta, Color.red, Color.green
        };
        return cores[(int) (Math.random() * cores.length)];
    }
    
    /**
     * Retorna a cor do carro.
     * @return  cor do carro
     */
    public Color getCor() {
        return this.cor;
    }
    
    /**
     * Define a cor do carro.
     * @param cor   cor do carro
     */
    public void setCor(Color cor) {
        this.cor = cor;
    }

    /**
     * Retorna o comprimento do carro.
     * @return  comprimento do carro
     */
    public int getComprimento() {
        return comprimento;
    }

    /**
     * Define o comprimento do carro.
     * @param comprimento   comprimento do carro
     */
    public void setComprimento(int comprimento) {
        this.comprimento = comprimento;
    }

    /**
     * Retorna a largura do carro.
     * @return  largura do carro
     */
    public int getLargura() {
        return largura;
    }

    /**
     * Define a largura do carro.
     * @param largura   largura do carro
     */
    public void setLargura(int largura) {
        this.largura = largura;
    }
    
    /**
     * Retorna a posição horizontal do carro.
     * @return  posição horizontal do carro
     */
    public int getX() {
        return this.x;
    }
    
    /**
     * Define a posição horizontal do carro.
     * @param x posição horizontal do carro
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Retorna a posição vertical do carro.
     * @return  posição vertical do carro
     */
    public int getY() {
        return this.y;
    }
    
    /**
     * Define a posição vertical do carro.
     * @param y posição vertical do carro
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Retorna o sentido atual do carro.
     * @return  sentido atual do carro
     */
    public int getSentido() {
        return this.sentido;
    }
    
    /**
     * Define o sentido atual do carro.
     * @param sentido   sentido atual do carro
     */
    public void setSentido(int sentido) {
        if(sentido >= 0 && sentido < 4) {
            this.sentido = sentido;
        }
    }
    
    /**
     * Retorna a velocidade horizontal atual do carro.
     * @return  velocidade horizontal atual do carro
     */
    public int getVelocidadeX() {
        return this.velocidadeX;
    }
    
    /**
     * Define a velocidade horizontal atual do carro.
     * @param vx velocidade horizontal atual do carro
     */
    public void setVelocidadeX(int vx) {
        this.velocidadeX = vx;
    }
    
    /**
     * Retorna a velocidade vertical atual do carro.
     * @return  velocidade vertical atual do carro
     */
    public int getVelocidadeY() {
        return this.velocidadeY;
    }
    
    /**
     * Define a velocidade vertical atual do carro.
     * @param vy velocidade vertical atual do carro
     */
    public void setVelocidadeY(int vy) {
        this.velocidadeY = vy;
    }
    
    /**
     * Verifica se o carro já terminou seu percurso.
     * @return  se o carro já terminou seu percurso
     */
    public boolean terminou() {
        if(sentido == SENTIDO_DIREITA && x > 800 + comprimento / 2) return true;
        if(sentido == SENTIDO_CIMA && y < -comprimento / 2) return true;
        if(sentido == SENTIDO_ESQUERDA && x < -comprimento / 2) return true;
        return sentido == SENTIDO_BAIXO && y > 800 + comprimento / 2;
    }
    
    /**
     * Verifica situação atual do trânsito e atualiza a posição e velocidade do carro.
     */
    public void atualiza() {
        if(verificaSemaforo()) {
            velocidadeX = (int)  Math.cos((double) sentido * Math.PI / 2.0);
            velocidadeY = (int) -Math.sin((double) sentido * Math.PI / 2.0);
            if(virar && x >= 375 && x <= 425 && y >= 375 && y <= 425) {
                sentido = (sentido + 3) % 4;
                x += (int)  Math.cos((double) sentido * Math.PI / 2.0) * (comprimento - largura);
                y += (int) -Math.sin((double) sentido * Math.PI / 2.0) * (comprimento - largura);
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

    /**
     * Verifica se o carro deve parar devido a um semáforo fechado.
     * @return  se o carro deve parar devido a um semáforo fechado
     */
    public boolean verificaSemaforo() {
        if(semaforos[sentido].getCor() == Color.red || semaforos[sentido].getCor() == Color.yellow) {
            if(sentido == SENTIDO_DIREITA)
                return (x >= semaforos[sentido].getX() - comprimento / 2) || (x < semaforos[sentido].getX() - (int) (((double) semaforos[sentido].getCarrosParados() * 1.25) * comprimento + comprimento));
            if(sentido == SENTIDO_CIMA)
                return (y <= semaforos[sentido].getY() + comprimento / 2) || (y > semaforos[sentido].getY() + (int) (((double) semaforos[sentido].getCarrosParados() * 1.25) * comprimento + comprimento));
            if(sentido == SENTIDO_ESQUERDA)
                return (x <= semaforos[sentido].getX() + comprimento / 2) || (x > semaforos[sentido].getX() + (int) (((double) semaforos[sentido].getCarrosParados() * 1.25) * comprimento + comprimento));
            return (y >= semaforos[sentido].getY() - comprimento / 2) || (y < semaforos[sentido].getY() - (int) (((double) semaforos[sentido].getCarrosParados() * 1.25) * comprimento + comprimento));
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