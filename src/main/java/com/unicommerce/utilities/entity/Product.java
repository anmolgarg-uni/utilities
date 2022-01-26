/*
 *  Copyright 2013 Unicommerce Technologies (P) Limited . All Rights Reserved.
 *  UNICOMMERCE TECHONOLOGIES PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *  
 *  @version     1.0, 26-Dec-2013
 *  @author akshay
 */

package com.unicommerce.utilities.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "product", uniqueConstraints = { @UniqueConstraint(columnNames = "code"), @UniqueConstraint(columnNames = "name") })
public class Product implements java.io.Serializable {

    public enum Type {
        LITE,
        STANDARD,
        PROFESSIONAL,
        ENTERPRISE,
        WINGS
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String  code;
    private String  name;
    private Date    created;
    private Date    updated;
    private Set<ProductAccessResource> productAccessResources = new HashSet<>();
    private Set<ProductFeature>        productFeatures        = new HashSet<>();

    public Product() {
    }

    public Product(String code) {
        this.code = code;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "code", nullable = false, length = 45)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, length = 19)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", nullable = false, length = 19, insertable = false)
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    public Set<ProductAccessResource> getProductAccessResources() {
        return productAccessResources;
    }

    public void setProductAccessResources(Set<ProductAccessResource> productAccessResources) {
        this.productAccessResources = productAccessResources;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    public Set<ProductFeature> getProductFeatures() {
        return productFeatures;
    }

    public void setProductFeatures(Set<ProductFeature> productFeatures) {
        this.productFeatures = productFeatures;
    }


}
