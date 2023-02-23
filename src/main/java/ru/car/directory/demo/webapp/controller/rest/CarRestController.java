package ru.car.directory.demo.webapp.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.car.directory.demo.corverter.CarMapper;
import ru.car.directory.demo.model.dto.request.CarRequestDto;
import ru.car.directory.demo.model.dto.response.CarResponseDto;
import ru.car.directory.demo.model.exception.CarExistException;
import ru.car.directory.demo.model.exception.CarNonExistException;
import ru.car.directory.demo.service.ads.CarService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/car")
@Tag(name = "CarController", description = "контроллер для взаимодействия с базой данных автомобилий")
public class CarRestController {

    private final CarService carService;
    private final CarMapper carMapper;

    @Autowired
    public CarRestController(CarService carService, CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "список всех автомобилий в БД",
        description = "возвращает все автомобили в БД"
    )
    public List<CarResponseDto> getCars() {
        return carService.getCars().stream().map(carMapper::toResponseDto).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "добавить автомобиль в БД",
        description = "добавляет новый автомобиль в БД."
    )
    public void postCar(@Parameter(description = "описание автомобиля") @RequestBody(required = false) CarRequestDto carRequestDto) throws CarExistException {
        System.out.println(carRequestDto);
        System.out.println(carMapper.toCar(carRequestDto));
        carService.save(carMapper.toCar(carRequestDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "удаление автомобиля из БД",
        description = "удоляет автомобиль из БД"
    )
    public void deleteById(@Parameter(description = "ид автомобиля") @PathVariable(name = "id") Long id) throws CarNonExistException {
        carService.delete(id);
    }

    @GetMapping("/statistic")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "получение статистических данных о БД автомобилий",
        description = "возвращает все автомобили в БД"
    )
    public Map<String, String> getStatistic(@Parameter(description = "статистические данныйб возможные значения: first_entry, last_entry, count_entry") @RequestParam(value = "meta", defaultValue = "") List<String> metaDates) throws CarNonExistException {
        return carService.getMetaDate(metaDates);
    }

}
