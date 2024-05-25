package nibm.hd231.madassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class headAdapter(private var headsList:ArrayList<Heads>): RecyclerView.Adapter<headAdapter.ViewHolder>()  {

    interface OnButtonClickListener {
        fun onButtonClick(data: String)  // Adjust the data type as needed
    }

    var onButtonClickListener: OnButtonClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): headAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tiles_view,parent,false)
//        return headAdapter.ViewHolder(itemView)
        return ViewHolder(itemView)
    }

    fun updateData(newData: List<Heads>) {
        headsList.clear()
        headsList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: headAdapter.ViewHolder, position: Int) {
        val currentItem = headsList[position]
        holder.lblnames.text = currentItem.title
        holder.btnview.setOnClickListener {
            onButtonClickListener?.onButtonClick(currentItem.title)
        }
    }

    override fun getItemCount(): Int {
        return headsList.size
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