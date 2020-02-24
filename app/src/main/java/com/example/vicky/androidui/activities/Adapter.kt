package com.example.vicky.androidui.activities

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vicky.androidui.R

class Adapter(
        private val androidVersionList: ArrayList<ServiceModel>,
        private val onClickListener: (codeName: String) -> Unit
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.txtTitle.text = androidVersionList[p1].codeName
        p0.txtContent.text = "Statut : ${androidVersionList[p1].versionName}"
        p0.image.setImageResource(androidVersionList[p1].imgResId!!)
        p0.connection.setOnClickListener {
            onClickListener(androidVersionList[p1].codeName!!)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.service_model, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return androidVersionList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView = itemView.findViewById(R.id.title)
        val txtContent: TextView = itemView.findViewById(R.id.description)
        val image: ImageView = itemView.findViewById(R.id.logo)
        var connection: Button = itemView.findViewById(R.id.button_connect)
    }
}