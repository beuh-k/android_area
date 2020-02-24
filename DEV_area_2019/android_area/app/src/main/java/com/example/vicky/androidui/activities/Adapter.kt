package com.example.vicky.androidui.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vicky.androidui.R

class Adapter(val context: HomePage, private val androidVersionList: ArrayList<ServiceModel>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.txtTitle.text = androidVersionList[p1].codeName
        p0.txtContent?.text = "Statut : ${androidVersionList[p1].versionName}"
        p0.image.setImageResource(androidVersionList[p1].imgResId!!)
        p0.connection = androidVersionList[p1].connect!!
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.service_model, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return androidVersionList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val txtTitle: TextView = itemView.findViewById(R.id.title)
        val txtContent: TextView = itemView.findViewById(R.id.description)
        val image: ImageView = itemView.findViewById(R.id.logo)
        var connection : Button = itemView.findViewById(R.id.button_connect)


        override fun onClick(p0: View?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }


    }


}