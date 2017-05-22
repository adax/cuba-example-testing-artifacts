package com.company.ceta.service.integration.entity

import com.company.ceta.entity.Customer
import com.company.ceta.entity.CustomerType
import com.company.ceta.entity.Order
import com.company.ceta.service.integration.base.EntityViewIntegrationSpec
import com.haulmont.cuba.core.global.View
import spock.lang.Unroll

class CustomerEntityViewIntegrationSpec extends EntityViewIntegrationSpec {

    Customer customer


    def setup() {

        given:
        customer = metadata.create(Customer)

        and:
        Order todaysOrder = metadata.create(Order)
        todaysOrder.customer = customer
        todaysOrder.orderDate = new Date()

        and:
        Order tomorrowsOrder = metadata.create(Order)
        tomorrowsOrder.customer = customer
        tomorrowsOrder.orderDate = new Date() + 1

        and:
        customer.type = CustomerType.NEW
        customer.orders = [todaysOrder, tomorrowsOrder] as Set

        and:
        persist(customer, todaysOrder, tomorrowsOrder)

    }


    @Unroll
    def "[View Checker]: #viewName contains attribute #attribute: #expectedIsLoaded"() {

        given:
        def reloadedCustomer = reloadWithView(customer, viewName)

        when:
        def actualIsLoaded = isLoaded(reloadedCustomer, attribute)

        then:
        expectedIsLoaded == actualIsLoaded

        where:
        viewName | attribute || expectedIsLoaded
        View.LOCAL | 'orders' || false
        'customer-view' | 'orders' || true

    }

}