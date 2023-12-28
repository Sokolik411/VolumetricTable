package ru.vsokolova.volumetric_table.db.chips_data

import java.io.Serializable

class ChipObject(private val amount: String, private val volume: String) : Serializable {
    private val result: String = (Integer.valueOf(amount)*(volume).toDouble()).toString()

    fun getAmount():String {
        return amount
    }

    fun getVolume():String {
        return volume
    }

    fun getResult():String {
        return result
    }
}
