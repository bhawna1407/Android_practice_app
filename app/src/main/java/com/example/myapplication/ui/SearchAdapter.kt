import android.text.method.TextKeyListener.clear
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class SearchAdapter(private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

//    private val names = mutableListOf<String>()
//    private val ids = mutableListOf<Int>()

//    fun submitList(newNames: List<String>) {
//        names.clear()
//        names.addAll(newNames)
//        notifyDataSetChanged()
//    }

//    private val data = mutableListOf<Pair<String, String>>()
//    private val types = mutableListOf<String>()
//
//    fun submitList(newData: List<Pair<String, String>>) {
//        data.clear()
//        data.addAll(newData)
//        notifyDataSetChanged()
//    }
//    fun submitType(newTypes: String) {
//        types.clear()
//        types.addAll(newTypes)
//        notifyDataSetChanged()
//    }

    private val data = mutableListOf<Triple<String, String, String>>()

    fun submitList(newData: List<Triple<String, String, String>>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(name: String, id:String, type: String)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (name, id, type) = data[position]

        holder.textView.text = "$name [$type]" // Assuming you want to display both name and type

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(name, id, type)
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }
}

