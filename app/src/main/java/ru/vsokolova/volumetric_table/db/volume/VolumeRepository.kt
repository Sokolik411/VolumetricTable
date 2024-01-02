package ru.vsokolova.volumetric_table.db.volume

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VolumeRepository @Inject constructor(private val volumeDao: VolumeDao) {

    suspend fun getAllLength(startWith: String, top: Short): List<String> {
        return withContext(Dispatchers.IO){
            return@withContext volumeDao.getLengthsList(startWith, top)
        }
    }

    suspend fun getAllThick(startWith: String, length: String, top: Short): List<String> {
        return withContext(Dispatchers.IO){
            return@withContext volumeDao.getThickList(startWith, length, top)
        }
    }

    suspend fun getVolume(length: String, thick: String, top: Short): String {
        return withContext(Dispatchers.IO){
            return@withContext volumeDao.getVolumeValue(length, thick, top)
        }
    }
}