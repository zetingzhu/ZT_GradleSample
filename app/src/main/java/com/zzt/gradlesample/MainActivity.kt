package com.zzt.gradlesample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.zzt.adapter.StartActivityRecyclerAdapter
import com.zzt.entity.StartActivityDao
import com.zzt.gradlesample.asm.AsmTest

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName
    var rv_list: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        rv_list = findViewById(R.id.rv_list)

        val mListDialog: MutableList<StartActivityDao> = ArrayList()
        mListDialog.add(StartActivityDao("日志 1", " ", "0"))
        mListDialog.add(StartActivityDao("日志 2", " ", "1"))
        mListDialog.add(StartActivityDao("日志 3", " ", "2"))
        mListDialog.add(StartActivityDao("日志 4", " ", "3"))
        mListDialog.add(StartActivityDao("日志 5", " ", "4"))
        mListDialog.add(StartActivityDao("日志 6", " ", "5"))
        mListDialog.add(StartActivityDao("日志 7", " ", "6"))
        mListDialog.add(StartActivityDao("日志 8", " ", "7"))

        StartActivityRecyclerAdapter.setAdapterData(
            rv_list,
            RecyclerView.VERTICAL,
            mListDialog
        ) { itemView: View?, position: Int, data: StartActivityDao ->
            when (data.arouter) {
                "0" -> {
                    AsmTest.getInstance().test()
                }

                "1" -> {
                    AsmTest.getInstance().test2()
                }

                "2" -> {
                    AsmTest.getInstance().test3()
                }

                "3" -> {
                    AsmTest.getInstance().test4()
                }

                "4" -> {
                    AsmTest.getInstance().test5()
                }

                "5" -> {
                    AsmTest.getInstance().test6(object : Runnable {
                        override fun run() {
                            Log.v(TAG, "runnable run on 6 " + Thread.currentThread().name)
                        }
                    })
                }

                "6" -> {
                    AsmTest.getInstance().test7(object : Runnable {
                        override fun run() {
                            Log.v(TAG, "runnable run on 7 " + Thread.currentThread().name)
                        }
                    })
                }

                "7" -> {
                    AsmTest.getInstance().test8()
                }
            }
        }
    }
}