package com.personal.shoppingcartrest.systemconfiguration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
    Configuration findById(long id);
}
