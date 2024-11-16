package com.example.myapplication.ui.dashboard.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.HelperClasses.EventHelperClass
import com.example.myapplication.ui.HelperClasses.adapters.EventAdapter

class HomeFragment : Fragment() {

    private lateinit var eventRecyclerView: RecyclerView
    private lateinit var eventAdapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Fragment initialization logic here (if needed)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        eventRecyclerView = view.findViewById(R.id.event_recyclerView) // Initialize RecyclerView
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        eventRecyclerView.setHasFixedSize(true)
        eventRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)

        // Create a list of events
        val locations = ArrayList<EventHelperClass>()
        locations.add(EventHelperClass(R.drawable.amman_park, "park", "exercise", "My first meeting"))

        // Initialize the adapter and set it to the RecyclerView
        eventAdapter = EventAdapter(locations)
        eventRecyclerView.adapter = eventAdapter

    }
}
