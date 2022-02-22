package de.hilling.cdi.sampleapp.testsupport;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import de.hilling.junit.cdi.annotations.GlobalTestImplementation;
import de.hilling.junit.cdi.jee.EntityManagerResourcesProvider;
import de.hilling.junit.cdi.scope.TestSuiteScoped;

/**
 * Producer for EntityManagers used in cdi-test unit tests.
 * <p>
 *     This should use the {@link EntityManagerResourcesProvider} to resolve {@link EntityManager}
 *     or {@link jakarta.persistence.EntityManagerFactory} (if needed) from the test persistence.xml.
 * </p>
 */
@TestSuiteScoped
public class EntityManagerTestProducer {
    @Inject
    private EntityManagerResourcesProvider entityManagerResourcesProvider;

    @Produces
    @GlobalTestImplementation
    @RequestScoped
    protected EntityManager provideTestEntityManager() {
        return entityManagerResourcesProvider.resolveEntityManager("test-persistence");
    }

}

