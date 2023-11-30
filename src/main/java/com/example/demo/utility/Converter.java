package com.example.demo.utility;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    private final ModelMapper modelMapper;

    public Converter() {

        this.modelMapper = new ModelMapper();
        System.out.println("Constructer Call");
    }

    public ModelMapper getModelMapper() {
        System.out.println("Model Callled");
        return modelMapper;
    }

    public <S, D> D convertModelToDto(S source, Class<D> destinationType) {
        return getModelMapper().map(source, destinationType);
    }

    public <S, D> D convertDtoToModel(S source, Class<D> destinationType) {
        System.out.println("Check");
        return getModelMapper().map(source, destinationType);
    }

}
