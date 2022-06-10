package com.example.yows


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yows.adapters.FoodCategoryAdapter
import com.example.yows.databinding.ActivityRestourantBinding
import com.example.yows.models.FoodCategory
import com.example.yows.models.FoodDetail
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


class RestourantActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRestourantBinding
    private var foodCategory = ArrayList<FoodCategory>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestourantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.include.imgback.setOnClickListener {
            startActivity(Intent(this@RestourantActivity,MainActivity::class.java))
        }

        addItemsFromJSON()
        binding.myRecycler.setHasFixedSize(true)

        // use a linear layout manager

        // use a linear layout manager
        binding.myRecycler.layoutManager = LinearLayoutManager(this)

        // specify an adapter (see also next example)

        // specify an adapter (see also next example)
        binding.myRecycler.adapter = FoodCategoryAdapter(foodCategory)



    }
    private fun addItemsFromJSON() {
        try {
            val jsonDataString = readJSONDataFromFile()
            val jsonArray = JSONArray(jsonDataString)
            for (i in 0 until jsonArray.length()) {
                var viewItems = ArrayList<FoodDetail>()
                val itemObj = jsonArray.getJSONObject(i)
                val categoryName = itemObj.getString("categoryName")
                val foodList = itemObj.getJSONArray("foodList")
                for (j in 0 until foodList.length()){
                    val foodDetail = foodList.getJSONObject(j)
                    val name = foodDetail.getString("name")
                    val detail = foodDetail.getString("detail")
                    val discountPrice = foodDetail.getString("discountPrice")
                    val price = foodDetail.getString("price")
                    val image = foodDetail.getString("image")
                    viewItems.add(FoodDetail(name, detail,discountPrice,price,image))
                }
                foodCategory.add(FoodCategory(categoryName,viewItems))
                //viewItems.clear()

            }
        } catch (e: JSONException) {
            Log.d("Error", "addItemsFromJSON: ", e)
        } catch (e: IOException) {
            Log.d("Error", "addItemsFromJSON: ", e)
        }
    }

    @Throws(IOException::class)
    private fun readJSONDataFromFile(): String {
        var inputStream: InputStream? = null
        val builder = StringBuilder()
        try {
            var jsonString: String?
            inputStream = resources.openRawResource(R.raw.name)
            val bufferedReader = BufferedReader(
                InputStreamReader(inputStream, "UTF-8")
            )
            while (bufferedReader.readLine().also { jsonString = it } != null) {
                builder.append(jsonString)
            }
        } finally {
            if (inputStream != null) {
                inputStream.close()
            }
        }
        return String(builder)
    }
}