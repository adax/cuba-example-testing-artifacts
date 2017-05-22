/*
 * TODO Copyright
 */

package com.company.ceta.service.integration.base

import com.company.ceta.entity.Customer
import com.company.ceta.entity.CustomerType
import com.company.ceta.entity.Order
import com.company.ceta.service.integration.container.ContainerIntegrationSpec
import com.haulmont.cuba.core.entity.Entity
import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.LoadContext
import com.haulmont.cuba.core.global.PersistenceHelper
import com.haulmont.cuba.core.global.View
import com.haulmont.cuba.core.global.ViewRepository

/**
 * EntityViewIntegrationSpec is a superclass for integration specs that checks
 * if views of entities are defined correctly
 */
abstract class EntityViewIntegrationSpec extends ContainerIntegrationSpec {

    ViewRepository viewRepository

    def setup() {
        viewRepository = AppBeans.get(ViewRepository)
    }

    /**
     * returns true if a property is loaded for an entity, otherwise false
     * @param entity the entity to check
     * @param property the property to check for
     * @return true if is loaded, otherwise false
     */
    protected boolean isLoaded(Entity entity, String property) {
        PersistenceHelper.isLoaded(entity, property)
    }

    /**
     * returns true if a property is loaded for an entity, otherwise false
     * @param entity the entity to check
     * @param property the property to check for
     * @return true if is loaded, otherwise false
     */
    protected boolean isLoaded(Entity entity, String... properties) {
        properties.every { property ->
           isLoaded(entity, property)
        }
    }

    /**
     * reloads an entity with a particular view
     * @param entityClass the class of the entity to reload
     * @param viewName the name of the view
     * @param entityId the id of the entity to reload
     * @return the reloaded entity with the given view or throws a ViewNotFoundException if the view is not found
     */
    protected reloadWithView(Class entityClass, String viewName, UUID entityId) {
        View view = viewRepository.getView(entityClass, viewName)
        LoadContext<Customer> loadContext = LoadContext.create(entityClass)
                .setId(entityId)
                .setView(view)
        dataManager.load(loadContext)
    }

    /**
     * reloads an entity with a particular view
     * @param entity the entity to reload
     * @param viewName the name of the view
     * @return the reloaded entity with the given view or throws a ViewNotFoundException if the view is not found
     */
    protected reloadWithView(Entity entity, String viewName) {
        View view = viewRepository.getView(entity.metaClass, viewName)
        dataManager.reload(entity, view)
    }



}