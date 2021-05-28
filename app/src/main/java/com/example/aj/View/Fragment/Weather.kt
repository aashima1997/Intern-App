package com.example.aj.View.Fragment

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.aj.R
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


@Suppress("DEPRECATION")
class Weather : Fragment() {
    lateinit var pg: ProgressBar
    lateinit var rl: RelativeLayout
    lateinit var erText: TextView
    lateinit var adText: TextView
    lateinit var upText:TextView
    lateinit var st:TextView
    lateinit var tempt:TextView
    lateinit var mint:TextView
    lateinit var maxt:TextView
    lateinit var sunriset:TextView
    lateinit var sunsetT:TextView
    lateinit var windt:TextView
    lateinit var prest:TextView
    lateinit var humidt:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)
        pg = view!!.findViewById<ProgressBar>(R.id.loader)
        rl = view!!.findViewById<RelativeLayout>(R.id.mainContainer)
        erText = view!!.findViewById<TextView>(R.id.errorText)
        adText = view!!.findViewById<TextView>(R.id.address)
        upText=view!!.findViewById<TextView>(R.id.updated_at)
        st=view!!.findViewById<TextView>(R.id.status)
        tempt=view!!.findViewById<TextView>(R.id.temp)
        mint=view!!.findViewById<TextView>(R.id.temp_min)
        maxt=view!!.findViewById<TextView>(R.id.temp_max)
        sunriset=view!!.findViewById<TextView>(R.id.sunrise)
        sunsetT=view!!.findViewById<TextView>(R.id.sunset)
        windt=view!!.findViewById<TextView>(R.id.wind)
        prest=view!!.findViewById<TextView>(R.id.pressure)
      humidt=  view!!.findViewById<TextView>(R.id.humidity)


        weatherTask().execute()
        return view
    }


    @SuppressLint("StaticFieldLeak")
    inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            /* Showing the ProgressBar, Making the main design GONE */
           pg.visibility = View.VISIBLE
            rl.visibility = View.GONE
            erText.visibility = View.GONE
        }

        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            val CITY: String = "Madurai,in"
            val API: String = "8118ed6ee68db2debfaaa5a44c832918"
            try {
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API").readText(
                        Charsets.UTF_8
                    )
            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)

                val updatedAt: Long = jsonObj.getLong("dt")
                val updatedAtText =
                    "Updated at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
                        Date(updatedAt * 1000)
                    )
                val temp = main.getString("temp") + "°C"
                val tempMin = "Min Temp: " + main.getString("temp_min") + "°C"
                val tempMax = "Max Temp: " + main.getString("temp_max") + "°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")

                val sunrise: Long = sys.getLong("sunrise")
                val sunset: Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")

                val address = jsonObj.getString("name") + ", " + sys.getString("country")

                /* Populating extracted data into our views */
               adText.text = address
               upText.text = updatedAtText
                st.text = weatherDescription.capitalize()
               tempt.text = temp
                mint.text = tempMin
                maxt.text = tempMax
               sunriset.text =
                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise * 1000))
               sunsetT.text =
                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset * 1000))
               windt.text = windSpeed
               prest .text = pressure
               humidt .text = humidity

                /* Views populated, Hiding the loader, Showing the main design */
                pg.visibility = View.GONE
                rl.visibility = View.VISIBLE

            } catch (e: Exception) {
                pg.visibility = View.GONE
                erText.visibility = View.VISIBLE
            }

        }
    }
}
