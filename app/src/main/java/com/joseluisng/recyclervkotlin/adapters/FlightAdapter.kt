package com.joseluisng.recyclervkotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.joseluisng.recyclervkotlin.R
import com.joseluisng.recyclervkotlin.inflate
import com.joseluisng.recyclervkotlin.listeners.RecyclerFlightListener
import com.joseluisng.recyclervkotlin.loadByResource
import com.joseluisng.recyclervkotlin.models.Flight
import kotlinx.android.synthetic.main.recycler_flight.view.*

class FlightAdapter(private val flights: List<Flight>, private val listener: RecyclerFlightListener) : RecyclerView.Adapter<FlightAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.recycler_flight))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(flights[position], listener)

    override fun getItemCount() = flights.size



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(flight: Flight, listener: RecyclerFlightListener) = with(itemView){

            tvCityName.text = flight.city
            imageViewBg.loadByResource(flight.imgResource)
            // Clicks Events
            setOnClickListener { listener.onClick(flight, adapterPosition) }
            //Estamos utilizando los listener que es las funciones del interface para no encapsular la logica dentro del adaptador
            // Ya que el adaptador lo que tiene que hacer es resmplazar solo los valores
            // y cuando hagas un click es decir que el listener va a llamar a ese metodo
            buttonDelete.setOnClickListener { listener.onDelete(flight, adapterPosition)}

        }

    }


}