import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.R

class BuyNowActivity : AppCompatActivity() {

    private lateinit var editTextNumber: EditText
    private lateinit var btnConfirm: Button
    private lateinit var databaseHelper: DatabaseHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_now)

        editTextNumber = findViewById(R.id.input_email_login)
        btnConfirm = findViewById(R.id.btn_confirm)
        databaseHelper = DatabaseHelper(this)

        btnConfirm.setOnClickListener {
            val number = editTextNumber.text.toString()
            if (number.isNotEmpty()) {

                databaseHelper.insertNumber(number)


                val intent = Intent(this, ConfirmationActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
