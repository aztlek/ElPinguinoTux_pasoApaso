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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import sun.awt.X11.XConstants;

/**
 *
 * @author aztlek
 */
public class Estallido extends ObjetoGrafico{

    public Estallido(double x, double y, double width, double height) {
        super(x, y, width, height, 190, 190);
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(getX(), getY());
        graphics2D.scale(getEscalaX(), getEscalaY());

        // Estallido exterior
        GeneralPath poligono = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
        poligono.moveTo(0, 0);
        poligono.lineTo(40, 40);
        poligono.lineTo(60, 0);
        poligono.lineTo(95, 75);
        poligono.lineTo(110, 0);
        poligono.lineTo(120, 30);
        poligono.lineTo(190, 0);
        poligono.lineTo(160, 80);
        poligono.lineTo(150,130);
        poligono.lineTo(190, 190);
        poligono.lineTo(90, 150);
        poligono.lineTo(55, 115);
        poligono.lineTo(0, 190);
        poligono.lineTo(30, 70);
        poligono.closePath();
        float[] fractions = {0.33f, 1f};
        Color[] colors = {Color.RED, Color.ORANGE};
        RadialGradientPaint gradiente = new RadialGradientPaint(
                (float)totalWidth / 2f, 
                (float)totalHeight / 2f, 
                (float)totalWidth / 2f, 
                fractions, 
                colors
        );
        graphics2D.setPaint(gradiente);
        graphics2D.fill(poligono);
        
//        // La grilla de referencia
//        new Grid(totalWidth, totalHeight).paint(graphics2D);
        
        graphics2D.setTransform(affineTransform);
        
    }
    
}
