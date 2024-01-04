package com.iffat.springbootreactivedatar2dbc.services;

import com.iffat.springbootreactivedatar2dbc.mappers.CustomerMapper;
import com.iffat.springbootreactivedatar2dbc.model.CustomerDTO;
import com.iffat.springbootreactivedatar2dbc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Flux<CustomerDTO> listCustomers() {
        return customerRepository.findAll()
                .map(customerMapper::customerToCustomerDto);
    }

    @Override
    public Mono<CustomerDTO> getCustomerById(Integer id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDto);
    }

    @Override
    public Mono<CustomerDTO> saveNewCustomer(CustomerDTO customerDTO) {
        return customerRepository.save(customerMapper.customerDtoToCustomer(customerDTO))
                .map(customerMapper::customerToCustomerDto);
    }

    @Override
    public Mono<CustomerDTO> updateCustomer(Integer customerId, CustomerDTO customerDTO) {
        return customerRepository.findById(customerId)
                .map(customer -> {
                    customer.setCustomerName(customerDTO.getCustomerName());

                    return customer;
                }).flatMap(customerRepository::save)
                .map(customerMapper::customerToCustomerDto);
    }

    @Override
    public Mono<CustomerDTO> patchCustomer(Integer customerId, CustomerDTO customerDTO) {
        return customerRepository.findById(customerId)
                .map(customer -> {
                    if(StringUtils.hasText(customerDTO.getCustomerName())) {
                        customer.setCustomerName(customerDTO.getCustomerName());
                    }

                    return customer;
                }).flatMap(customerRepository::save)
                .map(customerMapper::customerToCustomerDto);
    }

    @Override
    public Mono<Void> deleteCustomerById(Integer customerId) {
        return customerRepository.deleteById(customerId);
    }
}
