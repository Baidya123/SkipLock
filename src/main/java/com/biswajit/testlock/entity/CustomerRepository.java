package com.biswajit.testlock.entity;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;


public interface CustomerRepository  extends JpaRepository<Customer, Integer> {

    @Transactional
    @QueryHints(@QueryHint(name = AvailableSettings.JPA_LOCK_TIMEOUT, value = "-2")) // to ignore skipLocked timeout set to negative
    @Lock(LockModeType.PESSIMISTIC_WRITE) // prevents read, update, delete
    List<Customer> getAllByIdLessThan(int limit);
}