package com.iffat.springbootreactivedatar2dbc.services;

import com.iffat.springbootreactivedatar2dbc.model.BeerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerService {
    Flux<BeerDTO> listBeers();

    Mono<BeerDTO> getBeerById(Integer beerId);
}
