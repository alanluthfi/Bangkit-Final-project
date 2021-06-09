package com.dicoding.vaxi

import android.graphics.RectF
import com.robinhood.spark.SparkAdapter

class VaxiSparkAdapter(private val vaxiPublicData: List<VaxiData>): SparkAdapter() {

    var pilihan = Pilihan.VAKSINASI1
    var hariLalu = Periode.MAKSIMUM

    override fun getCount() = vaxiPublicData.size

    override fun getItem(index: Int) = vaxiPublicData[index]

    override fun getY(index: Int): Float {
        val dataHarianTerpilih = vaxiPublicData[index]
        return when(pilihan) {
            Pilihan.VAKSINASI1 -> dataHarianTerpilih.training.toFloat()
            Pilihan.VAKSINASI2 -> dataHarianTerpilih.test.toFloat()
            Pilihan.VAKSINASI3 -> dataHarianTerpilih.prediksi.toFloat()
        }
    }

    override fun getDataBounds(): RectF {
        val bounds = super.getDataBounds()
        if (hariLalu != Periode.MAKSIMUM) {
            bounds.left = count - hariLalu.jumlahHari.toFloat()
        }
        return bounds
    }

}
