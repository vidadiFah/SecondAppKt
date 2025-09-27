plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    id("com.google.devtools.ksp") version "2.2.10-2.0.2" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.9.3" apply false
    id("com.google.gms.google-services") version "4.4.3" apply false
}

buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.9.1")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.9.3")
    }
}
