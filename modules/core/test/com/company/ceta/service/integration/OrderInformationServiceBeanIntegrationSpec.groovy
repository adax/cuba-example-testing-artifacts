package com.company.ceta.service.integration

import com.company.ceta.entity.Customer
import com.company.ceta.entity.Order
import com.company.ceta.service.OrderInformationService
import com.company.ceta.service.integration.container.ContainerIntegrationSpec
import com.haulmont.cuba.core.global.AppBeans
/**
 * Created by mario on 03.02.17.
 */
class OrderInformationServiceBeanSpec extends ContainerIntegrationSpec {

    OrderInformationService service

    def setup() {
        service = AppBeans.get(OrderInformationService)
    }

    def "test"() {
        given:
        Customer customer = metadata.create(Customer)
        def todaysOrder = metadata.create(Order)
        customer.setOrders([todaysOrder] as Set)

        when:
        Order latestOrder = service.findLatestOrder(customer)
        then:
        latestOrder == todaysOrder
    }

}