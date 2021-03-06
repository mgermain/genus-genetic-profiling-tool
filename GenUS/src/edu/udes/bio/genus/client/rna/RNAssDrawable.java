/*
 * GenUS: Genetic Profiling Tool v.1.0
 * Copyright (C) 2009 Université de Sherbrooke
 * Contact: code.google.com/p/genus-genetic-profiling-tool/
 * 
 * This is a free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or any later version.
 * 
 * This project is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY. See the GNU
 * Lesser General Public License for more details.
 *  
 * Contributors: Mathieu Germain, Gabriel Girard, Alex Rouillard, Alexei Nordell-Markovits
 * 
 * December 2009
 * 
 */
package edu.udes.bio.genus.client.rna;

import java.util.HashMap;

import com.objetdirect.tatami.client.gfx.Color;
import com.objetdirect.tatami.client.gfx.GraphicObject;
import com.objetdirect.tatami.client.gfx.Line;
import com.objetdirect.tatami.client.gfx.Path;
import com.objetdirect.tatami.client.gfx.Point;

import edu.udes.bio.genus.client.pool.PoolObservable;
import edu.udes.bio.genus.client.ui.canvas.Drawer;

/**
 * The Class RNAssDrawable.
 * 
 * This is the class that handle the display of a RNAss into the canvas
 */
public class RNAssDrawable extends PoolObservable {

    /**
     * The Enum DrawStyle.
     */
    public static enum DrawStyle {
        Linear_Squared, Linear_Round, Circular
    };

    protected static final int NUCLEOTIDE_RADIUS = 15;
    protected static final int NUCLEOTIDE_DISTANCE = 40;
    protected DrawStyle m_style;
    protected boolean m_visible;
    protected String m_name;
    protected Color m_color;
    protected Drawer m_gc;
    protected StrandGO m_graphicGroup;
    protected RNAss m_rnass;

    /**
     * Instantiates a new RNA secondary structure drawable.
     * 
     * @param r
     *            the secondary structure
     * @param c
     *            the drawer
     * @param x
     *            the initial x coordinate
     * @param y
     *            the initial y coordinate
     * @param scale
     *            the scale factor
     * @param color
     *            the fill color
     * @param style
     *            the display style
     * @param visible
     *            is the object visible?
     * @param name
     *            the display name
     */
    public RNAssDrawable(RNAss r, Drawer c, int x, int y, float scale, Color color, DrawStyle style, boolean visible, String name) {
        this.m_graphicGroup = new StrandGO();
        this.m_gc = c;
        this.m_rnass = r;
        this.m_name = name;
        this.m_visible = visible;
        this.m_style = style;
        this.m_graphicGroup.translate(x, y);
        this.m_color = color;
        setDisplay();
        draw();
        this.m_graphicGroup.scale(scale);
    }

    /**
     * Instantiates a new RNA secondary structure drawable.
     * 
     * @param strDp
     *            the dot parenthesis input for the secondary structure
     * @param strSeq
     *            the sequence input for the secondary structure
     * @param x
     *            the initial x coordinate
     * @param y
     *            the initial y coordinate
     * @param scale
     *            the scale factor
     * @param color
     *            the fill color
     * @param style
     *            the display style
     * @param visible
     *            is the object visible?
     * @param name
     *            the display name
     * @param c
     *            the drawer
     * 
     * @throws RNAException
     */
    public RNAssDrawable(String strDp, String strSeq, int x, int y, int scale, Color color, DrawStyle style, boolean visible, String name, Drawer c) throws RNAException {
        this(new RNAss(strDp, strSeq), c, x, y, scale, color, style, visible, name);
    }

    /**
     * Instantiates a new RNA secondary structure drawable.
     * 
     * @param strDp
     *            the dot parenthesis input for the secondary structure
     * @param strSeq
     *            the sequence input for the secondary structure
     * @param scale
     *            the scale factor
     * @param color
     *            the fill color
     * @param style
     *            the display style
     * @param name
     *            the display name
     * @param c
     *            the drawer
     * 
     * @throws RNAException
     */
    public RNAssDrawable(String strDp, String strSeq, int scale, Color color, DrawStyle drawStyle, String name, Drawer c) throws RNAException {
        this(new RNAss(strDp, strSeq), c, c.getOffsetWidth() / 2, c.getOffsetHeight() / 2, scale, color, drawStyle, true, name);
    }

    /**
     * Instantiates a new RNA secondary structure drawable.
     * 
     * @param r
     *            the secondary structure
     * @param c
     *            the drawer
     */
    public RNAssDrawable(RNAss r, Drawer c) {
        this(r, c, c.getOffsetWidth() / 2, c.getOffsetHeight() / 2, (float) c.scaleFactor, Color.TEAL, DrawStyle.Linear_Squared, true, r.getDotParentheses());
    }

