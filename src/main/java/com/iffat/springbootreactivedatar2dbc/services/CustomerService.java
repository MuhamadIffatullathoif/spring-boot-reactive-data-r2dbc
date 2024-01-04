package com.iffat.springbootreactivedatar2dbc.services;

import com.iffat.springbootreactivedatar2dbc.model.CustomerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Flux<CustomerDTO> listCustomers();
    Mono<CustomerDTO> getCustomerById(Integer id);
    Mono<CustomerDTO> saveNewCustomer(CustomerDTO customerDTO);
    Mono<CustomerDTO> updateCustomer(Integer customerId, CustomerDTO customerDTO);
    Mono<CustomerDTO> patchCustomer(Integer customerId, CustomerDTO customerDTO);
    Mono<Void> deleteCustomerById(Integer customerId);
}
