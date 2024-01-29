package com.gumibom.travelmaker.ui.login

import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.tasks.Task
import com.gumibom.travelmaker.BuildConfig
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "LoginActivity"
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var navController : NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater).apply {
            navController = (supportFragmentManager.findFragmentById(R.id.fragment_container_login)
                    as NavHostFragment).navController
        }

        setContentView(binding.root)

    }



//    private fun googleSignOut(){
//        binding.btnTestSignOut.setOnClickListener {
//            googleSignInClient.signOut()
//    clearCredentialState()
//        }
//    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//            if (requestCode == RC_SIGN_IN) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            handleSignInResult(task)
//        }
//    }
//
//    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
//        try {
//            val account = completedTask.getResult(ApiException::class.java)
//            updateUI(account)
//        } catch (e: ApiException) {
//            // The ApiException status code indicates the detailed failure reason.
//            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
//            updateUI(null)
//        }
//    }
//
//    private fun updateUI(account: GoogleSignInAccount?) {
//        if (account != null) {
//            // User is signed in
//            // You can access account information or proceed with the authenticated user
//            Toast.makeText(this,"account${account}",Toast.LENGTH_SHORT).show()
//        } else {
//            // User is not signed in
//            // Show the sign-in button or handle sign-in failure
//            Toast.makeText(this,"account${account}",Toast.LENGTH_SHORT).show()
//        }
//    }

}