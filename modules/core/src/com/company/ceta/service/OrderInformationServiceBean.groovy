package com.company.ceta.service

import com.company.ceta.entity.Customer
import com.company.ceta.entity.Order
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.LoadContext
import org.springframework.stereotype.Service

import javax.inject.Inject

@Service(OrderInformationService.NAME)
public class OrderInformationServiceBean implements OrderInformationService {

    @Inject
    DataManager dataManager

    @Override
    Order findLatestOrder(Customer customer) {

        LoadContext.Query query = createCustomerQuery(customer)

        println query
        LoadContext loadContext = createOrderLoadContext().setQuery(query)

        dataManager.load(loadContext)
    }

    protected LoadContext.Query createCustomerQuery(Customer customer) {
        def sqlQueryString = 'select e from ceta$Order e where e.customer.id = :customerId order by e.orderDate desc'
        LoadContext.createQuery(sqlQueryString)
                .setParameter('customerId', customer.id)
                .setMaxResults(1)
    }

    protected LoadContext<Order> createOrderLoadContext() {
        LoadContext.create(Order)
    }
}