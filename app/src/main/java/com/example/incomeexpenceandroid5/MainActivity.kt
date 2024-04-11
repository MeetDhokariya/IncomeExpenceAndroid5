package com.example.incomeexpenceandroid5

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.incomeexpenceandroid5.adapter.DataAdaoter
import com.example.incomeexpenceandroid5.databinding.ActivityMainBinding
import com.example.incomeexpenceandroid5.helper.DBhelper
import com.example.incomeexpenceandroid5.model.IncomeExpenseModel

lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {

    lateinit var db : DBhelper
    lateinit var dataList: ArrayList<IncomeExpenseModel>

    override fun onResume() {
        super.onResume()
        dataList = DBhelper(this).getIncomeExpense()
        rvsetup()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db = DBhelper(this)
        dataList = db.getIncomeExpense()
        rvsetup()

        binding.btnAddCategory.setOnClickListener {
            var intent = Intent(this,CategoryActivity::class.java)
            startActivity(intent)
        }

        binding.floatingButton.setOnClickListener {
            var intent = Intent(this,IncomeExpenseActivity::class.java)
            startActivity(intent)
        }
    }
    private fun rvsetup() {
        var adapter =DataAdaoter(this,dataList)
        var lm = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        binding.rvData.layoutManager = lm
        binding.rvData.adapter = adapter
    }
}