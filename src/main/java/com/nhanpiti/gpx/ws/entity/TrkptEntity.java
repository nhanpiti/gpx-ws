package com.nhanpiti.gpx.ws.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Date;

/**
 * @author nhanpiti
 */

@Entity
@Table(name = "tbl_trkpt")
@XmlRootElement(name = "trkpt")
@XmlAccessorType(XmlAccessType.FIELD)
public class TrkptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trkpt_id")
    private long trkptId;

    @XmlAttribute
    private double lat;

    @XmlAttribute
    private double lon;

    private double ele;

    @Column(name = "trkpt_time", columnDefinition = "DATE")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssX")
    private Date time;

    @Column(name = "created_time", columnDefinition = "DATE DEFAULT NOW()")
    private long createdTime;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gpx", nullable = false)
    @JsonIgnore
    private GpxEntity gpx;

    public long getTrkptId() {
        return trkptId;
    }

    public void setTrkptId(long trkptId) {
        this.trkptId = trkptId;
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

    public double getEle() {
        return ele;
    }

    public void setEle(double ele) {
        this.ele = ele;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
