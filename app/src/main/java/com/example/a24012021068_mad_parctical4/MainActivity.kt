package com.example.a24012021068_mad_parctical4

import android.app.Service
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import java.time.Month
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var textAlarm: TextView
    lateinit var cardSetAlarm: MaterialCardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textAlarm = findViewById<TextView>(R.id.txtTime)
        cardSetAlarm = findViewById(R.id.card2)
        cardSetAlarm.visibility = View.GONE
        findViewById<MaterialButton>(R.id.btnCreate).setOnClickListener {
            showTimeDialog()
        }
        findViewById<MaterialButton>(R.id.btnCancel).setOnClickListener {
        }
    }
    private fun showTimeDialog(){
        val cldr: Calendar= Calendar.getInstance()
        val h: Int = cldr.get(Calendar.HOUR_OF_DAY)
        val m: Int = cldr.get(Calendar.MINUTE)
        val picker = TimePickerDialog(
            this,{tp,hour,mitute -> sendDialogDateToActivity(hour,mitute) },h,m,false
        )
        picker.show()
    }
    private  fun sendDialogDateToActivity(hour: Int, mitute: Int) {
        val alarmCalendar = Calendar.getInstance()
        val year: Int = alarmCalendar.get(Calendar.YEAR)
        val Month: Int = alarmCalendar.get(Calendar.MONTH)
        val date: Int = alarmCalendar.get(Calendar.DATE)
        alarmCalendar.set(year, Month, date, hour, mitute, 0)
        if (setAlarm(alarmCalendar.timeInMillis, AlarmBroadcastReceiver.START_VAL)) {
            textAlarm.text="$hour:$mitute"
            cardSetAlarm.visibility = View.VISIBLE

        }
    }
    fun setAlarm(milliTime : Long,str: String): Boolean{
        val intent = Intent(this, AlarmBroadcastReceiver::class.java)
        intent.putExtra("ACTION", str)

        return true

    }

}
