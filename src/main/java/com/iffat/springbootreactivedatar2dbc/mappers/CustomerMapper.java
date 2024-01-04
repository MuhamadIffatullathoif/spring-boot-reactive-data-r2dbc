package com.iffat.springbootreactivedatar2dbc.mappers;

import com.iffat.springbootreactivedatar2dbc.domain.Customer;
import com.iffat.springbootreactivedatar2dbc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer customerDtoToCustomer(CustomerDTO customerDTO);

    CustomerDTO customerToCustomerDto(Customer customer);
}
