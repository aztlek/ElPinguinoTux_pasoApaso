/*
 * Copyright (C) 2015 aztlek
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
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Luis Alejandro Bernal Romero (Aztlek)
 */
public class LetreroGanaste extends ObjetoGrafico {

    private final CuentaRegresiva cuentaRegresiva;
    private final Iceberg iceberg;
    private final Tux bebe, tux0, tux1;

    public LetreroGanaste(double x, double y, double width, double height, CuentaRegresiva cuentaRegresiva) {
        super(x, y, width, height, 190, 190);
        this.cuentaRegresiva = cuentaRegresiva;

        // La famila
        iceberg = new Iceberg(x, y, getTotalWidth(), getTotalHeight());
        bebe = new Tux(x + 81, y + 90, 29, 35);
        tux0 = new Tux(x + 23, y + 35, 72, 90);
        tux1 = new Tux(x + 95, y + 35, 72, 90);
    }

//    @Override
//    public void reiniciar() {
//        super.reiniciar();
//        setVisible(false);
//    }
    
    @Override
    public void paint(Graphics2D graphics2D) {
        cuentaRegresiva.cancel();
        AffineTransform transformacionesAnteriores = graphics2D.getTransform();

        // Trnasformaciones: transladar y escalar
        graphics2D.translate(getX(), getY());
        graphics2D.scale(getEscalaX(), getEscalaY());

        iceberg.paint(graphics2D);
        tux0.paint(graphics2D);
        tux1.paint(graphics2D);
        bebe.paint(graphics2D);

        // La cuenta
        double factorEscala = 1.35;
        Font oldFont = graphics2D.getFont();

        graphics2D.setColor(new Color(51, 253, 155));
        String stringConteo = cuentaRegresiva + "";
        graphics2D.setFont(new Font("sans", Font.BOLD, 27));
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        int widthFontConteo = fontMetrics.stringWidth(stringConteo);
        graphics2D.drawString(stringConteo, Math.round(x + (getTotalWidth() - widthFontConteo) / 2d), Math.round(y + 180));

        // Restablecer el anterior tipo de letra
        graphics2D.setFont(oldFont);

//        // Rejilla de referencia
//        new Grid(getTotalWidth(), getTotalHeight()).paint(graphics2D);

        graphics2D.setTransform(transformacionesAnteriores);
    }// pain()

} // class LetreroGanaste
