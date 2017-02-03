package com.company.ceta.service

import com.company.ceta.entity.Customer
import com.company.ceta.entity.Order
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.LoadContext
import spock.lang.Specification

/**
 * Created by mario on 03.02.17.
 */
class OrderInformationServiceBeanSpec extends Specification {

    OrderInformationService service
    LoadContext loadContext
    DataManager dataManager

    def setup() {
        loadContext = Mock(LoadContext)
        dataManager = Mock(DataManager)
        service = new OrderInformationServiceBeanWithMockableDependencies(
                dataManager: this.dataManager,
                orderLoadContext: loadContext
        )
    }

    def "findLatestOrder passes the correct SQL query to the loadContext"() {

        when:
        service.findLatestOrder(new Customer())

        then: 'the querString has been set in the query'
        1 * loadContext.setQuery({ LoadContext.Query query ->
            query.queryString == 'select e from ceta$Order e where e.customer.id = :customerId order by e.orderDate desc'
        })
    }

    def "findLatestOrder sets the parameter customerId in the query"() {

        given:
        def customerId = UUID.randomUUID()
        Customer customer = new Customer(id: customerId)

        when:
        service.findLatestOrder(customer)

        then: 'the parameter customerId with the id of the customer has been set in the query'
        1 * loadContext.setQuery({ LoadContext.Query query ->
            query.parameters['customerId'] == customerId
        })
    }

    def "findLatestOrder sets the maxResults to one, to just get the first order of the SQL query"() {

        when:
        service.findLatestOrder(new Customer())

        then: 'the parameter customerId with the id of the customer has been set in the query'
        1 * loadContext.setQuery({ LoadContext.Query query ->
            query.maxResults == 1
        })
    }

    def "findLatestOrder uses the loadContext to pass it into the dataManager"() {

        given: 'the load context has a fluent API, so we need to return the loadContext element'
        loadContext.setQuery(_) >> loadContext

        when:
        service.findLatestOrder(new Customer())

        then:
        1 * dataManager.load(loadContext)
    }
}

class OrderInformationServiceBeanWithMockableDependencies extends OrderInformationServiceBean {

    LoadContext orderLoadContext

    @Override
    protected LoadContext<Order> createOrderLoadContext() {
        orderLoadContext
    }
}
