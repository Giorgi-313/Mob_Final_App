package com.example.mob_final_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mob_final_app.data.model.Car
import com.example.mob_final_app.data.repository.CarRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CarViewModel(private val repository: CarRepository) : ViewModel() {

    val allCars: StateFlow<List<Car>> = repository.allCars.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun insert(car: Car) = viewModelScope.launch {
        repository.insert(car)
    }

    fun insertAll(cars: List<Car>) = viewModelScope.launch {
        repository.insertAll(cars)
    }

    suspend fun getCarById(id: Int): Car? {
        return repository.getCarById(id)
    }
    
    // Initial data setup
    fun prepopulateData() {
        viewModelScope.launch {
            val sampleCars = listOf(
                // Gas Cars
                Car(brand = "Toyota", model = "Supra", year = 2024, engine = "3.0L Turbo I6", horsepower = 382, transmission = "8-speed Automatic", description = "The legend returns with even more power and precision.", category = "Gas", fuelEfficiency = "23/31 MPG", imageUrl = "supra"),
                Car(brand = "BMW", model = "M4", year = 2024, engine = "3.0L Twin-Turbo I6", horsepower = 503, transmission = "8-speed Automatic", description = "Performance and luxury combined in a sleek coupe.", category = "Gas", fuelEfficiency = "16/23 MPG", imageUrl = "bmwm4"),
                Car(brand = "Porsche", model = "911 GT3", year = 2024, engine = "4.0L Flat-6", horsepower = 502, transmission = "7-speed PDK", description = "A race car for the road, offering an unmatched driving experience.", category = "Gas", fuelEfficiency = "15/18 MPG", imageUrl = "porsche911gt3"),
                Car(brand = "Nissan", model = "GT-R", year = 2024, engine = "3.8L Twin-Turbo V6", horsepower = 565, transmission = "6-speed Dual-Clutch", description = "Godzilla remains a formidable force on the track.", category = "Gas", fuelEfficiency = "16/22 MPG", imageUrl = "nissangtr"),
                Car(brand = "Ford", model = "Mustang GT", year = 2024, engine = "5.0L V8", horsepower = 480, transmission = "6-speed Manual", description = "The classic American pony car with a roaring V8.", category = "Gas", fuelEfficiency = "15/24 MPG", imageUrl = "mustanggt"),
                Car(brand = "Chevrolet", model = "Corvette Z06", year = 2024, engine = "5.5L V8", horsepower = 670, transmission = "8-speed Dual-Clutch", description = "A mid-engine masterpiece with a flat-plane crank V8.", category = "Gas", fuelEfficiency = "12/19 MPG", imageUrl = "corvettez06"),
                Car(brand = "Lamborghini", model = "Huracán Sterrato", year = 2024, engine = "5.2L V10", horsepower = 602, transmission = "7-speed Dual-Clutch", description = "An all-terrain supercar designed for maximum driving fun.", category = "Gas", fuelEfficiency = "13/18 MPG", imageUrl = "huracansterrato"),
                Car(brand = "Dodge", model = "Challenger SRT Hellcat", year = 2023, engine = "6.2L Supercharged V8", horsepower = 717, transmission = "8-speed Automatic", description = "Pure American muscle with overwhelming power.", category = "Gas", fuelEfficiency = "13/22 MPG", imageUrl = "challengersrt"),
                
                // Hybrid Cars
                Car(brand = "Toyota", model = "Prius", year = 2024, engine = "2.0L Hybrid", horsepower = 194, transmission = "eCVT", description = "The efficiency king gets a stylish new look.", category = "Hybrid", fuelEfficiency = "57/56 MPG"),
                Car(brand = "Honda", model = "Accord Hybrid", year = 2024, engine = "2.0L Hybrid", horsepower = 204, transmission = "eCVT", description = "A sophisticated sedan with impressive fuel economy.", category = "Hybrid", fuelEfficiency = "51/44 MPG"),
                Car(brand = "BMW", model = "XM", year = 2024, engine = "4.4L V8 Plug-in Hybrid", horsepower = 644, transmission = "8-speed Automatic", description = "A high-performance luxury SUV with hybrid power.", category = "Hybrid", fuelEfficiency = "46 MPGe"),
                Car(brand = "McLaren", model = "Artura", year = 2024, engine = "3.0L V6 Hybrid", horsepower = 671, transmission = "8-speed Dual-Clutch", description = "The next generation of supercar performance.", category = "Hybrid", fuelEfficiency = "39 MPGe"),
                Car(brand = "Ferrari", model = "296 GTB", year = 2024, engine = "2.9L V6 Hybrid", horsepower = 818, transmission = "8-speed Dual-Clutch", description = "Defining the concept of fun to drive with hybrid technology.", category = "Hybrid", fuelEfficiency = "47 MPGe"),
                Car(brand = "Lexus", model = "LC 500h", year = 2024, engine = "3.5L V6 Hybrid", horsepower = 354, transmission = "Multi-stage Hybrid", description = "Exquisite design meets advanced hybrid efficiency.", category = "Hybrid", fuelEfficiency = "26/33 MPG"),
                Car(brand = "Porsche", model = "Cayenne E-Hybrid", year = 2024, engine = "3.0L V6 Hybrid", horsepower = 463, transmission = "8-speed Automatic", description = "Versatile luxury SUV with plug-in hybrid performance.", category = "Hybrid", fuelEfficiency = "47 MPGe"),

                // Electric Cars
                Car(brand = "Tesla", model = "Model S Plaid", year = 2024, engine = "Tri-Motor Electric", horsepower = 1020, transmission = "Single-speed", description = "Blistering acceleration and cutting-edge tech.", category = "Electric", fuelEfficiency = "102 MPGe"),
                Car(brand = "Porsche", model = "Taycan Turbo S", year = 2024, engine = "Dual Electric Motors", horsepower = 750, transmission = "2-speed Automatic", description = "The electric sports car that sets new standards.", category = "Electric", fuelEfficiency = "70 MPGe"),
                Car(brand = "Hyundai", model = "IONIQ 6", year = 2024, engine = "Electric Motor", horsepower = 320, transmission = "Single-speed", description = "Ultra-streamlined design with exceptional range.", category = "Electric", fuelEfficiency = "140 MPGe"),
                Car(brand = "Rivian", model = "R1T", year = 2024, engine = "Quad-Motor Electric", horsepower = 835, transmission = "Single-speed", description = "The electric truck ready for any adventure.", category = "Electric", fuelEfficiency = "70 MPGe"),
                Car(brand = "Audi", model = "e-tron GT", year = 2024, engine = "Dual Electric Motors", horsepower = 637, transmission = "2-speed Automatic", description = "Electric performance meets Audi luxury.", category = "Electric", fuelEfficiency = "85 MPGe"),
                Car(brand = "Lucid", model = "Air Sapphire", year = 2024, engine = "Tri-Motor Electric", horsepower = 1234, transmission = "Single-speed", description = "The pinnacle of electric luxury and performance.", category = "Electric", fuelEfficiency = "105 MPGe"),
                Car(brand = "Rimac", model = "Nevera", year = 2024, engine = "Quad Electric Motors", horsepower = 1914, transmission = "Single-speed", description = "The world's fastest accelerating production car.", category = "Electric", fuelEfficiency = "53 MPGe"),
                Car(brand = "Ford", model = "F-150 Lightning", year = 2024, engine = "Dual Electric Motors", horsepower = 580, transmission = "Single-speed", description = "America's favorite truck, now fully electric.", category = "Electric", fuelEfficiency = "68 MPGe")
            )
            repository.insertAll(sampleCars)
        }
    }
}

class CarViewModelFactory(private val repository: CarRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CarViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
