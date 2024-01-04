package com.iffat.springbootreactivedatar2dbc.repositories;

import com.iffat.springbootreactivedatar2dbc.domain.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer> {
}
