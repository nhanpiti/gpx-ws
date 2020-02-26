package com.nhanpiti.gpx.ws.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author nhanpiti
 */

@Entity
@Table(name = "tbl_wpt")
@XmlAccessorType(XmlAccessType.FIELD)
public class WptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wpt_id")
    private long wptId;

    @XmlAttribute
    private double lat;

    @XmlAttribute
    private double lon;

    private String name;

    @Column(name = "created_time", columnDefinition = "DATE DEFAULT NOW()")
    private long createdTime;

    private String sym;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gpx", nullable = false)
    @JsonIgnore
    private GpxEntity gpx;

    public long getWptId() {
        return wptId;
    }

    public void setWptId(long wptId) {
        this.wptId = wptId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSym() {
        return sym;
    }

    public void setSym(String sym) {
        this.sym = sym;
    }

    public GpxEntity getGpx() {
        return gpx;
    }

    public void setGpx(GpxEntity gpx) {
        this.gpx = gpx;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
}
