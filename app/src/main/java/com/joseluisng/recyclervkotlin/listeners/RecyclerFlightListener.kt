package com.joseluisng.recyclervkotlin.listeners

import com.joseluisng.recyclervkotlin.models.Flight

interface RecyclerFlightListener {
    fun onClick(flight: Flight, position: Int)
    fun onDelete(flight: Flight, position: Int)

}