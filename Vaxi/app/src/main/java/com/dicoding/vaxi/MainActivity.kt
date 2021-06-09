package com.dicoding.vaxi

import android.os.Bundle
import android.util.Log
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.gson.GsonBuilder
import com.robinhood.ticker.TickerUtils
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val BASE_URL = "https://capstone-315702.et.r.appspot.com/"
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var dataTampilSekarang: List<VaxiData>
    private lateinit var adapter: VaxiSparkAdapter
    private lateinit var vaxiPublicData: List<VaxiData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd").create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val apiInstance = retrofit.create(VaxiServices::class.java)

        // Get the vaccination data
        apiInstance
            .getVaksinasi()
            .enqueue(object : Callback<VaxiDataResponse> {

                override fun onFailure(call: Call<VaxiDataResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure $t")
                }

                override fun onResponse(
                    call: Call<VaxiDataResponse>,
                    response: Response<VaxiDataResponse>
                ) {
                    Log.i(TAG, "onResponse $response")
                    val vaxiData = response.body()?.monitoring
                    if (vaxiData == null) {
                        Log.w(TAG, "Did not receive a valid response body")
                        return
                    }
                    setEventListeners()
                    vaxiPublicData = vaxiData
                    Log.i(TAG, "Update graph with vaccination data")
                    updateDisplayWithData(vaxiPublicData)
                }

            })
    }

    private fun setEventListeners() {
        jumlahKasus.setCharacterLists(TickerUtils.provideNumberList())

        // Add listener for the user on the chart
        sparkView.isScrubEnabled = true
        sparkView.setScrubListener { itemData ->
            if (itemData is VaxiData) {
                updateInfoForDate(itemData)
            }
        }
        // Respond to radio button selected
        radioGroupPeriode.setOnCheckedChangeListener { _, checkedId ->
            adapter.hariLalu = when (checkedId) {
                R.id.minggu -> Periode.MINGGU
                R.id.bulan -> Periode.BULAN
                else -> Periode.MAKSIMUM
            }
            adapter.notifyDataSetChanged()
        }
        radioGroupPilihan.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.vaksinasi1 -> updateDisplayPilihan(Pilihan.VAKSINASI1)
                R.id.vaksinasi2 -> updateDisplayPilihan(Pilihan.VAKSINASI2)
                R.id.prediksi -> updateDisplayPilihan(Pilihan.VAKSINASI3)
            }
        }
    }

    private fun updateDisplayPilihan(pilihan: Pilihan) {
        // Update warna grafik
        val colorResource = when(pilihan) {
            Pilihan.VAKSINASI1 -> R.color.vaksinasi1
            Pilihan.VAKSINASI2 -> R.color.vaksinasi2
            Pilihan.VAKSINASI3 -> R.color.vaksinasi3
        }

        @ColorInt val colorInt = ContextCompat.getColor(this, colorResource)
        sparkView.lineColor = colorInt
        jumlahKasus.setTextColor(colorInt)

        // Update pilihan pada updater
        adapter.pilihan = pilihan
        adapter.notifyDataSetChanged()

        // Reset angka dan tanggal di bawah
        updateInfoForDate(dataTampilSekarang.last())
    }

    private fun updateDisplayWithData(vaxiPublicData: List<VaxiData>) {
        dataTampilSekarang = vaxiPublicData
        // Create a new SparkAdapter with the data
        adapter = VaxiSparkAdapter(vaxiPublicData)
        sparkView.adapter = adapter
        // Update radio buttons to select vaksinasi 1 by default
        vaksinasi1.isChecked = true
        maksimum.isChecked = true
        // Display metric for the most recent date
        updateDisplayPilihan(Pilihan.VAKSINASI1)
    }

    private fun updateInfoForDate(vaxiData: VaxiData) {
        val angkaKasus = when (adapter.pilihan) {
            Pilihan.VAKSINASI1 -> vaxiData.training
            Pilihan.VAKSINASI2 -> vaxiData.test
            Pilihan.VAKSINASI3 -> vaxiData.prediksi
        }
        jumlahKasus.text = NumberFormat.getInstance().format(angkaKasus)
        val outputDateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)
        tanggalKasus.text = outputDateFormat.format(vaxiData.date)
    }
}