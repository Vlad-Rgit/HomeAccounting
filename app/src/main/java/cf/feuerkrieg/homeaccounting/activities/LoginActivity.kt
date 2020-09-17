package cf.feuerkrieg.homeaccounting.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import cf.feuerkrieg.homeaccounting.R
import cf.feuerkrieg.homeaccounting.security.AuthHelper

class LoginActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        val authHelper = AuthHelper()

        //If user has already logged go to main activity
        if(authHelper.hasAuthenticated()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val toolbar = findViewById<Toolbar>(R.id.login_toolbar)
        navController = findNavController(R.id.nav_host_login_fragment)

        setSupportActionBar(toolbar)

       NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

}