package com.example.demo.utility;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class converter {

    private final ModelMapper modelMapper;

    public converter() {
        this.modelMapper = new ModelMapper();
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    public <S, D> D convertModelToDto(S source, Class<D> destinationType) {
        return getModelMapper().map(source, destinationType);
    }

    public <S, D> D convertDtoToModel(S source, Class<D> destinationType) {
        return getModelMapper().map(source, destinationType);
    }
}
