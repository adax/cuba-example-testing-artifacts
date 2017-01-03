package com.company.ceta.entity

import spock.lang.Specification

class CustomerSpec extends Specification {
    
    Customer customer
    
    void setup() {

        customer = new Customer()
    }


    def "getCaption combines the first name and the last name"() {

        given:
        customer.firstName = "Jack"
        customer.name = "Jones"
        customer.type = CustomerType.LOYAL

        expect:
        customer.getCaption() == "Jones, Jack (LOYAL)"
    }

    def "getCaption only uses name if first name is not present"() {

        given:
        customer.firstName = null
        customer.name = "Jones"
        customer.type = CustomerType.LOYAL

        expect:
        customer.getCaption() == "Jones (LOYAL)"
    }
}
