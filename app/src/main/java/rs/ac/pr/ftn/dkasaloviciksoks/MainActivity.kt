package rs.ac.pr.ftn.dkasaloviciksoks

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var inputNumber: EditText
    private lateinit var btnRed: Button
    private lateinit var btnBlue: Button
    private lateinit var btnNewGame: Button
    private val cells = mutableListOf<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputNumber = findViewById(R.id.inputNumber)
        btnRed = findViewById(R.id.btnRed)
        btnBlue = findViewById(R.id.btnBlue)
        btnNewGame = findViewById(R.id.btnNewGame)

        for (i in 1..9) {
            val cellId = resources.getIdentifier("cell$i", "id", packageName)
            cells.add(findViewById(cellId))
        }

        btnRed.setOnClickListener { paintCell(Color.RED) }
        btnBlue.setOnClickListener { paintCell(Color.BLUE) }

        btnNewGame.setOnClickListener {
            for (cell in cells) {
                cell.setBackgroundColor(Color.WHITE)
                cell.text = ""
            }
        }
    }

    private fun paintCell(color: Int) {
        val number = inputNumber.text.toString().toIntOrNull()
        if (number in 1..9) {
            cells[number!! - 1].setBackgroundColor(color)
        } else {
            Toast.makeText(this, "Унесите број од 1 до 9", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        for (i in 1..9) {
            val cell = cells[i - 1]
            val color = (cell.background as ColorDrawable).color
            outState.putInt("cell$i", color)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        for (i in 1..9) {
            val color = savedInstanceState.getInt("cell$i", Color.WHITE)
            cells[i - 1].setBackgroundColor(color)
        }
    }
}
