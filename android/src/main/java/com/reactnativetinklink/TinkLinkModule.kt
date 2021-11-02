package com.reactnativetinklink

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.facebook.react.bridge.*

import com.jakewharton.threetenabp.AndroidThreeTen
import com.tink.core.Tink
import com.tink.link.ui.CredentialsOperation
import com.tink.link.ui.LinkUser
import com.tink.link.ui.TinkLinkResult
import com.tink.link.ui.TinkLinkUiActivity
import com.tink.model.user.Scope
import com.tink.service.network.Environment
import com.tink.service.network.TinkConfiguration

class TinkLinkModule(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return "TinkLink"
  }

  init {
    AndroidThreeTen.init(reactContext);
  }

  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  @ReactMethod
  fun multiply(a: Int, b: Int, promise: Promise) {
    promise.resolve(a * b)
  }

  @ReactMethod
  fun displayTinkLink(a: String, b: String, promise: Promise) {

    val uriScheme = a.substringBefore("://")
    val uriHost = a.substringAfter("://")

    val config = TinkConfiguration(
        environment = Environment.Production, // Or define your own environment
        oAuthClientId = b, // clientId. Retrieve it from console.tink.com,
        redirectUri = Uri.Builder()
            .scheme("$uriScheme")
            .encodedAuthority("$uriHost")
            .build()
    )

    Tink.init(config, reactContext)

    val scopes = listOf(Scope.AccountsRead)
    val linkUser = LinkUser.TemporaryUser(market = "SE", locale = "sv_SE")
    val credentialsOperation = CredentialsOperation.Create()
    val intent = TinkLinkUiActivity.createIntent(
      context = reactContext,
      linkUser = linkUser,
      scopes = scopes,
      styleResId = R.style.TinkLinkUiStyle,
      credentialsOperation = credentialsOperation
    )

    reactContext.addActivityEventListener(mActivityEventListener(promise));
    reactContext.startActivityForResult(intent, REQUEST_CODE, null)
  }

  private fun mActivityEventListener(promise:Promise): ActivityEventListener = object : BaseActivityEventListener() {
    override fun onActivityResult(activity: Activity, requestCode: Int, resultCode: Int, data: Intent?) {
      super.onActivityResult(activity, requestCode, resultCode, data)
      if (requestCode == REQUEST_CODE) {
        when (resultCode) {
          TinkLinkUiActivity.RESULT_SUCCESS -> {
            when (val result = data?.extras?.getParcelable<TinkLinkResult>(TinkLinkUiActivity.RESULT_DATA)) {

              is TinkLinkResult.TemporaryUser -> {
                // Exchange the authorization code for an access token.
                val authorizationCode = result.authorizationCode
                promise.resolve(authorizationCode)
              }
            }
          }
          TinkLinkUiActivity.RESULT_CANCELLED -> { /* Handle cancellation */
            promise.reject("100","RESULT_CANCELLED")
          }
          TinkLinkUiActivity.RESULT_FAILURE -> { /* Handle error */
            promise.reject("100","RESULT_FAILURE")
          }
        }
      }
    }
  }


  companion object {
    private const val REQUEST_CODE = 100
  }
}
