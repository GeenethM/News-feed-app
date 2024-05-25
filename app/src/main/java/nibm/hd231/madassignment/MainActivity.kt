package nibm.hd231.madassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentContainer = R.id.fragmentContainer

        val fragManager: FragmentManager=supportFragmentManager

        val fragTransaction: FragmentTransaction = fragManager.beginTransaction()

        val fragment: FirstFragment = FirstFragment()

        fragTransaction.replace(fragmentContainer, fragment)

        fragTransaction.commit()
    }
}