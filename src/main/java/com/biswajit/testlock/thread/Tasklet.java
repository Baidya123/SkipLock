package com.biswajit.testlock.thread;

import com.biswajit.testlock.entity.Customer;
import com.biswajit.testlock.entity.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Callable;

@RequiredArgsConstructor
@Slf4j
public class Tasklet implements Callable<Integer> {

    private final CustomerRepository customerRepository;

    @Override
    public Integer call() {
        List<Customer> customers = customerRepository.getAllByIdLessThan(500);

        log.info("Thread {}, Customers {}", Thread.currentThread().getName(), customers.size());
        return customers.size();
    }
}
