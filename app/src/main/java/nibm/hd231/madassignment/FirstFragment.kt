package nibm.hd231.madassignment

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class FirstFragment : Fragment(), newsAdapter.OnButtonClickListener {
    private lateinit var adapter: newsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsArrayList: ArrayList<News>
    private lateinit var dbhelper: DBHelper
    private lateinit var vwsearch: SearchView
//    private lateinit var List<News> itemList

//    lateinit var news : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        dataInitialize()
        dbhelper = DBHelper(requireContext())


        vwsearch = view.findViewById(R.id.vwsearch)
        vwsearch.clearFocus();

        vwsearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle query submission if needed
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle query text changes and filter your data
                if (newText != null) {
                    newsArrayList = filterList(newText)
                    adapter.updateData(newsArrayList)
                }
                filterList(newText)
                return true
            }
        })





        newsArrayList = dataInitialize()
        val layoutManager = LinearLayoutManager(context)

        recyclerView = view.findViewById(R.id.rvnews)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
//        adapter.updateData(newsArrayList)
        adapter = newsAdapter(newsArrayList)
        adapter.onButtonClickListener = this
        recyclerView.adapter = adapter
    }

    private fun filterList(text: String?): ArrayList<News> {
        val list = arrayListOf<News>()
        if(text != null){
            val cursor: Cursor = dbhelper.getCategory()

            while(cursor.moveToNext()) {
                val catOne: String = cursor.getString(0)
                val news = News(catOne)
                list.add(news)
            }

            cursor.close()

            val result1 = list.filter { it.name.contains(text, ignoreCase = true) }
            list.clear()
            list.addAll(result1)
        }

        return list
    }


    private fun dataInitialize(): ArrayList<News>{
        val list = arrayListOf<News>()
            val cursor: Cursor = dbhelper.getCategory()

            while(cursor.moveToNext()) {
                val catOne: String = cursor.getString(0)
                val news = News(catOne)
                list.add(news)
            }
            cursor.close()

        return list
    }

    override fun onButtonClick(data: String) {
        // Handle the button click and received data in the fragment
        // You can use the 'data' parameter to get information from the adapter

        val newscat:String = data

        //Loading a fragment from another fragment
        //Create fragment manager
        var fragManager: FragmentManager =requireActivity().supportFragmentManager
        //Create fragment transaction
        var fragTransaction: FragmentTransaction =fragManager.beginTransaction()

        //Create fragment instance
        var fragment:SecondFragment=SecondFragment(newscat)

        //Fragment Container
        var fragmentContainer=R.id.fragmentContainer

        //Specify transaction
        fragTransaction.replace(fragmentContainer, fragment)
        fragTransaction.addToBackStack(null)

        //commit transaction
        fragTransaction.commit()
    }


}