package com.elisabethlobis.myshoppinglist
// Mit Package wird die App im Store identifiziert.

// Import werden die  Daten, die man braucht aus Android importiert
import android.os.Bundle
import android.text.InputType
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.elisabethlobis.myshoppinglist.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


// Private -> gilt nur fuer diese Klasse
// lateinit -> die beteffende Variable wird nicht sofort definiert
// Wert nach Doppelpunkt -> Wie verhaelt sich die betreffende Variable?
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var lvTodoList: ListView
    private lateinit var shoppingItem: ArrayList<String>
    private lateinit var fab: FloatingActionButton
    private lateinit var itemAdapter: ArrayAdapter<String>

    // Override fun on create bedeutet, dass der Code innerhalb der Klammer beim Start der App ausgefuehrt wird.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Variablen werden mitteinander verknuepft
        lvTodoList = findViewById(R.id.lvTodoList)
        fab = findViewById(R.id.fab)
        shoppingItem = ArrayList()

        shoppingItem.add("Apfel")
        shoppingItem.add("Banane")

        itemAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            shoppingItem
        )

        lvTodoList.adapter = itemAdapter

        lvTodoList.setOnItemLongClickListener(OnItemLongClickListener { arg0, arg1, pos, id ->
            shoppingItem.removeAt(pos)
            itemAdapter.notifyDataSetChanged()
            Toast.makeText(applicationContext, "Element gelöscht!", Toast.LENGTH_SHORT).show()
            true
        })

        fab.setOnClickListener {
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Hinzufügen")

            var input = EditText(this)
            input.hint = "Text eingeben"
            input.inputType = InputType.TYPE_CLASS_TEXT

            builder.setView(input)

            builder.setPositiveButton("OK") { dialog, which ->
                shoppingItem.add(input.text.toString())
            }

            builder.setNegativeButton("Abbrechen") { dialog, which ->
                Toast.makeText(applicationContext, "Abgebrochen", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }
    }
}