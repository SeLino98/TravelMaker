package com.gumibom.travelmaker.ui.login

import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
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
    private lateinit var oneTapClient: SignInClient
    private val REQ_ONE_TAP = 2  // Can be any integer unique to the Activity
    private var showOneTapUI = true
    private lateinit var signInRequest: BeginSignInRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater).apply {
            navController = (supportFragmentManager.findFragmentById(R.id.fragment_container_login)
                    as NavHostFragment).navController
        }
        setContentView(binding.root)
        oneTapClient = Identity.getSignInClient(this)
        signInRequest = BeginSignInRequest.builder()
            .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
                .setSupported(true)
                .build())
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(BuildConfig.GOOGLE_CLIENT_ID)
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(false)
                    .build())
            // Automatically sign in when exactly one credential is retrieved.
            .setAutoSelectEnabled(true)
            .build()
        // ...

        googleLogin()
    }
    private fun googleLogin(){
        binding.btnGoogleLogin.setOnClickListener {
            
            oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener(this) { result ->
                    try {
                        startIntentSenderForResult(
                            result.pendingIntent.intentSender, REQ_ONE_TAP,
                            null, 0, 0, 0, null)
                    } catch (e: IntentSender.SendIntentException) {
                        Log.e(TAG, "Couldn't start One Tap UI: ${e.localizedMessage}")
                    }
                }
                .addOnFailureListener(this) { e ->
                    // No saved credentials found. Launch the One Tap sign-up flow, or
                    // do nothing and continue presenting the signed-out UI.
                    Log.d(TAG, "asdf"+e.localizedMessage)

                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_ONE_TAP -> {
                try {
                    // ...
                } catch (e: ApiException) {
                    when (e.statusCode) {
                        CommonStatusCodes.CANCELED -> {
                            Log.d(TAG, "One-tap dialog was closed.")
                            // Don't re-prompt the user.
                            showOneTapUI = false
                        }
                        CommonStatusCodes.NETWORK_ERROR -> {
                            Log.d(TAG, "One-tap encountered a network error.")
                            // Try again or just ignore.
                        }
                        else -> {
                            Log.d(TAG, "Couldn't get credential from result." +
                                    " (${e.localizedMessage})")
                        }
                    }
                }
            }
        }
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