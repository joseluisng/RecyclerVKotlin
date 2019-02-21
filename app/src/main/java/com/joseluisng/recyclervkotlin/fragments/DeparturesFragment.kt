package com.joseluisng.recyclervkotlin.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joseluisng.recyclervkotlin.R
import com.joseluisng.recyclervkotlin.adapters.FlightAdapter
import com.joseluisng.recyclervkotlin.listeners.RecyclerFlightListener
import com.joseluisng.recyclervkotlin.models.Flight
import com.joseluisng.recyclervkotlin.toast
import kotlinx.android.synthetic.main.fragment_departures.view.*


class DeparturesFragment : Fragment() {

    private val list: ArrayList<Flight> by lazy { getFlights()}

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: FlightAdapter

    private val layoutManager by lazy { LinearLayoutManager(context)}


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //poner el titulo a la barra cuando esta en este fragmento
        activity?.setTitle(R.string.departures_fragment_title)

        // Inflate the layout for this fragment
        val rootview = inflater.inflate(R.layout.fragment_departures, container, false)

        recycler = rootview.recyclerView as RecyclerView
        setRecyclerView()
        return rootview
    }



    private fun setRecyclerView(){
        recycler.setHasFixedSize(true)
        recycler.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
        recycler.layoutManager = layoutManager
        adapter = (FlightAdapter(list, object : RecyclerFlightListener{
            override fun onClick(flight: Flight, position: Int) {
                activity?.toast("Lert's go to ${flight.city}")
            }

            override fun onDelete(flight: Flight, position: Int) {
                list.remove(flight)
                adapter.notifyItemRemoved(position)
                activity?.toast("${flight.city} has been removed!")
            }

        }))
        recycler.adapter = adapter
    }

    // Función para llenar una lista al estilo java
    /*
    private fun getFlights(): ArrayList<Flight>{
        val list = ArrayList<Flight>()
        list.add(Flight(1, "Seatle", R.drawable.seatle))
        list.add(Flight(2, "London", R.drawable.london))

        return list
    }
    */

    //Función para llenar la lista al estilo kotlin
    private fun getFlights(): ArrayList<Flight>{
        return object: ArrayList<Flight>(){
            init {
                add(Flight(1, "London", R.drawable.london_atardecer))
                add(Flight(2, "San Francisco", R.drawable.golden_gate2))
                add(Flight(3, "Paris", R.drawable.thumb_paris))
                add(Flight(4, "Chicado", R.drawable.chicago))
                add(Flight(5, "Nederland", R.drawable.holanda))
                add(Flight(6, "New York", R.drawable.new_york))
                add(Flight(7, "Santorini", R.drawable.santorini))
                add(Flight(8, "Roma", R.drawable.roma_italy))
            }
        }

    }


}
