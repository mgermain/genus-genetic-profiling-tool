package edu.udes.bio.genus.client.rna;

import com.objetdirect.tatami.client.gfx.GraphicObject;
import com.objetdirect.tatami.client.gfx.VirtualGroup;

public class CostumVirtualGroup extends VirtualGroup {

    public float m_angle;
    public float m_scale;

    public CostumVirtualGroup() {
        this.m_angle = 0;
        this.m_scale = 1;
    }

    @Override
    public GraphicObject rotate(float angle) {
        this.m_angle += angle;
        return super.rotate(angle);
    }

    public GraphicObject setRotation(float angle) {
        return this.rotate(this.m_angle * -1 + angle);
    }

    @Override
    public GraphicObject scale(float factor) {
        this.m_scale *= factor;
        return super.scale(factor);
    }

    public GraphicObject setScaleFactor(float factor) {
        return this.scale(factor / this.m_scale);
    }

    public GraphicObject updateContent() {
        setRotation(this.m_angle);
        return setScaleFactor(this.m_scale);
    }

}
