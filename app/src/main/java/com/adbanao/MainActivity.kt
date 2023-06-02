package com.adbanao

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var centerTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        centerTextView = findViewById<TextView>(R.id.centerTextView)
        if(intent.data != null) {
            Firebase.dynamicLinks
                .getDynamicLink(intent.data!!)
                .addOnSuccessListener(this) { pendingDynamicLinkData ->
                    if (pendingDynamicLinkData != null) {
                        val deepLink = pendingDynamicLinkData.link
//                        val deepLinkPage = deepLink?.getQueryParameter("page")
                        centerTextView.text = deepLink?.toString()
                    }
                }
        }else
            centerTextView.text = "intent data is null"


        //try to get the value of some other link
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            getTheValueOfSecondLink()
        }
    }

    private fun getTheValueOfSecondLink(){
        Firebase.dynamicLinks
            .getDynamicLink(Uri.parse("https://adbanao.page.link/lottie_video"))
            .addOnSuccessListener(this) { pendingDynamicLinkData ->
                if (pendingDynamicLinkData != null) {
                    val deepLink = pendingDynamicLinkData.link
                    centerTextView.text = deepLink?.toString()
                }
            }
    }
}