package edu.udes.bio.genus.client.rna;

import java.util.HashMap;

import com.objetdirect.tatami.client.gfx.Circle;
import com.objetdirect.tatami.client.gfx.Color;
import com.objetdirect.tatami.client.gfx.GraphicCanvas;
import com.objetdirect.tatami.client.gfx.GraphicObject;
import com.objetdirect.tatami.client.gfx.Line;
import com.objetdirect.tatami.client.gfx.Path;
import com.objetdirect.tatami.client.gfx.Point;
import com.objetdirect.tatami.client.gfx.Text;

import edu.udes.bio.genus.client.pool.PoolObservable;
import edu.udes.bio.genus.client.ui.canvas.Drawer;

public class RNAssDrawable extends PoolObservable {
    public static enum DrawStyle {
        Linear_Squared, Linear_Round, Circular
    };

    protected static final int NUCLEOTIDE_RADIUS = 15;
    protected static final int NUCLEOTIDE_DISTANCE = 40;
    protected DrawStyle m_style;
    protected boolean m_visible;
    protected String m_name;
    protected Color m_color;
    protected GraphicCanvas m_gc;
    protected CostumVirtualGroup m_graphicGroup;
    protected RNAss m_rnass;

    public RNAssDrawable(RNAss r, GraphicCanvas c, int x, int y, int scale, Color color, DrawStyle style, boolean visible, String name) {
        this.m_graphicGroup = new CostumVirtualGroup();
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

    public RNAssDrawable(String strDp, String strGacu, int x, int y, int scale, Color color, DrawStyle style, boolean visible, String name, GraphicCanvas c) throws RNAException {
        this(new RNAss(strDp, strGacu), c, x, y, scale, color, style, visible, name);
    }

    public RNAssDrawable(String strDp, String strGacu, int scale, Color color, DrawStyle drawStyle, String name, GraphicCanvas c) throws RNAException {
        this(new RNAss(strDp, strGacu), c, c.getOffsetWidth() / 2, c.getOffsetHeight() / 2, scale, color, drawStyle, true, name);
    }

    public RNAssDrawable(RNAss r, GraphicCanvas c) {
        this(r, c, c.getOffsetWidth() / 2, c.getOffsetHeight() / 2, 1, Color.TEAL, DrawStyle.Linear_Squared, true, r.getDotParentheses());
    }

    public RNAssDrawable(Drawer c) throws RNAException {
        this(new RNAss("", ""), c);
    }

    public RNAssDrawable(String strDp, String strGacu, GraphicCanvas c) throws RNAException {
        this(new RNAss(strDp, strGacu), c);
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
            if (cur.m_next != null) {
                final Point pointA = new Point(positions.get(cur).getX() - Math.sin(cur_angle) * RNAssDrawable.NUCLEOTIDE_RADIUS, positions.get(cur).getY() - Math.cos(cur_angle) * RNAssDrawable.NUCLEOTIDE_RADIUS);
                final Point pointB = new Point(positions.get(cur.m_next).getX() + Math.sin(cur_angle) * RNAssDrawable.NUCLEOTIDE_RADIUS, positions.get(cur.m_next).getY() + Math.cos(cur_angle) * RNAssDrawable.NUCLEOTIDE_RADIUS);
                this.m_graphicGroup.add(new Line(pointA, pointB));
            }
            if (cur.m_linked_previus) {
                final Point pointA = new Point(positions.get(cur).getX(), positions.get(cur).getY());
                final Point pointB = new Point(positions.get(cur.m_linked).getX(), positions.get(cur.m_linked).getY());
                this.m_graphicGroup.add(new Line(pointA, pointB));
            }
        }
        // display nodes
        for (final Nucleotide cur : this.m_rnass) {
            addNode(positions.get(cur), RNAssDrawable.NUCLEOTIDE_RADIUS, String.valueOf(cur.m_ribose));
        }
    }

    private void draw_linear(boolean squaredLink) {
        final int center = (this.m_rnass.size() * RNAssDrawable.NUCLEOTIDE_DISTANCE / 2);
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
            if (cur.m_linked_previus) {
                if (squaredLink) {
                    A = new Point(curD.getX(), curD.getY() - RNAssDrawable.NUCLEOTIDE_RADIUS);
                    B = new Point(curD.getX(), curD.getY() - getLinkHeight(cur.m_linked, cur));
                    this.m_graphicGroup.add(new Line(A, B));
                    A = new Point(positions.get(cur.m_linked).getX(), curD.getY() - getLinkHeight(cur.m_linked, cur));
                    this.m_graphicGroup.add(new Line(B, A));
                    B = new Point(positions.get(cur.m_linked).getX(), curD.getY() - RNAssDrawable.NUCLEOTIDE_RADIUS);
                    this.m_graphicGroup.add(new Line(A, B));
                } else {
                    final Path p = new Path();
                    p.moveTo(curD.getX(), curD.getY() - RNAssDrawable.NUCLEOTIDE_RADIUS);
                    final double x1 = curD.getX();
                    final double y1 = curD.getY() - RNAssDrawable.NUCLEOTIDE_RADIUS;
                    final double x2 = positions.get(cur.m_linked).getX();
                    final double y2 = positions.get(cur.m_linked).getY() - RNAssDrawable.NUCLEOTIDE_RADIUS;
                    A = new Point(x1, y1);
                    B = new Point(x2, y2);
                    p.arcTo(12, 5, 1, true, false, B);
                    p.arcTo(12, 5, 1, true, true, A);
                    this.m_graphicGroup.add(p);
                }
            }
        }

