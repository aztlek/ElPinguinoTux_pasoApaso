/*
 * Copyright (C) 2015 Luis Alejandro Bernal Romero (Aztlek)
 * Contact: aztlek@gmail.com
 *
 * This file is part of El Pingüino Tux Refactorizado.
 *
 * El Pingüino Tux Refactorizado is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * El Pingüino Tux Refactorizado is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with El Pingüino Tux Refactorizado.  If not, see <http://www.gnu.org/licenses/>.
 */
package elpinguinotux_pasoapaso;

import java.awt.Graphics2D;

/**
 * La familia de Tux
 *
 * @author Luis Alejandro Bernal Romero (Aztlek)
 */
public class FamiliaTux extends ObjetoGrafico{
    private final Tux bebe;
    private final Tux pareja;

    public FamiliaTux(double x, double y, double width, double height) {
        super(x, y, width, height, 223, 190);
        bebe = new Tux(
                x + width * 0 / getTotalWidth(),
                y + height * 110 / getTotalHeight(),
                width * 62 / getTotalWidth(),
                height * 77 / getTotalHeight(),
                null
        );
        pareja = new Tux(
                x + width * 60 / getTotalWidth(),
                y + height * 0 / getTotalHeight(),
                width * 154 / getTotalWidth(),
                height * 190 / getTotalHeight(),
                null
        );
    } // FamiliaTux()

    @Override
    public void paint(Graphics2D graphics2D) {
        bebe.paint(graphics2D);
        pareja.paint(graphics2D);
    }
} // class FamiliaTux
