package com.adbanao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val centerTextView = findViewById<TextView>(R.id.centerTextView)
        if(intent.data != null) {
            Firebase.dynamicLinks
                .getDynamicLink(intent)
                .addOnSuccessListener(this) { pendingDynamicLinkData ->
                    if (pendingDynamicLinkData != null) {
                        val deepLink = pendingDynamicLinkData.link
//                        val deepLinkPage = deepLink?.getQueryParameter("page")
                        centerTextView.text = deepLink?.toString()
                    }
                }
        }else
            centerTextView.text = "intent data is null"
    }
}