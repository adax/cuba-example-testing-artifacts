package com.company.ceta.service

import com.company.ceta.entity.Customer
import com.company.ceta.entity.Order
import spock.lang.Specification

/**
 * Created by mario on 03.02.17.
 */
class OrderInformationServiceBeanSpec extends Specification {

    OrderInformationService service

    def setup() {
        service = new OrderInformationServiceBean()
    }

    def "findLatestOrder returns the order with the latest orderDate"() {

        given: "there are two orders"
        def today = new Date()
        def yesterday = today - 1
        Order yesterdaysOrder = new Order(orderDate: yesterday)
        Order todaysOrder = new Order(orderDate: today)

        and: "there is one customer that holds those orders"
        Customer customer = new Customer(orders: [todaysOrder, yesterdaysOrder])

        when: "we search for the latest Order"
        Order latestOrder = service.findLatestOrder(customer)

        then: "we get back the todays order as the latest one"
        latestOrder == todaysOrder
    }
}
