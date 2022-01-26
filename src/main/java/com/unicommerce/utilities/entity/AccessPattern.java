package com.unicommerce.utilities.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * AccessPattern generated by hbm2java
 */
@Entity
@Table(name = "access_pattern", uniqueConstraints = @UniqueConstraint(columnNames = "url_pattern"))
public class AccessPattern implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1777206518938215745L;
    private Integer           id;
    private AccessResource    accessResource;
    private String            urlPattern;
    private Date              created;
    private Date              updated;

    public AccessPattern() {
    }

    public AccessPattern(AccessResource accessResource, String urlPattern, Date created, Date updated) {
        this.accessResource = accessResource;
        this.urlPattern = urlPattern;
        this.created = created;
        this.updated = updated;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "access_resource_id", nullable = false)
    public AccessResource getAccessResource() {
        return this.accessResource;
    }

    public void setAccessResource(AccessResource accessResource) {
        this.accessResource = accessResource;
    }

    @Column(name = "url_pattern", unique = true, nullable = false, length = 100)
    public String getUrlPattern() {
        return this.urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, length = 19)
    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", nullable = false, length = 19, insertable = false, updatable = false)
    public Date getUpdated() {
        return this.updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

}
