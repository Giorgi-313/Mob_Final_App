package com.example.mob_final_app.data.local

import androidx.room.*
import com.example.mob_final_app.data.model.Car
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {
    @Query("SELECT * FROM cars")
    fun getAllCars(): Flow<List<Car>>

    @Query("SELECT * FROM cars WHERE id = :id")
    suspend fun getCarById(id: Int): Car?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(car: Car)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cars: List<Car>)

    @Query("DELETE FROM cars")
    suspend fun deleteAll()

    @Query("SELECT * FROM cars WHERE horsepower >= :minHp")
    fun getCarsByMinHorsepower(minHp: Int): Flow<List<Car>>

    @Query("SELECT * FROM cars WHERE category = :category AND horsepower >= :minHp")
    fun getCarsByCategoryAndMinHorsepower(category: String, minHp: Int): Flow<List<Car>>

    @Delete
    suspend fun deleteCar(car: Car)
}
