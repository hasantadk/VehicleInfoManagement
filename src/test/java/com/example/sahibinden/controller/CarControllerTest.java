package com.example.sahibinden.controller;

import com.example.sahibinden.model.Car;
import com.example.sahibinden.model.dto.CarRequest;
import com.example.sahibinden.model.dto.CarResponse;
import com.example.sahibinden.service.CarService;
import com.example.sahibinden.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {
    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    @Test
    void getAllCars() {
        // Given
        List<CarResponse> mockCarResponses = new ArrayList<>();
        when(carService.getAllCars()).thenReturn(new ArrayList<>());

        // When
        ResponseEntity<List<CarResponse>> responseEntity = carController.getAllCars();

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockCarResponses, responseEntity.getBody());

        verify(carService, times(1)).getAllCars();
    }

    @Test
    void getCarById() {
        Long carId = 1L;
        Car mockCar = TestUtils.carBuilder();
        when(carService.getCarById(carId)).thenReturn(mockCar);

        ResponseEntity<CarResponse> responseEntity = carController.getCarById(carId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(carService, times(1)).getCarById(carId);
    }

    @Test
    void createCar() {
        CarRequest mockCarRequest = new CarRequest();
        Car mockAddedCar = TestUtils.carBuilder();
        when(carService.addCar(any(Car.class))).thenReturn(mockAddedCar);

        ResponseEntity<CarResponse> responseEntity = carController.createCar(mockCarRequest);


        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(carService, times(1)).addCar(any(Car.class));
    }

    @Test
    void updateCar() {
        Long carId = 1L;
        CarRequest mockCarRequest = new CarRequest();
        Car mockUpdatedCar = TestUtils.carBuilder();
        when(carService.updateCar(any(Car.class))).thenReturn(mockUpdatedCar);

        ResponseEntity<CarResponse> responseEntity = carController.updateCar(carId, mockCarRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(carService, times(1)).updateCar(any(Car.class));
    }

    @Test
    void deleteCar() {
        Long carId = 1L;
        when(carService.deleteCarById(carId)).thenReturn(true);

        ResponseEntity<Void> responseEntity = carController.deleteCar(carId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        verify(carService, times(1)).deleteCarById(carId);
    }
}
