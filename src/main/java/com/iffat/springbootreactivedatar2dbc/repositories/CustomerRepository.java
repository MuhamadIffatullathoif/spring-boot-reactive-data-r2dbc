package com.iffat.springbootreactivedatar2dbc.repositories;

import com.iffat.springbootreactivedatar2dbc.domain.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}
