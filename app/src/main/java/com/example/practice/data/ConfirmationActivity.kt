import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.R
import com.example.practice.data.HomePageActivity

class ConfirmationActivity : AppCompatActivity() {

    private lateinit var btnBackHome: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        btnBackHome = findViewById(R.id.back_home)

        btnBackHome.setOnClickListener {

            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
