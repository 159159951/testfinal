package com.appman.monocle

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.agm.monocle.model.AGMMonocleConfiguration
import com.agm.monocle.model.CopyrightWatermark
import com.agm.monocle.model.StampWatermark
import com.agm.monocle.opencv.CameraWithAutoCaptureActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import monocle.KotlinClass

class MainActivity : AppCompatActivity() {
    private val OPEN_CAMERA_REQ: Int = 100
    private lateinit var userConfigObject: AGMMonocleConfiguration;
    private lateinit var copyrightWatermark: CopyrightWatermark;
    private lateinit var stampWatermark: StampWatermark;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        userConfigObject = AGMMonocleConfiguration(
            null,
            false,
            null,
            false,
            -1
        )

        copyrightWatermark = CopyrightWatermark(
            "#e9d5e7",
            20f,
            30f,
            "#copyrightWatermark",
            "#eaff4f"
        )

        stampWatermark = StampWatermark(
            "#c4546f",
            "#050505",
            10f,
            "#stampWatermark",
            "#5530eb"

        )

        setupCameraView(0, true)
        setupCameraView(1, true)
        setupCameraView(2, true)
        setupCameraView(3, true)
        setupCameraView(4, true)

        fab.setOnClickListener { view ->
            Toast.makeText(this, KotlinClass().getStringRes(), Toast.LENGTH_SHORT).show()
            openCamera()
        }
    }

    private fun openCamera() {
        var cameraIntent = Intent(this, CameraWithAutoCaptureActivity::class.java);
        cameraIntent.putExtra("BUNDLE_USER_CONFIG", Gson().toJson(userConfigObject));
        startActivityForResult(cameraIntent, OPEN_CAMERA_REQ)
    }


    private fun setupCameraView(case: Int, isChecked: Boolean) {
        when (case) {
            //Water mark logo
            0 -> if (isChecked) {
                userConfigObject.waterMarkLogo = R.drawable.water_mark_icon
            } else {
                userConfigObject.waterMarkLogo = -1
            }
            //Policy Text
            1 -> if (isChecked) {
                userConfigObject.copyrightWatermark = copyrightWatermark
            } else {
                userConfigObject.copyrightWatermark = null
            }
            //Stamp Text
            2 -> if (isChecked) {
                userConfigObject.stampWatermark = stampWatermark
            } else {
                userConfigObject.stampWatermark = null
            }
            //Toggle Crop
            3 -> userConfigObject.isCropEnabled = isChecked
            4 -> userConfigObject.useDetection = isChecked
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
