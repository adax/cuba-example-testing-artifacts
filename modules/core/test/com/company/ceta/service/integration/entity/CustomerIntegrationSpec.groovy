package com.company.ceta.service.integration.entity

import com.company.ceta.entity.Customer
import com.company.ceta.entity.CustomerType
import com.company.ceta.entity.Order
import com.company.ceta.service.integration.base.EntityViewIntegrationSpec
import com.haulmont.cuba.core.global.View

class CustomerIntegrationSpec extends EntityViewIntegrationSpec {

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


    def "customer-view also includes the orders in a minimal form"() {

        given:
        def reloadedCustomer = reloadWithView(customer, 'customer-view')

        expect:
        isLoaded(reloadedCustomer, 'orders', 'type')

    }


    def "_local does not include the orders"() {

        given:
        def reloadedCustomer = reloadWithView(customer, View.LOCAL)

        expect:
        !isLoaded(reloadedCustomer, 'orders')
    }

}