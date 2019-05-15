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

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

/**
 * Classe principal da simulação de cruzamento.
 * @author PedroCesarMesquita
 */
public class Main {

    /**
     * Método principal.
     * @param args  argumentos da linha de comando
     */
    public static void main(String[] args) {
        JFrame f = new JFrame("Simulação de cruzamento");        
        Carro[] carros = new Carro[20];
        GUI gui = new GUI(carros, Carro.getSemaforos());
        
        Timer timer = new Timer();        
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                boolean b = true;
                for(int i = 0; i < carros.length; i++) {
                    if(carros[i] != null) {
                        if(carros[i].terminou())
                            carros[i] = null;
                    }
                    else if(b) {
                        carros[i] = new Carro();
                        b = false;
                    }
                }
            }
        };
        
        timer.schedule(task, 1000L, 1000L);
        
        f.setSize(800, 800);
        f.setLocationRelativeTo(null);
        f.add(gui);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
