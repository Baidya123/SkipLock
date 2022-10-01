package com.biswajit.testlock.service;

import com.biswajit.testlock.entity.CustomerRepository;
import com.biswajit.testlock.thread.Tasklet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppService {

    private final CustomerRepository customerRepository;

    public void init() throws InterruptedException {

        int pool = 5;

        Tasklet callableTask = new Tasklet(customerRepository);

        List<Callable<Integer>> callableTasks = new ArrayList<>();

        int totalRecords = 0;

        for (int i = 0; i < pool; i++) {
            callableTasks.add(callableTask);
        }

        ExecutorService executor = Executors.newFixedThreadPool(pool);

        List<Future<Integer>> result = executor.invokeAll(callableTasks);

        executor.shutdown();

        for (Future<Integer> item: result) {
            try {
                totalRecords += item.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        log.info("{} threads found {} records", pool, totalRecords);
    }
}
