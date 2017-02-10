package com.company.ceta.service.integration

import com.company.ceta.entity.Customer
import com.company.ceta.entity.CustomerType
import com.company.ceta.entity.Order
import com.company.ceta.service.OrderInformationService
import com.company.ceta.service.integration.container.ContainerIntegrationSpec
import com.haulmont.cuba.core.EntityManager
import com.haulmont.cuba.core.Transaction
import com.haulmont.cuba.core.entity.Entity
import com.haulmont.cuba.core.global.AppBeans


/**
 * Created by mario on 03.02.17.
 */
class OrderInformationServiceBeanSpec extends ContainerIntegrationSpec {

    OrderInformationService service

    Customer customer

    def setup() {
        service = AppBeans.get(OrderInformationService)
        customer = new Customer(type: CustomerType.NEW, name: "testCustomer")
    }

    def "findLatestOrder returns the order with the latest orderDate"() {

        given: "there are two orders"
        def todaysOrder = new Order(orderDate: new Date(), customer: customer)
        def yesterdaysOrder = new Order(orderDate: new Date() - 1, customer: customer)

        and: "the orders belong to that customer"
        customer.orders = [todaysOrder, yesterdaysOrder] as Set

        and: "the entities are persisted in the database"
        persist(customer, todaysOrder, yesterdaysOrder)


        when: "the latest order of the customer is requested"
        Order latestOrder = service.findLatestOrder(customer)

        then: "the latest order is todays order"
        latestOrder == todaysOrder
    }


    private void persist(Entity... entitiys) {

        Transaction tx = container.persistence().createTransaction()

        EntityManager entityManager = container.entityManager()
        entitiys.each {
            entityManager.persist(it);
        }
        tx.commit()

    }
}