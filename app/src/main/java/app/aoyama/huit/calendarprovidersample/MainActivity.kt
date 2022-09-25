package app.aoyama.huit.calendarprovidersample

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import app.aoyama.huit.calendarprovidersample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val EVENT_PROJECTION: Array<String> = arrayOf(
        CalendarContract.Calendars._ID,                     // 0
        CalendarContract.Calendars.ACCOUNT_NAME,            // 1
        CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,   // 2
        CalendarContract.Calendars.OWNER_ACCOUNT            // 3
    )

    private val PROJECTION_ID_INDEX: Int = 0
    private val PROJECTION_ACCOUNT_NAME_INDEX: Int = 1
    private val PROJECTION_DISPLAY_NAME_INDEX: Int = 2
    private val PROJECTION_OWNER_ACCOUNT_INDEX: Int = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val uri: Uri = CalendarContract.Calendars.CONTENT_URI
        val selection: String = "((${CalendarContract.Calendars.ACCOUNT_NAME} = ?) AND (" +
                "${CalendarContract.Calendars.ACCOUNT_TYPE} = ?) AND (" +
                "${CalendarContract.Calendars.OWNER_ACCOUNT} = ?))"
        val selectionArgs: Array<String> =
            arrayOf("eitoao88@gmail.com", "com.google", "eitoao88@gmail.com")
        val cur: Cursor =
            contentResolver.query(uri, EVENT_PROJECTION, selection, selectionArgs, null)

        while (cur.moveToNext()) {
            val calID: Long = cur.getLong(PROJECTION_ID_INDEX)
            val displayName: String = cur.getString(PROJECTION_DISPLAY_NAME_INDEX)
            val accountName: String = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX)
            val ownerName: String = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX)

            binding.getButton.setOnClickListener {
                println(accountName)
            }
        }

    }

}