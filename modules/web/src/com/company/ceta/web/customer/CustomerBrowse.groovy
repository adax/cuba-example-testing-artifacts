package com.company.ceta.web.customer

import com.company.ceta.entity.Customer
import com.company.ceta.entity.Order
import com.company.ceta.service.OrderInformationService
import com.haulmont.cuba.gui.WindowManager
import com.haulmont.cuba.gui.components.AbstractLookup
import com.haulmont.cuba.gui.components.Frame
import com.haulmont.cuba.gui.components.Table

import javax.inject.Inject

class CustomerBrowse extends AbstractLookup {

    @Inject
    Table<Customer> customersTable

    @Inject
    OrderInformationService orderInformationService

    void findLatestOrder() {
        Order latestOrder = orderInformationService.findLatestOrder(customersTable.singleSelected)

        if (latestOrder) {
            openEditor(latestOrder, WindowManager.OpenType.DIALOG)
        }
        else {
            showNotification(getMessage('noOrderFound'), Frame.NotificationType.ERROR)
        }
    }
}