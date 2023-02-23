package ru.car.directory.demo.model.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CarRequestDto {

    private String registerNumber;
    private String brand;
    private String color;
    private Integer issue;

}
