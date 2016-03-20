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

/**
 *
 * @author aztlek
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author aztlek
 */
public class Orca {

    private final double totalWidth = 280.0d;
    private final double totalHeight = 160.0d;
    private double x, y;
    private final double width, height;
    private final double escalaX, escalaY;
    private TipoDireccion direccion = TipoDireccion.derecha;

    public Orca(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.escalaX = width / totalWidth;
        this.escalaY = height / totalHeight;
    } // Orca()

    public void paint(Graphics2D graphics2D) {
        // Transladar y escalar
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(getX(), getY());
        graphics2D.scale(getEscalaX(), getEscalaY());

        // Cuerpo
        graphics2D.setColor(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(50, 65, 230, 71));

        // Aleta caudal
        graphics2D.setColor(Color.BLACK);

        // Puntos aleta caudal
        int[] aletaCaudalx = {0, 79, 22};
        int[] aletaCaudaly = {63, 102, 111};
        graphics2D.fillPolygon(aletaCaudalx, aletaCaudaly, aletaCaudalx.length);

        // Puntos aleta dorsal
        int[] aletaDorsalx = {158, 186, 150};
        int[] aletaDorsaly = {0, 66, 66};
        graphics2D.fillPolygon(aletaDorsalx, aletaDorsaly, aletaDorsalx.length);

        // Mancha pos ocular
        graphics2D.setColor(Color.WHITE);
        graphics2D.fill(new Ellipse2D.Double(201, 76, 42, 20));

        // Ojo
        graphics2D.setColor(Color.RED);
        graphics2D.fill(new Ellipse2D.Double(245, 92, 3, 3));

        // Aleta pectoral
        graphics2D.setColor(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Double(177, 122, 26, 40));

        // Mancha ventral
        graphics2D.setColor(Color.WHITE);
        graphics2D.fill(new Ellipse2D.Double(74, 104, 58, 27));
        graphics2D.fill(new Ellipse2D.Double(172, 102, 88, 29));
        
//        // Rejilla de referencia
//        new Grid(getTotalWidth(), getTotalHeight()).paint(graphics2D);

        // Volver a la translación y escalación anterior
        graphics2D.setTransform(affineTransform);

    } // paint()

    public double getTotalWidth() {
        return totalWidth;
    }

    public double getTotalHeight() {
        return totalHeight;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getEscalaX() {
        return escalaX;
    }

    public double getEscalaY() {
        return escalaY;
    }

    public TipoDireccion getDireccion() {
        return direccion;
    }

    public void setDireccion(TipoDireccion direccion) {
        this.direccion = direccion;
    }

} // class Orca