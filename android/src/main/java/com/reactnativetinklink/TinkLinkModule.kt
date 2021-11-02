package com.reactnativetinklink

import android.util.Log
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.jakewharton.threetenabp.AndroidThreeTen

class TinkLinkModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

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
    promise.resolve(a + b)
  }
}