    /**
     * Instantiates a new RNA secondary structure drawable.
     * 
     * @param c
     *            the drawer
     * 
     * @throws RNAException
     */
    public RNAssDrawable(Drawer c) throws RNAException {
        this(new RNAss("", ""), c);
    }

    /**
     * Instantiates a new RNA secondary structure drawable.
     * 
     * @param strDp
     *            the dot parenthesis input for the secondary structure
     * @param strSeq
     *            the sequence input for the secondary structure
     * @param c
     *            the drawer
     * 
     * @throws RNAException
     */
    public RNAssDrawable(String strDp, String strSeq, Drawer c) throws RNAException {
        this(new RNAss(strDp, strSeq), c);
    }

    private void draw_circular() {
        final double angle = 2 * Math.PI / (this.m_rnass.size() + 0.5);
        double cur_angle = 1.5 * Math.PI - angle / 4;
        int minY = 0;
        int cur_x = RNAssDrawable.NUCLEOTIDE_DISTANCE / 2;
        int cur_y = 0;
        // set nodes position in circle
        final HashMap<Nucleotide, Point> positions = new HashMap<Nucleotide, Point>();
        for (final Nucleotide cur : this.m_rnass) {
            cur_x += (int) (Math.sin(cur_angle) * RNAssDrawable.NUCLEOTIDE_DISTANCE);
            cur_y += (int) (Math.cos(cur_angle) * RNAssDrawable.NUCLEOTIDE_DISTANCE);
            cur_angle -= angle;
            positions.put(cur, new Point(cur_x, cur_y));
            if (minY > cur_y) {
                minY = cur_y;
            }
        }
        // center the circle of nodes
        final int radius = minY / -2;
        for (final Nucleotide cur : this.m_rnass) {
            positions.get(cur).setY(positions.get(cur).getY() + radius);
        }
        // link nodes together
        for (final Nucleotide cur : this.m_rnass) {
            cur_angle -= angle;
            if (cur.next != null) {
                final Point pointA = new Point(positions.get(cur).getX() - Math.sin(cur_angle) * RNAssDrawable.NUCLEOTIDE_RADIUS, positions.get(cur).getY() - Math.cos(cur_angle) * RNAssDrawable.NUCLEOTIDE_RADIUS);
                final Point pointB = new Point(positions.get(cur.next).getX() + Math.sin(cur_angle) * RNAssDrawable.NUCLEOTIDE_RADIUS, positions.get(cur.next).getY() + Math.cos(cur_angle) * RNAssDrawable.NUCLEOTIDE_RADIUS);
                this.m_graphicGroup.add(new Line(pointA, pointB));
            }
            if (cur.isLinkedEnd) {
                final Point pointA = new Point(positions.get(cur).getX(), positions.get(cur).getY());
                final Point pointB = new Point(positions.get(cur.linked).getX(), positions.get(cur.linked).getY());
                this.m_graphicGroup.add(new Line(pointA, pointB));
            }
        }
        // display nodes
        for (final Nucleotide cur : this.m_rnass) {
            addNode(positions.get(cur), RNAssDrawable.NUCLEOTIDE_RADIUS, String.valueOf(cur.ribose));
        }
    }

    private void draw_linear(boolean squaredLink) {
        final int center = ((this.m_rnass.size() - 1) * RNAssDrawable.NUCLEOTIDE_DISTANCE / 2);
        int cur_pos = 0;
        final HashMap<Nucleotide, Point> positions = new HashMap<Nucleotide, Point>();
        for (final Nucleotide cur : this.m_rnass) {
            positions.put(cur, new Point(0 - center + cur_pos, 0));
            cur_pos += RNAssDrawable.NUCLEOTIDE_DISTANCE;
        }
        Point curD = null;
        Point A, B;
        for (final Nucleotide cur : this.m_rnass) {
            A = (curD == null) ? null : new Point(curD.getX() + RNAssDrawable.NUCLEOTIDE_RADIUS, curD.getY());
            curD = positions.get(cur);
            if (A != null) {
                B = new Point(curD.getX() - RNAssDrawable.NUCLEOTIDE_RADIUS, curD.getY());
                this.m_graphicGroup.add(new Line(A, B));
            }
            if (cur.isLinkedEnd) {
                if (squaredLink) {
                    A = new Point(curD.getX(), curD.getY() - RNAssDrawable.NUCLEOTIDE_RADIUS);
                    B = new Point(curD.getX(), curD.getY() - getLinkHeight(cur.linked, cur));
                    this.m_graphicGroup.add(new Line(A, B));
                    A = new Point(positions.get(cur.linked).getX(), curD.getY() - getLinkHeight(cur.linked, cur));
                    this.m_graphicGroup.add(new Line(B, A));
                    B = new Point(positions.get(cur.linked).getX(), curD.getY() - RNAssDrawable.NUCLEOTIDE_RADIUS);
                    this.m_graphicGroup.add(new Line(A, B));
                } else {
                    final Path p = new Path();
                    p.moveTo(curD.getX(), curD.getY() - RNAssDrawable.NUCLEOTIDE_RADIUS);
                    final double x1 = curD.getX();
                    final double y1 = curD.getY() - RNAssDrawable.NUCLEOTIDE_RADIUS;
                    final double x2 = positions.get(cur.linked).getX();
                    final double y2 = positions.get(cur.linked).getY() - RNAssDrawable.NUCLEOTIDE_RADIUS;
                    A = new Point(x1, y1);
                    B = new Point(x2, y2);
                    p.arcTo(12, 5, 1, true, false, B);
                    p.arcTo(12, 5, 1, true, true, A);
                    this.m_graphicGroup.add(p);
                }
            }
        }

        for (final Nucleotide cur : this.m_rnass) {
            addNode(positions.get(cur), RNAssDrawable.NUCLEOTIDE_RADIUS, "" + cur.ribose);
        }
    }

