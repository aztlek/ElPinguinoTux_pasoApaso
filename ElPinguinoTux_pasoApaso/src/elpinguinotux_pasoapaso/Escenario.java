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
import javax.swing.JFrame;

/**
 *
 * @author aztlek
 */
public class Escenario extends Canvas {

    private int x;
    private int y;
    private double totalWidth;
    private double totalHeight;
    private JFrame marco;
    private double escalaX;
    private double escalaY;

    public Escenario(int x, int y, int width, int height, double totalWidth, double totalHeight, JFrame marco) {
        setBounds(x, y, width, height);
        this.x = x;
        this.y = y;
        this.totalWidth = totalWidth;
        this.totalHeight = totalHeight;
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

        // Dibujar el protagonista
        Tux tux = new Tux(x, y, totalHeight, totalHeight);
        tux.paint(graphics2D);
    }

}
