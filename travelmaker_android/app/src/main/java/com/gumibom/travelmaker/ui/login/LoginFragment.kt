package com.gumibom.travelmaker.ui.login

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.icu.number.IntegerWidth
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.gumibom.travelmaker.BuildConfig
import com.gumibom.travelmaker.databinding.FragmentLoginBinding
import com.gumibom.travelmaker.ui.main.MainActivity
import com.gumibom.travelmaker.ui.signup.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "LoginFragment"

@AndroidEntryPoint
class LoginFragment  : Fragment(){
    private val REQ_ONE_TAP = 2  // Can be any integer unique to the Activity
    private var showOneTapUI = true

    private lateinit var oneTapClient: SignInClient

    private lateinit var signInRequest: BeginSignInRequest
    private var _binding :FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var activity:LoginActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as LoginActivity
    }
    private lateinit var auth: FirebaseAuth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        oneTapClient = Identity.getSignInClient(activity)
        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(BuildConfig.GOOGLE_CLIENT_ID)
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(true)
                    .build())
            .build()
        initGoogleLogin()
        googleLogin()
        testClickEvent()

    }
    private fun testClickEvent(){
        binding.btnTest.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onStart() {
        super.onStart()
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater,container,false);
        return binding.root

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun initGoogleLogin(){


    }
    private fun googleLogin(){
        binding.btnGoogleLogin.setOnClickListener {
            oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener(activity) { result ->
                    try {
                        val intentSender = result.pendingIntent.intentSender
                        startIntentSenderForResult(intentSender, REQ_ONE_TAP, null, 0, 0, 0, null)
                    } catch (e: IntentSender.SendIntentException) {
                        Log.e(TAG, "Couldn't start One Tap UI: ${e.localizedMessage}")
                    }
                }
                .addOnFailureListener(activity) { e ->
                    Log.d(TAG, e.localizedMessage)
                }
        }
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_ONE_TAP -> {
                try {
                    val credential = oneTapClient.getSignInCredentialFromIntent(data)
                    val idToken = credential.googleIdToken
                    when {
                        idToken != null -> {
                            // Got an ID token from Google. Use it to authenticate
                            // with your backend.
                            Log.d(TAG, "Got ID token.")
                        }

                        else -> {
                            // Shouldn't happen.
                            Log.d(TAG, "No ID token!")
                        }
                    }
                } catch (e: ApiException) {
                }
            }
        }

    }
}
