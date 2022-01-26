package com.unicommerce.utilities.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import static javax.persistence.GenerationType.IDENTITY;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "tenant")
@DynamicUpdate
public class Tenant implements java.io.Serializable {

    public enum Mode {
        LIVE,
        TESTING
    }

    public enum StatusCode {
        ACTIVE,
        INACTIVE,
        SUSPENDED
    }

    /**
     *
     */
    private static final long serialVersionUID = -6314717971219052771L;
    private Integer           id;
    private String            code;
    private String            name;
    private String            accessUrl;
    private String            accountCode;
    private StatusCode        statusCode;
    private Mode              mode;
    private Tenant            baseTenant;
    private String            accessIpWhitelistString;
    private String            logoUrl;
    private Product           product;
    private Date created;
    private Date              updated;
    private Date              lastReactivationDate;
    private Date              goLiveDate;
    private String            setupState       = "INIT";
    private String            companyName;

    public Tenant() {
    }

    public Tenant(Integer id) {
        this.id = id;
    }

    public Tenant(String code, String name, String accessUrl, Date created, Date updated) {
        this.code = code;
        this.name = name;
        this.accessUrl = accessUrl;
        this.created = created;
        this.updated = updated;
    }

    public Tenant(String code, String name, String accessUrl, StatusCode statusCode, Date created, Date updated) {
        super();
        this.code = code;
        this.name = name;
        this.accessUrl = accessUrl;
        this.statusCode = statusCode;
        this.created = created;
        this.updated = updated;
    }

    public Tenant(String code, String name, String mode, String accessUrl, StatusCode statusCode, Date created, Tenant baseTenant) {
        super();
        this.code = code;
        this.name = name;
        this.mode = Mode.valueOf(mode);
        this.accessUrl = accessUrl;
        this.statusCode = statusCode;
        this.created = created;
        this.baseTenant = baseTenant;
    }

    public Tenant(String code, String name, String companyName, String mode, String accessUrl, String accounCode, StatusCode statusCode, Date created, Tenant baseTenant,
            Product product) {
        super();
        this.code = code;
        this.name = name;
        this.companyName = companyName;
        this.mode = Mode.valueOf(mode);
        this.accessUrl = accessUrl;
        this.accountCode = accounCode;
        this.statusCode = statusCode;
        this.created = created;
        this.updated = created;
        this.baseTenant = baseTenant;
        this.product = product;
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

    @Column(name = "code", unique = true, nullable = false, length = 45)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "access_ip_whitelist")
    public String getAccessIpWhitelistString() {
        return this.accessIpWhitelistString;
    }

    public void setAccessIpWhitelistString(String accessIpWhitelistString) {
        this.accessIpWhitelistString = accessIpWhitelistString;
    }

    @Column(name = "account_code")
    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    @Column(name = "status_code", nullable = false)
    @Enumerated(EnumType.STRING)
    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    @Column(name = "mode", nullable = false)
    @Enumerated(EnumType.STRING)
    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_tenant_id")
    public Tenant getBaseTenant() {
        return baseTenant;
    }

    public void setBaseTenant(Tenant baseTenant) {
        this.baseTenant = baseTenant;
    }

    @Column(name = "access_url", unique = true, nullable = false, length = 200)
    public String getAccessUrl() {
        return this.accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }

    @Column(name = "logo_url", length = 200)
    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code", nullable = false, referencedColumnName = "code" )
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
    @Column(name = "updated", nullable = false, length = 19, insertable = false)
    public Date getUpdated() {
        return this.updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_reactivation_date", length = 19)
    public Date getLastReactivationDate() {
        return lastReactivationDate;
    }

    public void setLastReactivationDate(Date lastReactivationDate) {
        this.lastReactivationDate = lastReactivationDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "go_live_date", length = 19)
    public Date getGoLiveDate() {
        return goLiveDate;
    }

    public void setGoLiveDate(Date goLiveDate) {
        this.goLiveDate = goLiveDate;
    }

    @Column(name = "setup_state", nullable = false)
    public String getSetupState() {
        return setupState;
    }

    public void setSetupState(String setupState) {
        this.setupState = setupState.toUpperCase();
    }

    @Column(name = "company_name", nullable = false, length = 100)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Tenant tenant = (Tenant) o;

        if (!code.equals(tenant.code))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Transient
    public boolean isLive() {
        return Mode.LIVE.equals(this.mode);
    }

    @Transient
    @Override public String toString() {
        return "Tenant{" + "code='" + code + '\'' + ", name='" + name + '\'' + ", accessUrl='" + accessUrl + '\''
                + ", updated=" + updated + ", companyName='" + companyName + '\'' + '}';
    }
}
