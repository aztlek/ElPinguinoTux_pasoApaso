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

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author aztlek
 */
public class Escenario extends Canvas {

    private final int x;
    private final int y;
    private final double totalWidth = 280.0d;
    private final double totalHeight = 160.0d;
    private final JFrame marco;
    private final double escalaX;
    private final double escalaY;

    public Escenario(int x, int y, int width, int height, JFrame marco) {
        setBounds(x, y, width, height);
        this.x = x;
        this.y = y;
        this.marco = marco;
        this.escalaX = (double) width / totalWidth;
        this.escalaY = (double) height / totalHeight;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;

        // Transformaciones
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(x, y);
        graphics2D.scale(escalaX, escalaY);

//        // Dibujar el protagonista
//        Tux tux = new Tux(0.0d, 0.0d, totalWidth / 2.0d, totalHeight);
//        tux.paint(graphics2D);
        
        // Dibujar el villano
        Orca orca = new Orca(0.0d, 0.0d, totalWidth, totalHeight);
        orca.paint(graphics2D);
        
//        // Dibujar los villanos
//        
//        Orca[] orcas = new Orca[4];
//        Random random = new Random();
//        double  orcasInicioX = 27.0d, 
//                orcasInicioY = 12.0d,
//                orcaSeparaciónAlto = 37.0d;
//        
//        // Instanciar las orcas
//        for (int i = 0; i < orcas.length; i++) {
//            orcas[i] = new Orca(
//                    orcasInicioX + random.nextDouble() * (111.0d - 27.0d), 
//                    orcasInicioY + i * orcaSeparaciónAlto, 
//                    43.0d, 
//                    23.0d
//            );
//        }
//        
//        // Dibujar las orcas
//        for (Orca orca : orcas) {
//            orca.paint(graphics2D);
//        }
//        
        // Rejilla de referencia
        new Grid(getTotalWidth(), getTotalHeight()).paint(graphics2D);
        
        // Reestrablece las transformaciones
        graphics2D.setTransform(affineTransform);
    } // paint()

    public double getTotalWidth() {
        return totalWidth;
    }

    public double getTotalHeight() {
        return totalHeight;
    }

    
} // class Escenario
