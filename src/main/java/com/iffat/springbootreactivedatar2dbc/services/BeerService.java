package com.iffat.springbootreactivedatar2dbc.services;

import com.iffat.springbootreactivedatar2dbc.model.BeerDTO;
import reactor.core.publisher.Flux;

public interface BeerService {
    Flux<BeerDTO> listBeers();
}
