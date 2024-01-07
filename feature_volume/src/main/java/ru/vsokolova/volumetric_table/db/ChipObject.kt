package ru.vsokolova.volumetric_table.db

import java.io.Serializable

class ChipObject(private val amount: String, private val volume: String) : Serializable {
    private val result: Double = Integer.valueOf(amount)*(volume).toDouble()

    fun getAmount():String {
        return amount
    }

    fun getVolume():String {
        return volume
    }

    fun getResult():Double {
        return result
    }
}
