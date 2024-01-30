package com.gumibom.travelmaker.ui.login

import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.tasks.Task
import com.gumibom.travelmaker.BuildConfig

import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.databinding.ActivityLoginBinding
import com.gumibom.travelmaker.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "LoginActivity"
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var navController : NavController


//    private lateinit var oneTapClient: SignInClient
    private val REQ_ONE_TAP = 2  // Can be any integer unique to the Activity
    private var showOneTapUI = true
//    private lateinit var signInRequest: BeginSignInRequest


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater).apply {
            navController = (supportFragmentManager.findFragmentById(R.id.fragment_container_login)
                    as NavHostFragment).navController
        }

        setContentView(binding.root)

//        oneTapClient = Identity.getSignInClient(this)
//        signInRequest = BeginSignInRequest.builder()
//            .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
//                .setSupported(true)
//                .build())
//            .setGoogleIdTokenRequestOptions(
//                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                    .setSupported(true)
//                    // Your server's client ID, not your Android client ID.
//                    .setServerClientId(BuildConfig.GOOGLE_CLIENT_ID)
//                    // Only show accounts previously used to sign in.
//                    .setFilterByAuthorizedAccounts(false)
//                    .build())
//            // Automatically sign in when exactly one credential is retrieved.
//            .setAutoSelectEnabled(true)
//            .build()
        // ...

//        googleLogin()

    }

    fun navigateToNextFragment(bundle : Bundle) {
        Log.d(TAG, "navigateToNextFragment: gdgd")
        when(navController.currentDestination?.id){
            R.id.loginFragment2-> navController.navigate(R.id.action_loginFragment2_to_findIdPwFragment, bundle)
            R.id.signupNicknameFragment->navController.navigate(R.id.action_signupNicknameFragment_to_signupLocationFragment)
        }
    }
//    private fun googleLogin(){
//        binding.btnGoogleLogin.setOnClickListener {
//
//            oneTapClient.beginSignIn(signInRequest)
//                .addOnSuccessListener(this) { result ->
//                    try {
//                        startIntentSenderForResult(
//                            result.pendingIntent.intentSender, REQ_ONE_TAP,
//                            null, 0, 0, 0, null)
//                    } catch (e: IntentSender.SendIntentException) {
//                        Log.e(TAG, "Couldn't start One Tap UI: ${e.localizedMessage}")
//                    }
//                }
//                .addOnFailureListener(this) { e ->
//                    // No saved credentials found. Launch the One Tap sign-up flow, or
//                    // do nothing and continue presenting the signed-out UI.
//                    Log.d(TAG, "asdf"+e.localizedMessage)
//
//                }
//        }
//    }



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
    // 메인 화면으로 넘어가는 함수
    fun moveMainActivity() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }
}