package ru.car.directory.demo.model.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CarResponseDto {

    private Long id;
    private String registerNumber;
    private String brand;
    private String color;
    private Integer issue;

}
