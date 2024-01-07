package ru.vsokolova.volumetric_table.db

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DensityRepository @Inject constructor(private val densityDao: DensityDao) {

    suspend fun getDensity(wood: String, humidity: String): String {
        return withContext(Dispatchers.IO){
            return@withContext densityDao.getDensityValue(wood, humidity)
        }
    }
}