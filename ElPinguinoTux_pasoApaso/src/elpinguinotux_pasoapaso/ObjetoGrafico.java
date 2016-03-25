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

import java.awt.Graphics2D;

/**
 *
 * @author aztlek
 */
public abstract class ObjetoGrafico {
    private double x, y;
    private final double width, height;
    private final double totalWidth, totalHeight;
    private final double escalaX, escalaY;

    public ObjetoGrafico(double x, double y, double width, double height, double totalWidth, double totalHeight) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.totalWidth = totalWidth;
        this.totalHeight = totalHeight;
        this.escalaX = width / totalWidth;
        this.escalaY = height / totalHeight;
    }

    public abstract void paint(Graphics2D graphics2D);
    
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
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
    
}
