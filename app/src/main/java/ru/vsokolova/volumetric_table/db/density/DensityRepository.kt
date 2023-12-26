package ru.vsokolova.volumetric_table.db.density

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DensityRepository(private val densityDao: DensityDao) {

    suspend fun getDensity(wood: String, humidity: String): String {
        return withContext(Dispatchers.IO){
            return@withContext densityDao.getDensityValue(wood, humidity)
        }
    }
}