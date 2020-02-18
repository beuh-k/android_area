package com.example.vicky.androidui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import app.area.ui.model.FeedModel
import com.example.vicky.androidui.R
import com.example.vicky.androidui.fragment.model.ServiceModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    // putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
    class Helper {
        companion object {
            fun <ArrayList> getVersionsList(): ArrayList {
                var androidVersionList = ArrayList<ServiceModel>()
                androidVersionList.add(ServiceModel(R.drawable.logo_facebook, "Facebook", "Not connected", apiLevel = "3"))
                androidVersionList.add(ServiceModel(R.drawable.logo_spotify, "Spotify", "Not connected", apiLevel = "3"))
                androidVersionList.add(ServiceModel(R.drawable.logo_office, "Office", "Not connected", apiLevel = "3"))

                return androidVersionList as ArrayList
            }
        }
    }

    private lateinit var adapter: CustomRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        adapter = CustomRecyclerAdapter(this@HomeFragment, Helper.Companion.getVersionsList())
        list.adapter = adapter
    }


    class CustomRecyclerAdapter(val context: HomeFragment, val androidVersionList: ArrayList<ServiceModel>) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {

        override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
            p0?.txtTitle?.text = androidVersionList[p1].codeName
            p0?.txtContent?.text = "Version : ${androidVersionList[p1].versionName}, Api Name : ${androidVersionList[p1].apiLevel}"
            p0?.image.setImageResource(androidVersionList[p1].imgResId!!)
        }

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
            val v = LayoutInflater.from(p0?.context).inflate(R.layout.service_model, p0, false)
            return ViewHolder(v);
        }

        override fun getItemCount(): Int {
            return androidVersionList.size
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val txtTitle = itemView.findViewById<TextView>(R.id.appOSTitle_txtVw)
            val txtContent = itemView.findViewById<TextView>(R.id.appOSDetails_txtVw)
            val image = itemView.findViewById<ImageView>(R.id.appOS_imageVw)
        }
    }



    private fun getDummyList(): ArrayList<FeedModel> {
        var list = ArrayList<FeedModel>()
        for (i in 1..50) {
            list.add(FeedModel("Dummy item " + i))
        }
        return list
    }
}

//homeViewModel =
//ViewModelProviders.of(this).get(HomeViewModel::class.java)
//val root = inflater.inflate(R.layout.fragment_home, container, false)
//val textView: TextView = root.findViewById(R.id.text_home)
//homeViewModel.text.observe(this, Observer {
//    textView.text = it
//})
//return root