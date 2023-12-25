package ru.vsokolova.volumetric_table.db

import android.app.Application
import androidx.lifecycle.LiveData


class VolumeRepository {
    private var volumeDao: VolumeDao? = null
    private var volumeLiveData: LiveData<List<Volume?>?>? = null

//    fun VolumeRepository(application: Application?) {
//        val db: VolumeDatabase = VolumeDatabase.getDatabase(application)
//        volumeDao = db.getVolumeDao()
//
////        volumeLiveData = volumeDao.get
//    }
//
//    fun getVolumeLiveData(): LiveData<List<Volume?>?>? {
//        return volumeLiveData
//    }
}