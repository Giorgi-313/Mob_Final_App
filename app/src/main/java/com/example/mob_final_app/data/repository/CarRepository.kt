package com.example.mob_final_app.data.repository

import com.example.mob_final_app.data.local.CarDao
import com.example.mob_final_app.data.model.Car
import kotlinx.coroutines.flow.Flow

class CarRepository(private val carDao: CarDao) {
    val allCars: Flow<List<Car>> = carDao.getAllCars()

    suspend fun getCarById(id: Int): Car? {
        return carDao.getCarById(id)
    }

    suspend fun insert(car: Car) {
        carDao.insertCar(car)
    }

    suspend fun insertAll(cars: List<Car>) {
        carDao.insertAll(cars)
    }
}
