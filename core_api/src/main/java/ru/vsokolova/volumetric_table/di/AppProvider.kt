package ru.vsokolova.volumetric_table.di

import android.content.Context

interface AppProvider {

    fun provideContext(): Context
}