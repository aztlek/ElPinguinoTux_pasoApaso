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
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.TimerTask;

/**
 *
 * @author Luis Alejandro Bernal Romero (Aztlek)
 */
public class CuentaRegresiva extends TimerTask {
    private final double x, y, width, height, totalWidth = 290, totalHeight = 160;
    private final double escalaX, escalaY;
    private short horas = 24;
    private short minutos = 59;
    private short paso = 1;
    private final Escenario escenario;

    public CuentaRegresiva(double x, double y, double width, double height, Escenario escenario) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.escalaX = width / totalWidth;
        this.escalaY = height / totalHeight;
        this.escenario = escenario;
    }

    public void paint(Graphics2D graphics2D) {
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(getX(), getY());
        graphics2D.scale(getEscalaX(), getEscalaY());

        graphics2D.setColor(new Color(0, 128, 128));
        graphics2D.setFont(new Font("sans", Font.BOLD, 65));
        graphics2D.drawString("Tiempo", 11, 50);

        graphics2D.setFont(new Font("sans", Font.BOLD, 52));
        graphics2D.drawString(toString(), 63.5f, 160.0f);

        graphics2D.setTransform(affineTransform);
    }

    public void decrementar() {
        if (horas > 0) {
            minutos -= paso;
            if (minutos < 0) {
                horas--;
                minutos = 60;
            }
        } else {
            horas = 0;
            minutos = 0;
        }
    }

    @Override
    public String toString() {
        String cadena = "";
        cadena += (horas >= 0 && horas <= 9) ? "0" : "";
        cadena += horas + ":" ;
        cadena += (minutos >= 0 && minutos <= 9) ? "0" : "";
        cadena += minutos;
        return cadena;
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

    public double getTotalWidth() {
        return totalWidth;
    }

    public double getTotalHeight() {
        return totalHeight;
    }

    public double getEscalaX() {
        return escalaX;
    }

    public double getEscalaY() {
        return escalaY;
    }

    public short getHoras() {
        return horas;
    }

    public void setHoras(short horas) {
        this.horas = horas;
    }

    public short getMinutos() {
        return minutos;
    }

    public void setMinutos(short minutos) {
        this.minutos = minutos;
    }

    public short getPaso() {
        return paso;
    }

    public void setPaso(short paso) {
        this.paso = paso;
    }

    @Override
    public void run() {
        decrementar();
        escenario.repaint();
    }
}
