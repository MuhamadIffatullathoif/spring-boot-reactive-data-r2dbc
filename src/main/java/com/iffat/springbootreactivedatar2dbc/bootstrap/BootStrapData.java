package com.iffat.springbootreactivedatar2dbc.bootstrap;

import com.iffat.springbootreactivedatar2dbc.domain.Beer;
import com.iffat.springbootreactivedatar2dbc.domain.Customer;
import com.iffat.springbootreactivedatar2dbc.repositories.BeerRepository;
import com.iffat.springbootreactivedatar2dbc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class BootStrapData implements CommandLineRunner {

    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBeerData();
        loadCustomerData();
        beerRepository.count().subscribe(count -> {
            System.out.println("Count is: " + count);
        });

        customerRepository.count().subscribe(count -> {
            System.out.println("Count is: " + count);
        });
    }

    private void loadCustomerData() {
        customerRepository.count().subscribe(count -> {
            if (count == 0) {
                Customer customer = Customer.builder()
                        .customerName("Customer 1")
                        .build();

                Customer customer1 = Customer.builder()
                        .customerName("Customer 2")
                        .build();

                Customer customer2 = Customer.builder()
                        .customerName("Customer 3")
                        .build();

                customerRepository.saveAll(List.of(customer, customer1, customer2)).subscribe();
            }
        });
    }

    private void loadBeerData() {
        beerRepository.count().subscribe(count -> {
            if (count == 0) {
                Beer beer1 = Beer.builder()
                        .beerName("Galaxy Cat")
                        .beerStyle("Pale Ale")
                        .upc("12356")
                        .price(new BigDecimal("12.99"))
                        .quantityOnHand(122)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                Beer beer2 = Beer.builder()
                        .beerName("Crank")
                        .beerStyle("Pale Ale")
                        .upc("12356222")
                        .price(new BigDecimal("11.99"))
                        .quantityOnHand(392)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                Beer beer3 = Beer.builder()
                        .beerName("Sunshine City")
                        .beerStyle("Pale Ale")
                        .upc("12356")
                        .price(new BigDecimal("13.99"))
                        .quantityOnHand(144)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();
                beerRepository.saveAll(List.of(beer1, beer2, beer3)).subscribe();
            }
        });
    }

}
