/*
 * Copyright (C) 2016 aztlek
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package elpinguinotux_pasoapaso;

import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Esta es la clase principal del juego.
 * @author aztlek
 */
public class ElPinguinoTux_pasoApaso {
    
    public static void main(String[] args) {
        // Instanciar el marco (la ventana) y averiguar la resolución
        JFrame marco = new JFrame("El Pingüino Tux");
        marco.setUndecorated(true);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height - 22;

        // El escenario
        Escenario escenario = new Escenario(0, 0, screenWidth, screenHeight, marco);
        marco.add(escenario);

        marco.setSize(screenWidth, screenHeight);
        marco.setVisible(true);
    }
    
}
