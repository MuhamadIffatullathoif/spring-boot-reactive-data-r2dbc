package com.iffat.springbootreactivedatar2dbc.mappers;

import com.iffat.springbootreactivedatar2dbc.domain.Beer;
import com.iffat.springbootreactivedatar2dbc.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);
}
