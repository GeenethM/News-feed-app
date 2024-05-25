package nibm.hd231.madassignment

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class SecondFragment(var newscat:String) : Fragment(), headAdapter.OnButtonClickListener {
    private lateinit var adapter: headAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var headsArrayList: ArrayList<Heads>
    private lateinit var dbhelper: DBHelper
    private lateinit var vwartsearch: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbhelper = DBHelper(requireContext())

        vwartsearch = view.findViewById(R.id.vwartsearch)
        vwartsearch.clearFocus();

        vwartsearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle query submission if needed
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle query text changes and filter your data
                if (newText != null) {
                    headsArrayList = filterList(newText)
                    adapter.updateData(headsArrayList)
                }
                filterList(newText)
                return true
            }
        })
        headsArrayList = dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.rvhead)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        adapter = headAdapter(headsArrayList)
        adapter.onButtonClickListener = this
        recyclerView.adapter = adapter
    }

    private fun filterList(text: String?): ArrayList<Heads> {
        val list = arrayListOf<Heads>()
        if(text != null){
            val cursor: Cursor = dbhelper.getTitles(newscat)

            while(cursor.moveToNext()) {
                val catOne: String = cursor.getString(0)
                val news = Heads(catOne)
                list.add(news)
            }

            cursor.close()

            val result1 = list.filter { it.title.contains(text, ignoreCase = true) }
            list.clear()
            list.addAll(result1)
        }

        return list
    }

    private fun dataInitialize(): ArrayList<Heads>{
        val list = arrayListOf<Heads>()
//        dbhelper = DBHelper(requireContext())

        val cursor: Cursor = dbhelper.getTitles(newscat)

        while(cursor.moveToNext()) {
            val catOne: String = cursor.getString(0)
            val news = Heads(catOne)
            list.add(news)
        }

        cursor.close()
        return list


    }

    override fun onButtonClick(data: String) {
        // Handle the button click and received data in the fragment
        // You can use the 'data' parameter to get information from the adapter

        val titleart:String = data

        //Loading a fragment from another fragment
        //Create fragment manager
        var fragManager: FragmentManager =requireActivity().supportFragmentManager
        //Create fragment transaction
        var fragTransaction: FragmentTransaction =fragManager.beginTransaction()

        //Create fragment instance
        var fragment:ThirdFragment=ThirdFragment(titleart)

        //Fragment Container
        var fragmentContainer=R.id.fragmentContainer

        //Specify transaction
        fragTransaction.replace(fragmentContainer, fragment)
        fragTransaction.addToBackStack(null)

        //commit transaction
        fragTransaction.commit()
    }


}