package com.example.stack

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


class MainActivity : AppCompatActivity(), MyAdapter.Listener {

    private var myAdapter: MyAdapter? = null
    private var myCompositeDisposable: CompositeDisposable? = null
    private var quesArrayList: ArrayList<ques>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myCompositeDisposable = CompositeDisposable()
        initRecyclerView()
        loadData()
    }

    private fun initRecyclerView() {

//Use a layout manager to position your items to look like a standard ListView//


        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        stockques_list.layoutManager = layoutManager

    }

    private fun loadData() {

//Define the Retrofit request//


        Log.d("Abhi tak sab badiya", "1")

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
        client.addInterceptor(logging)

        val requestInterface = Retrofit.Builder()
            .client(client.build())
            .baseUrl("https://api.stackexchange.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(GetData::class.java)


        myCompositeDisposable?.add(
            requestInterface.getData()


                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse)
        )
        Log.d("Abhi tak sab badiya", "2")

    }



    private fun handleResponse(quesList: Question) {

        Log.d("Abhi tak sab badiya", "3")

        quesArrayList = ArrayList(quesList.items)
        myAdapter = MyAdapter(quesArrayList!!, this)

//        rv = findViewById<RecyclerView>(R.id.stockques_list)


        stockques_list.adapter = myAdapter

        Log.d("Abhi tak sab badiya", "4")

    }

    override fun onItemClick(Ques: ques) {

//If the user clicks on an item, then display a Toast//

        Toast.makeText(this, "You clicked: ${Ques.title}", Toast.LENGTH_LONG).show()

    }

    override fun onDestroy() {
        super.onDestroy()

        myCompositeDisposable?.clear()

        Log.d("Abhi tak sab badiya", "7")
    }
}
