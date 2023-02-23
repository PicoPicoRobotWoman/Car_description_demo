package ru.car.directory.demo.corverter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.car.directory.demo.model.dto.response.CarResponseDto;
import ru.car.directory.demo.model.entity.Car;
import ru.car.directory.demo.model.dto.request.CarRequestDto;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(source = "registerNumber", target = "registerNumber")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "color", target = "color")
    @Mapping(source = "issue", target = "issue")
    Car toCar(CarRequestDto carRequestDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "registerNumber", target = "registerNumber")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "color", target = "color")
    @Mapping(source = "issue", target = "issue")
    CarResponseDto toResponseDto(Car car);

}
