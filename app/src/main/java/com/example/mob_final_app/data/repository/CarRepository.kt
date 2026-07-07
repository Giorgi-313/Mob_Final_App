package com.example.mob_final_app.data.repository

import com.example.mob_final_app.data.local.CarDao
import com.example.mob_final_app.data.model.Car
import kotlinx.coroutines.flow.Flow

class CarRepository(private val carDao: CarDao) {
    val allCars: Flow<List<Car>> = carDao.getAllCars()

    suspend fun getCarById(id: Int): Car? {
        return carDao.getCarById(id)
    }

    fun getCarsByMinHorsepower(minHp: Int): Flow<List<Car>> {
        return carDao.getCarsByMinHorsepower(minHp)
    }

    fun getCarsByCategoryAndMinHorsepower(category: String, minHp: Int): Flow<List<Car>> {
        return carDao.getCarsByCategoryAndMinHorsepower(category, minHp)
    }

    suspend fun insert(car: Car) {
        carDao.insertCar(car)
    }

    suspend fun insertAll(cars: List<Car>) {
        carDao.insertAll(cars)
    }

    suspend fun deleteAll() {
        carDao.deleteAll()
    }
}
