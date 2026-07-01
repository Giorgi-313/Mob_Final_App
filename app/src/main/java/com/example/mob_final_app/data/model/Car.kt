package com.example.mob_final_app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class Car(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val brand: String,
    val model: String,
    val year: Int,
    val engine: String,
    val horsepower: Int,
    val transmission: String,
    val description: String,
    val category: String, // Gas, Hybrid, Electric
    val fuelEfficiency: String, // e.g., "25 MPG" or "100 MPGe"
    val imageUrl: String = ""
)
