package com.company.ceta.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

import com.google.common.base.Joiner;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DeletePolicy;
import com.haulmont.cuba.core.global.Messages;

import java.util.Set;
import javax.persistence.OneToMany;

@NamePattern("#getCaption|firstName,name,type")
@Table(name = "CETA_CUSTOMER")
@Entity(name = "ceta$Customer")
public class Customer extends StandardEntity {
    private static final long serialVersionUID = 3748864199667741996L;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "FIRST_NAME")
    protected String firstName;

    @Column(name = "TYPE_", nullable = false)
    protected String type;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "customer")
    protected Set<Order> orders;

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Order> getOrders() {
        return orders;
    }


    public void setType(CustomerType type) {
        this.type = type == null ? null : type.getId();
    }

    public CustomerType getType() {
        return type == null ? null : CustomerType.fromId(type);
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getCaption() {
        return Joiner.on(", ").skipNulls().join(name, firstName) + " (" + type + ")";
    }
}