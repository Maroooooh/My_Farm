import GridAdapter.Companion.maporder
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

class GridAdapter2(private val context: Context) : BaseAdapter() {

    private val arrayvetbales = arrayOf(
        R.drawable.carrots, R.drawable.cauliflower, R.drawable.bell_peppers, R.drawable.onions,
        R.drawable.peas, R.drawable.potatoes, R.drawable.tomatoes, R.drawable.celery
    )
    private val vegetablenames = arrayOf(
        "Carrots", "Cauliflower", "Bell Peppers", "Onions",
        "Peas", "Potatoes", "Tomatoes", "Celery"
    )
    private val vegetablePrices = arrayOf(
        "1.99$", "2.49$", "1.79$", "0.99$",
        "1.29$", "0.89$", "1.99$", "1.49$"
    )
    private val vprices= doubleArrayOf(
        1.99, 2.49, 1.79, 0.99,
        1.29, 0.89, 1.99, 1.49
    )

    override fun getCount(): Int {
        return arrayvetbales.size
    }

    override fun getItem(position: Int): Any {
        return arrayvetbales[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    fun check(name: String): Double {
        for (i in vegetablenames.indices) {
            if (vegetablenames[i] == name) {
                return vprices[i]
            }
        }
        return 0.0
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.itemmodel, parent, false)
            viewHolder = ViewHolder()
            viewHolder.circleTextView = view.findViewById(R.id.circleTextView)
            viewHolder.imageView = view.findViewById(R.id.imageView)
            viewHolder.textView = view.findViewById(R.id.textView)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        viewHolder.imageView.setImageResource(arrayvetbales[position])
        viewHolder.textView.text = vegetablenames[position]
        viewHolder.circleTextView.text = vegetablePrices[position]

        view.setOnClickListener {
            showQuantityDialog(vegetablenames[position])
        }

        return view
    }

    private class ViewHolder {
        lateinit var imageView: ImageView
        lateinit var textView: TextView
        lateinit var circleTextView: TextView
    }

    private fun showQuantityDialog(vegetableName: String) {
        val quantityDialog = AlertDialog.Builder(context)
        quantityDialog.setTitle("Order $vegetableName")
        quantityDialog.setMessage("Enter quantity in kg : ")

        val quantityEditText = EditText(context)
        quantityDialog.setView(quantityEditText)

        quantityDialog.setPositiveButton("Order") { dialog, which ->
            val quantity = quantityEditText.text.toString()
            // Perform the ordering logic here
            maporder[vegetableName] = quantity

            Toast.makeText(context, "Ordered $quantity $vegetableName", Toast.LENGTH_SHORT).show()
            val sharedPreferences = context.getSharedPreferences("MyFarm", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(vegetableName, quantity)
            editor.apply()
        }

        quantityDialog.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }

        quantityDialog.show()
    }
}
