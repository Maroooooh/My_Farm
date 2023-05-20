import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.EditText
import com.example.myfarm.R

class GridAdapter(private val context: Context) : BaseAdapter() {
    companion object {  var maporder = mutableMapOf<String, String>()  }

    private val arrayFruits = arrayOf(
        R.drawable.orange, R.drawable.apricot, R.drawable.grapes, R.drawable.mangga,
        R.drawable.pineapple, R.drawable.pomegranate, R.drawable.strawberry, R.drawable.fig
    )
    private val fruitNames = arrayOf(
        "Orange", "Apricot", "Grapes", "Mango",
        "Pineapple", "Pomegranate", "Strawberry", "Fig"
    )
    private val fruitPrices = arrayOf(
        "0.99$", "1.49$", "2.99$", "1.79$",
        "2.49$", "2.89$", "0.79$", "1.99$"
    )
    private val fprices = doubleArrayOf(
        0.99, 1.49, 2.99, 1.79,
        2.49, 2.89, 0.79, 1.99
    )

    override fun getCount(): Int {
        return arrayFruits.size
    }

    override fun getItem(position: Int): Any {
        return arrayFruits[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    fun check(name: String): Double {
        for (i in fruitNames.indices) {
            if (fruitNames[i] == name) {
                return fprices[i]
            }
        }
        return 0.0
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.fitemmodel, parent, false)
            viewHolder = ViewHolder()
            viewHolder.circleTextView = view.findViewById(R.id.circleTextView)
            viewHolder.imageView = view.findViewById(R.id.imageView)
            viewHolder.textView = view.findViewById(R.id.textView)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        viewHolder.imageView.setImageResource(arrayFruits[position])
        viewHolder.textView.text = fruitNames[position]
        viewHolder.circleTextView.text = fruitPrices[position]

        view.setOnClickListener {
            showQuantityDialog(fruitNames[position])
        }

        return view
    }

    private class ViewHolder {
        lateinit var imageView: ImageView
        lateinit var textView: TextView
        lateinit var circleTextView: TextView
    }

    private fun showQuantityDialog(fruitName: String) {
        val quantityDialog = AlertDialog.Builder(context)
        quantityDialog.setTitle("Order $fruitName")
        quantityDialog.setMessage("Enter quantity in kg :")

        val quantityEditText = EditText(context)
        quantityDialog.setView(quantityEditText)

        quantityDialog.setPositiveButton("Order") { dialog, which ->
            val quantity = quantityEditText.text.toString()
            // Perform the ordering logic here
            maporder[fruitName] = quantity

            Toast.makeText(context, "Ordered $quantity $fruitName", Toast.LENGTH_SHORT).show()
            val sharedPreferences = context.getSharedPreferences("MyFarm", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(fruitName, quantity)
            editor.apply()
        }

        quantityDialog.setNegativeButton("Cancel") { dialog, which -> dialog.dismiss() }

        quantityDialog.show()
    }
}
