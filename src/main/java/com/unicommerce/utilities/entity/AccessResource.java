package com.unicommerce.utilities.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * AccessResource generated by hbm2java
 */
@Entity
@Table(name = "access_resource", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class AccessResource implements java.io.Serializable {

    public enum Name {
        PICKLIST_RECEIVE,
        PUTAWAY_TRANSFER,
        IMPLICIT_ACCESS,
        OUTBOUND_GATEPASS,
        MERGE_SHIPMENTS,
        GOOD_INVENTORY_BARCODE_VIEW,
        MANIFEST,
        SHIPPING_PROVIDER_ALLOCATE,
        PICKLIST_EDIT,
        LOOKUP_ITEM_TYPE,
        MARK_QUANTITY_FOUND,
        MARK_QUANTITY_NOT_FOUND,
        SHIPPER,
        IMPORT_SALE_ORDERS,
        SALE_ORDER_CANCEL_ITEM,
        SALE_ORDER_HOLD_UNHOLD,
        LOOKUP_SALE_ORDER,
        ADMIN_CATALOG,
        DUAL_COMPANY_API,
        SALE_ORDER_ADDRESS_EDIT,
        LOOKUP_INVENTORY,
        IMPORT_INVENTORY_ADJUSTMENT,
        PROCUREMENT,
        VENDOR_MANAGEMENT,
        SALE_ORDER_ALTERNATE_ACCEPT,
        SHIPPING,
        REVERSE_PICKUP_CREATE,
        SALE_ORDER_METADATA_EDIT,
        CREATE_TENANT,
        VENDOR_API,
        SUBSCRIBE_EXPORT,
        VENDOR_CATALOG,
        VENDOR_CREATE,
        UPDATE_TRACKING_STATUS,
        INFLOW_GRN_SEARCH,
        INFLOW_GRN_CREATE_LABELS,
        MINIMAL,
        EXPORT_LOCATIONS,
        MATERIAL_MANAGEMENT,
        VERIFY_PENDING_ORDERS,
        CUSTOMER_INVOICE,
        ASN_CREATE,
        ADMIN_TEMPLATE,
        LOOKUP_SHIPPING_PACKAGE,
        SALE_ORDER_RETURN,
        INFLOW_GRN_CREATE,
        SHIPMENT_BATCH,
        VENDOR_INVOICE,
        VENDOR,
        ADMIN_USER,
        PUTAWAY_CREATE,
        PUTAWAY_COMPLETE,
        IMPORT_SHIPPING_PROVIDER_LOCATION,
        FORCE_DISPATCH_SHIPPING_PACKAGE,
        ITEM_DETAILING,
        STORE_PICKUP,
        CHANNELS_VIEW,
        CHANNELS_ADMIN,
        PRICE_UPDATE,
        PRICE_VIEW,
        INFLOW_INVENTORY_ADJUST,
        RECOMMENDATION,
        ACCOUNT_VIEW,
        PICKER,
        PICKLIST_VIEW,
        EXPORT,
        CYCLE_COUNT_VIEW,
        COUNT_SHELF,
        LOOKUP_INVENTORY_LEDGER,
        SALE_ORDER_RELEASE_INVENTORY,
        BLOCKED_ORDER_CREATE,
        CHANNEL_BLOCKED_INVENTORY_EDIT,
        CART_VIEW,
        CART_EDIT,
        CART_PRICE_UPDATE,
        POS_CUSTOMER_VIEW,
        POS_CUSTOMER_CREATE,
        POS_PAYMENTS_VIEW,
        REVERSE_PICKUP_EDIT,
        REVERSE_PICKUP_APPROVE,
        REVERSE_PICKUP_CHECK_SERVICEABILITY,
        SALE_ORDER_CONFIRM_FACILITY,
        IGNORE_PENDENCY_FOR_INVENTORY_DEDUCTION_FLOWS,
        MACHINE_CREATE,
        MACHINE_VIEW,
        DIMENSIONS_CAPTURE,
        SALE_ORDER_SWITCH_FACILITY,
        ADD_EDIT_SOI_SELLING_PRICE,
        ADD_EDIT_SOI_DISCOUNT,
        SALE_ORDER_STATUS_UPDATE,
        ALL_ACTIVE_TENANT_JOBS_ACCESS,
        BILLING_PARTY_FINANCE_DETAILS_EDIT,
        LOOKUP_BULK_RETURNS,
        VENDOR_APPROVE,
        EDIT_FACILITY_CODE,
        SHOW_CUSTOMER_DETAILS,
        PICKLIST_CREATE
    }

    public enum Level {
        TENANT,
        FACILITY,
        BOTH
    }

    /**
     *
     */
    private static final long serialVersionUID = -6738948660014594718L;
    private Integer             id;
    private AccessResourceGroup accessResourceGroup;
    private String              name;
    private String              level;
    private Date                created;
    private Date                updated;
    private Set<AccessPattern> accessPatterns = new HashSet<AccessPattern>(0);

    public AccessResource() {
    }

    public AccessResource(String name, Date created, Date updated) {
        this.name = name;
        this.created = created;
        this.updated = updated;
    }

    /**
     * @param accessResourceId
     */
    public AccessResource(Integer accessResourceId) {
        this.id = accessResourceId;
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
    @JoinColumn(name = "access_resource_group_id")
    public AccessResourceGroup getAccessResourceGroup() {
        return this.accessResourceGroup;
    }

    public void setAccessResourceGroup(AccessResourceGroup accessResourceGroup) {
        this.accessResourceGroup = accessResourceGroup;
    }

    @Column(name = "name", unique = true, nullable = false, length = 45)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "level", nullable = false, length = 8)
    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accessResource")
    public Set<AccessPattern> getAccessPatterns() {
        return this.accessPatterns;
    }

    public void setAccessPatterns(Set<AccessPattern> accessPatterns) {
        this.accessPatterns = accessPatterns;
    }

}
