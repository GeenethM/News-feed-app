package nibm.hd231.madassignment

import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ThirdFragment(var titleart:String) : Fragment() {
    private lateinit var dbhelper: DBHelper
    private lateinit var yourDataList: List<Desc>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_third, container, false)
        var view:View= inflater.inflate(R.layout.fragment_third, container, false)

        dbhelper = DBHelper(requireContext())



        val textView3:TextView = view.findViewById(R.id.textView3)

//        lbldesc.setText(fetchDataFromDatabase())
        fetchDataFromDatabase()
        Log.d("DataListSize", yourDataList.size.toString())
//        lbldesc.text = yourDataList.toString()

        textView3.setText(yourDataList.toString())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        dbhelper = DBHelper(requireContext())
//
//        var lbldesc: TextView = view.findViewById(R.id.lbldesc)
//
////        lbldesc.setText(fetchDataFromDatabase())
//        fetchDataFromDatabase()
////        lbldesc.text = yourDataList.toString()
//        lbldesc.setText("text")

    }

    private fun fetchDataFromDatabase() {
        val dataList = mutableListOf<Desc>()

        val cursor: Cursor = dbhelper.getDesc(titleart)


        while(cursor.moveToNext()) {
            val catOne: String = cursor.getString(0)
            Log.d("DatabaseData", catOne)
            val news = Desc(catOne)
            dataList.add(news)
        }

        cursor.close()

        yourDataList =  dataList
    }


}