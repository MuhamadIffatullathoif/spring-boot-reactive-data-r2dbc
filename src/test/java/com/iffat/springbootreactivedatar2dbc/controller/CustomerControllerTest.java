package com.iffat.springbootreactivedatar2dbc.controller;

import com.iffat.springbootreactivedatar2dbc.domain.Customer;
import com.iffat.springbootreactivedatar2dbc.model.BeerDTO;
import com.iffat.springbootreactivedatar2dbc.model.CustomerDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureWebTestClient
class CustomerControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void testPatchNotfound() {

        webTestClient.patch()
                .uri(CustomerController.CUSTOMER_PATH_ID, 888)
                .body(Mono.just(getCustomer()), CustomerDTO.class)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchBadData() {
        CustomerDTO customerDTO = getCustomer();
        customerDTO.setCustomerName("");

        webTestClient.patch()
                .uri(CustomerController.CUSTOMER_PATH_ID, 1)
                .body(Mono.just(customerDTO), CustomerDTO.class)
                .exchange()
                .expectStatus().isBadRequest();

    }

    @Test
    void testDeleteNotFound() {

        webTestClient.delete()
                .uri(CustomerController.CUSTOMER_PATH_ID, 999)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Order(999)
    void testDeleteCustomer() {

        webTestClient.delete()
                .uri(CustomerController.CUSTOMER_PATH_ID, 1)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testUpdateCustomerNotFound() {

        webTestClient.put()
                .uri(CustomerController.CUSTOMER_PATH_ID, 999)
                .body(Mono.just(getCustomer()), CustomerDTO.class)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateCustomerBadData() {
        CustomerDTO customerDTO = getCustomer();
        customerDTO.setCustomerName("");

        webTestClient.put()
                .uri(CustomerController.CUSTOMER_PATH_ID, 1)
                .body(Mono.just(customerDTO), CustomerDTO.class)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @Order(3)
    void testUpdateCustomer() {

        webTestClient.put()
                .uri(CustomerController.CUSTOMER_PATH_ID,1)
                .body(Mono.just(getCustomer()), CustomerDTO.class)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testCreateCustomerBadData() {
        CustomerDTO customerDTO = getCustomer();
        customerDTO.setCustomerName("");

        webTestClient.post()
                .uri(CustomerController.CUSTOMER_PATH)
                .body(Mono.just(customerDTO), CustomerDTO.class)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateCustomer() {

        webTestClient.post()
                .uri(CustomerController.CUSTOMER_PATH)
                .body(Mono.just(getCustomer()), CustomerDTO.class)
                .header("Content-Type","application/json")
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().location("http://localhost:8080/api/v2/customers/4");
    }

    @Test
    void testGetCustomerByIdNotFound() {

        webTestClient.get()
                .uri(CustomerController.CUSTOMER_PATH_ID, 999)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Order(2)
    void testGetCustomerById() {

        webTestClient.get()
                .uri(CustomerController.CUSTOMER_PATH_ID, 1)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-type","application/json")
                .expectBody(BeerDTO.class);
    }

    @Test
    @Order(1)
    void testListCustomers() {

        webTestClient.get()
                .uri(CustomerController.CUSTOMER_PATH)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-type","application/json")
                .expectBody().jsonPath("$.size()").isEqualTo(3);
    }

    public static CustomerDTO getCustomer() {
        return CustomerDTO.builder()
                .customerName("Customer Test")
                .build();
    }
}