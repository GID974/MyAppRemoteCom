package com.ryanburnsworth.nativesocketkommunication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myappremotecom.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private external fun init(hostname: String, port: Int): Int
    private external fun sendData(sock: Int, data: String)
    private external fun recvData(sock: Int): String
    private external fun disconnect(sock: Int)

    private var sock = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        completeConnection()
    }

    private fun completeConnection() {
        // run the remote TCP/IP calls on an IO thread
        GlobalScope.launch(Dispatchers.IO) {
            var incomingData = ""

            // change to your remote server IP and port
            sock = init("193.253.115.56", 9090)

            // send a hello to the remote server
            sendData(sock, "Hello Server!")

            // listen for incoming data
            while (incomingData == "") {
                incomingData = recvData(sock)
                Log.e("DATA", "Incoming: $incomingData")
            }

            // disconnect from the remote server
            disconnect(sock)
        }
    }

    // load the native library on initialization of this class
    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }
}