    private void addNode(Point pos, int r, String ribose) {
        this.m_graphicGroup.add(new NucleotideGO(r, ribose).translate((int) pos.getX(), (int) pos.getY()));
    }

    private int getLinkHeight(Nucleotide a, Nucleotide b) {
        int maxLink = 2;
        int count = 2;
        Nucleotide tmp = a;
        while (tmp != b) {
            if (tmp.linked != null) {
                count = tmp.isLinkedEnd ? --count : ++count;
                maxLink = (count > maxLink) ? count : maxLink;
            }
            tmp = tmp.next;
        }
        return maxLink * RNAssDrawable.NUCLEOTIDE_RADIUS / 2;
    }

    /**
     * Draw.
     * draw the object in the drawer
     */
    public void draw() {
        this.m_graphicGroup.clear();
        switch (this.m_style) {
        case Linear_Squared:
            draw_linear(true);
            break;
        case Linear_Round:
            draw_linear(false);
            break;
        case Circular:
            draw_circular();
            break;
        }
        this.m_graphicGroup.setFillColor(getColor());
        this.m_graphicGroup.updateContent();
    }

    /**
     * Gets the x.
     * 
     * @return the x coordinate
     */
    public int getX() {
        return (int) this.m_graphicGroup.getX();
    }

    /**
     * Gets the y.
     * 
     * @return the y coordinate
     */
    public int getY() {
        return (int) this.m_graphicGroup.getY();
    }

    /**
     * Gets the draw style.
     * 
     * @return the draw style
     */
    public DrawStyle getDrawStyle() {
        return this.m_style;
    }

    /**
     * Checks if is visible.
     * 
     * @return true, if is visible
     */
    public boolean isVisible() {
        return this.m_visible;
    }

    /**
     * Gets the name.
     * 
     * @return the display name
     */
    public String getName() {
        return this.m_name;
    }

    /**
     * Gets the color.
     * 
     * @return the color
     */
    public Color getColor() {
        return this.m_color;
    }

    /**
     * Gets the sacle factor.
     * 
     * @return the sacle factor
     */
    public double getSacleFactor() {
        return this.m_graphicGroup.scale;
    }

    /**
     * Gets the angle.
     * 
     * @return the current angle
     */
    public double getAngle() {
        return this.m_graphicGroup.angle;
    }

    /**
     * Gets the dot parentesis.
     * 
     * @return the dot parentesis string
     */
    public String getDotParentesis() {
        return this.m_rnass.getDotParentheses();
    }

    /**
     * Gets the sequence.
     * 
     * @return the sequence
     */
    public String getSequence() {
        String res = this.m_rnass.getSequence();
        while (res.length() > 0 && res.charAt(res.length() - 1) == ' ') {
            res = res.substring(0, res.length() - 1);
        }
        return res;
    }

    /**
     * Sets the draw style.
     * 
     * @param s
     *            the style
     * 
     * @return this
     */
    public RNAssDrawable setDrawStyle(DrawStyle s) {
        this.m_style = s;
        draw();
        notifyObserversMod(NotifyMessage.UPDATE);
        return this;
    }

    /**
     * Sets the angle.
     * 
     * @param a
     *            the angle
     * 
     * @return this
     */
    public RNAssDrawable setAngle(float a) {
        this.m_graphicGroup.setRotation(a);
        notifyObserversMod(NotifyMessage.UPDATE);
        return this;
    }

