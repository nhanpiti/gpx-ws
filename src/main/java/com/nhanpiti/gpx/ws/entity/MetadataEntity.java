package com.nhanpiti.gpx.ws.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author nhanpiti
 */

@Entity
@Table(name = "tbl_metadata")
@XmlAccessorType(XmlAccessType.FIELD)
public class MetadataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "metadata_id")
    private long metadataId;

    private String name;

    private String desc;

    private String author;

    @XmlPath("link/@href")
    private String link;

    @Column(name = "metadata_text")
    @XmlPath("link/text/text()")
    private String text;

    @Column(name = "metadata_time", columnDefinition = "DATE")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssX")
    private Date time;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gpx", nullable = false)
    @JsonIgnore
    private GpxEntity gpx;

    public long getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(long metadataId) {
        this.metadataId = metadataId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
}
