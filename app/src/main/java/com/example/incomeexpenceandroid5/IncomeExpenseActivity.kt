package com.example.incomeexpenceandroid5

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility
import com.example.incomeexpenceandroid5.databinding.ActivityIncomeExpenseBinding
import com.example.incomeexpenceandroid5.helper.DBhelper
import com.example.incomeexpenceandroid5.model.CategoryModel
import com.example.incomeexpenceandroid5.model.IncomeExpenseModel

lateinit var binding1: ActivityIncomeExpenseBinding
class IncomeExpenseActivity : AppCompatActivity() {

     var id: String? = null
    lateinit var db: DBhelper
    var categoryList= arrayListOf<CategoryModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding1 = ActivityIncomeExpenseBinding.inflate(layoutInflater)
        setContentView(binding1.root)
      db = DBhelper(this)
        categoryList = db.getCategory()
        initbinding()
        getintentData()
    }

    private fun initbinding() {

        var namelist = arrayListOf<String>()
        categoryList.forEach {
            namelist.add(it.name)
        }
        var arrayadapter = ArrayAdapter(
            this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,namelist
        )
        binding1.Spinner.adapter = arrayadapter

        binding1.btnIncome.setOnClickListener {

        var index = binding1.Spinner.selectedItemPosition
        var finelId = "0"

            if(id!=null)
            {
                finelId = id!!
            }

            var model = IncomeExpenseModel(
                finelId,
                binding1.EdtTitle.text.toString(),
                binding1.EdtAmount.text.toString(),
                binding1.EdtNotes.text.toString(),
                binding1.Edtdate.text.toString(),
                binding1.EdtTime.text.toString(),
                namelist[index])

            if(id!=null)
            {
                db.updateIncomeExpense(model)
            }
            else
            {
                db.addIncomeExpense(model)
            }
            finish()
        }

        binding1.btnExpense.setOnClickListener {

            var index = binding1.Spinner.selectedItemPosition
            var finelId = "0"

            if(id!=null)
            {
                finelId = id!!
            }

            var model = IncomeExpenseModel(
                finelId,
                binding1.EdtTitle.text.toString(),
                binding1.EdtAmount.text.toString(),
                binding1.EdtNotes.text.toString(),
                binding1.Edtdate.text.toString(),
                binding1.EdtTime.text.toString(),
                namelist[index])

            if(id!=null)
            {
                db.updateIncomeExpense(model)
            }
            else
            {
                db.addIncomeExpense(model)
            }
            finish()
        }
        binding1.btnDelete.setOnClickListener {
            db.deletIncomeExpense(id!!)
            finish()
        }

    }

    private fun getintentData() {

        var notes = intent.getStringExtra("notes")
        var title = intent.getStringExtra("title")
        var amount = intent.getStringExtra("amount")
        var date = intent.getStringExtra("date")
        var id = intent.getStringExtra("id")

        var category = intent.getStringExtra("category")
        var time = intent.getStringExtra("time")

        if (amount!=null)
        {
            binding1.btnDelete.visibility = View.VISIBLE
            binding1.EdtAmount.setText("$amount")
            binding1.EdtNotes.setText("$notes")
            binding1.Edtdate.setText("$date")
            binding1.EdtTime.setText("$time")
            binding1.EdtTitle.setText("$title")

            var i = 0
            while (i < categoryList.size){
                if(categoryList[i].name.equals(category)){
                    binding1.Spinner.setSelection(i)
                }
                i++
            }
        }
    }
}