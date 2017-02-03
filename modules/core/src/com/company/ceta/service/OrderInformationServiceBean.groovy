package com.company.ceta.service

import com.company.ceta.entity.Customer
import com.company.ceta.entity.Order
import org.springframework.stereotype.Service

@Service(OrderInformationService.NAME)
public class OrderInformationServiceBean implements OrderInformationService {

    @Override
    Order findLatestOrder(Customer customer) {
        customer.orders?.max {it.orderDate}
    }
}