    /**
     * Sets the visible.
     * 
     * @param v
     *            isVisible?
     * 
     * @return this
     */
    public RNAssDrawable setVisible(boolean v) {
        this.m_visible = v;
        setDisplay();
        notifyObserversMod(NotifyMessage.UPDATE);
        return this;
    }

    private void setDisplay() {
        if (this.m_visible) {
            this.m_gc.add(this.m_graphicGroup, 0, 0);
        } else {
            this.m_gc.remove(this.m_graphicGroup);
        }
    }

    /**
     * Sets the name.
     * 
     * @param n
     *            the display name
     * 
     * @return this
     */
    public RNAssDrawable setName(String n) {
        this.m_name = n;
        notifyObserversMod(NotifyMessage.UPDATE);
        return this;
    }

    /**
     * Sets the x coordinate.
     * 
     * @param x
     * 
     * 
     * @return this
     */
    public RNAssDrawable setX(int x) {
        return setPosition(x, getY());
    }

    /**
     * Sets the y coordinate.
     * 
     * @param y
     * 
     * @return this
     */
    public RNAssDrawable setY(int y) {
        return setPosition(getX(), y);
    }

    /**
     * Sets the position.
     * 
     * @param x
     * @param y
     * 
     * @return this
     */
    public RNAssDrawable setPosition(int x, int y) {
        this.m_graphicGroup.translate(getX() - x, getY() - y);
        notifyObserversMod(NotifyMessage.UPDATE);
        return this;
    }

    /**
     * Translate.
     * 
     * @param x
     *            displacement in x
     * @param y
     *            displacement in y
     * 
     * @return this
     */
    public RNAssDrawable translate(int xLag, int yLag) {
        this.m_graphicGroup.translate(xLag, yLag);
        notifyObserversMod(NotifyMessage.UPDATE);
        return this;
    }

    /**
     * Rotate.
     * 
     * @param a
     *            the angle
     * 
     * @return this
     */
    public RNAssDrawable rotate(float a) {
        this.m_graphicGroup.rotate(a);
        notifyObserversMod(NotifyMessage.UPDATE);
        return this;
    }

    /**
     * Sets the color.
     * 
     * @param c
     *            the color
     * 
     * @return this
     */
    public RNAssDrawable setColor(Color c) {
        this.m_color = c;
        this.m_graphicGroup.setFillColor(c);
        notifyObserversMod(NotifyMessage.UPDATE);
        return this;
    }

    /**
     * Scale.
     * 
     * @param s
     *            the scale factor
     * 
     * @return this
     */
    public RNAssDrawable scale(double s) {
        final int preX = (this.m_gc.getOffsetWidth() / 2) - getX();
        final int preY = (this.m_gc.getOffsetHeight() / 2) - getY();
        final double hypo = Math.sqrt((preX * preX) + (preY * preY));
        int newX = (int) (hypo * s * Math.abs(preX / hypo));
        int newY = (int) (hypo * s * Math.abs(preY / hypo));
        newX = (preX < 0) ? -newX : newX;
        newY = (preY < 0) ? -newY : newY;
        translate(-(newX - preX), -(newY - preY));
        this.m_graphicGroup.scale((float) s);
        notifyObserversMod(NotifyMessage.UPDATE);
        return this;
    }

    /**
     * Sets the RNAss sequence.
     * 
     * @param s
     *            the sequence
     * 
     * @return this
     * 
     * @throws RNAException
     */
    public RNAssDrawable setRNAssSequence(String s) throws RNAException {
        this.m_rnass.setSequence(s);
        draw();
        notifyObserversMod(NotifyMessage.UPDATE);
        return this;
    }

    /**
     * Sets the RNAss structure from dot parentheses.
     * 
     * @param dp
     *            the dot parentheses input
     * 
     * @return this
     * 
     * @throws RNAException
     */
    public RNAssDrawable setRNAssDotParentheses(String dp) throws RNAException {
        this.m_rnass.setDotParentesis(dp);
        draw();
        notifyObserversMod(NotifyMessage.UPDATE);
        return this;
    }

    /**
     * Test if contains.
     * 
     * @param rna
     *            The RNA secondary structure
     * 
     * @return true, if contains
     */
    public Boolean Contain(RNAss rna) {
        return this.m_rnass == rna;
    }

    /**
     * Test if contains.
     * 
     * @param go
     *            The graphic object
     * 
     * @return true, if contains
     */
    public Boolean Contain(GraphicObject go) {
        return this.m_graphicGroup == go || this.m_graphicGroup.contains(go);
    }

    /**
     * Test if contains a standGO.
     * 
     * @param go
     *            The graphic object (strandGO)
     * 
     * @return true, if contains
     */
    public Boolean ContainVirtualGroup(GraphicObject go) {
        return this.m_graphicGroup == go;
    }

}
