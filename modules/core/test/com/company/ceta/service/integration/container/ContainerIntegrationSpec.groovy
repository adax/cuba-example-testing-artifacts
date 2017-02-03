package com.company.ceta.service.integration.container

import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.core.sys.DbUpdater
import spock.lang.Shared
import spock.lang.Specification

class ContainerIntegrationSpec extends Specification {

    @Shared
    IntegrationTestContainer container = IntegrationTestContainer.Common.INSTANCE

    @Shared
    DataManager dataManager

    @Shared
    Metadata metadata

    def setupSpec() {
        container.before()
        initDb()
        initBeans()
    }

    protected void initDb() {
        DbUpdater dbUpdater = AppBeans.get(DbUpdater)
        dbUpdater.updateDatabase()
    }

    protected void initBeans() {
        dataManager = AppBeans.get(DataManager)
        metadata = AppBeans.get(Metadata)
    }

    def cleanupSpec() {
        container.after()
    }

}