package com.company.ceta.service

import com.company.ceta.entity.Customer
import com.company.ceta.entity.Order


public interface OrderInformationService {
    String NAME = "ceta_OrderInformationService";

    Order findLatestOrder(Customer customer);
}