package com.example.incomeexpenceandroid5

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.incomeexpenceandroid5.adapter.CategoryAdapter
import com.example.incomeexpenceandroid5.databinding.ActivityCategoryBinding
import com.example.incomeexpenceandroid5.helper.DBhelper
import com.example.incomeexpenceandroid5.model.CategoryModel


lateinit var categoryBinding: ActivityCategoryBinding
class CategoryActivity : AppCompatActivity() {

    var list = arrayListOf<CategoryModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       categoryBinding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(categoryBinding.root)
        var db =DBhelper(this)
       list = db.getCategory()
        initbinding()
    }

    private fun initbinding() {

        var adapter = CategoryAdapter(this@CategoryActivity,list)
        var lm = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        categoryBinding.rvCategory.layoutManager = lm
        categoryBinding.rvCategory.adapter = adapter

        categoryBinding.btnCategory.setOnClickListener {
            var db = DBhelper(this@CategoryActivity)
            if (categoryBinding.EdtCategory.text!!.isEmpty())
            {
                    categoryBinding.EdtCategory.setError("Jo bhai jo ama kaik lakhi to de")

            }
            else
            {
                    db.addCategory(categoryBinding.EdtCategory.text.toString())
                  finish()
            }
        }
    }
}