        for (final Nucleotide cur : this.m_rnass) {
            addNode(positions.get(cur), RNAssDrawable.NUCLEOTIDE_RADIUS, "" + cur.m_ribose);
        }
    }

    private void addNode(Point pos, int r, String ribose) {
        final GraphicObject cir = new Circle(r).setFillColor(getColor());
        cir.translate((int) pos.getX(), (int) pos.getY());
        this.m_graphicGroup.add(cir);
        final GraphicObject t = new Text(ribose).setFillColor(Color.WHITE).setStrokeColor(Color.WHITE);
        t.translate((int) pos.getX() - r / 2, (int) pos.getY() + r / 2).scale(1.5f);
        this.m_graphicGroup.add(t);
    }

    private int getLinkHeight(Nucleotide a, Nucleotide b) {
        int maxLink = 2;
        int count = 2;
        Nucleotide tmp = a;
        while (tmp != b) {
            if (tmp.m_linked != null) {
                count = tmp.m_linked_previus ? --count : ++count;
                maxLink = (count > maxLink) ? count : maxLink;
            }
            tmp = tmp.m_next;
        }
        return maxLink * RNAssDrawable.NUCLEOTIDE_RADIUS / 2;
    }

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

    public int getX() {
        return (int) this.m_graphicGroup.getX();
    }

    public int getY() {
        return (int) this.m_graphicGroup.getY();
    }

    public DrawStyle getDrawStyle() {
        return this.m_style;
    }

    public boolean isVisible() {
        return this.m_visible;
    }

    public String getName() {
        return this.m_name;
    }

    public Color getColor() {
        return this.m_color;
    }

    public double getSacleFactor() {
        return this.m_graphicGroup.m_scale;
    }

    public double getAngle() {
        return this.m_graphicGroup.m_angle;
    }

    public String getDotParentesis() {
        return this.m_rnass.getDotParentheses();
    }

    public String getSequence() {
        String res = this.m_rnass.getSequence();
        while (res.length() > 0 && res.charAt(res.length() - 1) == ' ') {
            res = res.substring(0, res.length() - 1);
        }
        return res;
    }

    public RNAssDrawable setDrawStyle(DrawStyle s) {
        this.m_style = s;
        draw();
        notifyObserversMod(NotifyMessage.UPDATE);
        return this;
    }

    public RNAssDrawable setAngle(float a) {
        this.m_graphicGroup.setRotation(a);
        notifyObserversMod(NotifyMessage.UPDATE);
        return this;
    }

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

    public RNAssDrawable setName(String n) {
        this.m_name = n;
        notifyObserversMod(NotifyMessage.UPDATE);
        return this;
    }

    public RNAssDrawable setX(int x) {
        return setPosition(x, getY());
    }

    public RNAssDrawable setY(int y) {
        return setPosition(getX(), y);
    }

    public RNAssDrawable setPosition(int x, int y) {
        this.m_graphicGroup.translate(getX() - x, getY() - y);
        notifyObserversMod(NotifyMessage.UPDATE);
        return this;
    }

    public RNAssDrawable translate(int xLag, int yLag) {
        this.m_graphicGroup.translate(xLag, yLag);
        notifyObserversMod(NotifyMessage.UPDATE);
        return this;
    }

    public RNAssDrawable rotate(float a) {
        this.m_graphicGroup.rotate(a);
        notifyObserversMod(NotifyMessage.UPDATE);
        return this;
    }

    public RNAssDrawable setColor(Color c) {
        this.m_color = c;
        this.m_graphicGroup.setFillColor(c);
        notifyObserversMod(NotifyMessage.UPDATE);
        return this;
    }

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

    public RNAssDrawable setRNAssGACU(String s) throws RNAException {
        this.m_rnass.setSequence(s);
        draw();
        notifyObserversMod(NotifyMessage.UPDATE);
        return this;
    }

    public RNAssDrawable setRNAssDotParentheses(String dp, String gacu) throws RNAException {
        this.m_rnass.setDotParentesis(dp);
        this.m_rnass.setSequence(gacu);
        draw();
        notifyObserversMod(NotifyMessage.UPDATE);
        return this;
    }

    public Boolean Contain(RNAss rna) {
        return this.m_rnass == rna;
    }

    public Boolean Contain(GraphicObject go) {
        return this.m_graphicGroup == go || this.m_graphicGroup.contains(go);
    }

    public Boolean ContainVirtualGroup(GraphicObject go) {
        return this.m_graphicGroup == go;
    }

    public void setRNAssDotParentheses(String dp) throws RNAException {
        setRNAssDotParentheses(dp, getSequence());
    }
}
