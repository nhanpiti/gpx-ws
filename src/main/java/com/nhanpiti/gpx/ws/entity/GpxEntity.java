package com.nhanpiti.gpx.ws.entity;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author nhanpiti
 */

@Entity
@Table(name = "tbl_gpx")
@XmlRootElement(name = "gpx")
@XmlAccessorType(XmlAccessType.FIELD)
public class GpxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gpx_id")
    private long gpxId;

    @Column(name = "created_time")
    private long createdTime;

    @Column(name = "updated_time")
    private long updatedTime;

    @Column(name = "user_id")
    private int userId;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "gpx")
    @XmlElement(name = "metadata")
    private MetadataEntity gpxMetadata;

    @OneToMany(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "gpx")

    @XmlPath("trk/trkseg/trkpt")
    private List<TrkptEntity> trkpts;

    @OneToMany(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "gpx")
    @XmlElement(name = "wpt")
    private List<WptEntity> wpts;

    public long getGpxId() {
        return gpxId;
    }

    public void setGpxId(long gpxId) {
        this.gpxId = gpxId;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public MetadataEntity getGpxMetadata() {
        return gpxMetadata;
    }

    public void setGpxMetadata(MetadataEntity gpxMetadata) {
        this.gpxMetadata = gpxMetadata;
    }

    public List<TrkptEntity> getTrkpts() {
        return trkpts;
    }

    public void setTrkpts(List<TrkptEntity> trkpts) {
        this.trkpts = trkpts;
    }

    public List<WptEntity> getWpts() {
        return wpts;
    }

    public void setWpts(List<WptEntity> wpts) {
        this.wpts = wpts;
    }
}
