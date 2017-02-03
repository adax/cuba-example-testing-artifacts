package com.company.ceta.service.integration.container

import com.haulmont.cuba.testsupport.TestContainer

class IntegrationTestContainer extends TestContainer {

    static class Common extends IntegrationTestContainer {

        public static final Common INSTANCE = new Common();

        private static volatile boolean initialized;

        private Common() {
        }

        @Override
        void before() throws Throwable {
            if (!initialized) {
                super.before();
                initialized = true;
            }
            setupContext();
        }

        @Override
        void after() {
            cleanupContext();
            // never stops - do not call super
        }
    }

    IntegrationTestContainer() {
        super()
        appPropertiesFiles = Arrays.asList(
                // List the files defined in your web.xml
                // in appPropertiesConfig context parameter of the core module
                'cuba-app.properties',
                'app.properties',
                'ceta-test-app.properties'
        )

        dbDriver = 'org.hsqldb.jdbcDriver'
        dbUrl = 'jdbc:hsqldb:mem:cetaTestDb'
        dbUser = 'sa'
        dbPassword = ''
    }

    @Override
    void before() throws Throwable {
        super.before()
    }

    @Override
    void after() {
        super.after()
    }
}
