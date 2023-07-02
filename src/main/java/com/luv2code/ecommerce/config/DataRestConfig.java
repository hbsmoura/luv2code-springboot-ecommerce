package com.luv2code.ecommerce.config;

import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {

    private final EntityManager entityManager;

    @Autowired
    public DataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] unsupportedMethods = {HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PUT};

        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedMethods))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedMethods));
        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedMethods))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedMethods));

        exposeIds(config);

        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        Class[] domainTypes = entities.stream()
                .map(entity -> entity.getJavaType())
                .collect(Collectors.toList())
                .toArray(new Class[0]);

        config.exposeIdsFor(domainTypes);

        /*
        If we want to expose ids for a specific domain class, we can do that just by
        passing this class as parameter, like so:
        config.exposeIdsFor(Product.class);

        Hard-coded list could be submitted too, eg.:
        config.exposeIdsFor(Product.class, ProductCategory.class);
         */
    }
}
