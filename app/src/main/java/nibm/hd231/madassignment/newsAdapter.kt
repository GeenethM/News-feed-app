package nibm.hd231.madassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class newsAdapter(private var newsList:ArrayList<News>): RecyclerView.Adapter<newsAdapter.ViewHolder>() {

    interface OnButtonClickListener {
        fun onButtonClick(data: String)  // Adjust the data type as needed
    }

    var onButtonClickListener: OnButtonClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tiles_view,parent,false)
        return ViewHolder(itemView)
    }

    fun updateData(newData: List<News>) {
        newsList.clear()
        newsList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: newsAdapter.ViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.lblnames.text = currentItem.name
        holder.btnview.setOnClickListener {
            onButtonClickListener?.onButtonClick(currentItem.name)
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val lblnames : TextView = view.findViewById(R.id.lblnames)
        val btnview : Button = view.findViewById(R.id.btnview)
//        val textView: TextView
//
//        init {
//            // Define click listener for the ViewHolder's View
//            textView = view.findViewById(R.id.textView)
//        }
    }


}