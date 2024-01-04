package com.iffat.springbootreactivedatar2dbc.services;

import com.iffat.springbootreactivedatar2dbc.mappers.BeerMapper;
import com.iffat.springbootreactivedatar2dbc.model.BeerDTO;
import com.iffat.springbootreactivedatar2dbc.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public Mono<BeerDTO> getBeerById(Integer beerId) {
        return beerRepository.findById(beerId)
                .map(beerMapper::beerToBeerDto);
    }

    @Override
    public Flux<BeerDTO> listBeers() {
        return beerRepository.findAll().map(beerMapper::beerToBeerDto);
    }
}