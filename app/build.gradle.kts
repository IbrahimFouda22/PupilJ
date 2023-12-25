plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.pupilJ.jk"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.pupilJ.jk"
        minSdk = 24
        targetSdk = 33
        versionCode = 77
        versionName = "1.7"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            debug {
                isDebuggable = true
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")

    //dotsIndicator
    implementation("com.tbuonomo:dotsindicator:5.0")
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    //coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")

    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    //encrypted sharedPref
    implementation ("androidx.security:security-crypto:1.1.0-alpha01")

    //piccaso
    implementation ("com.squareup.picasso:picasso:2.8")

    //splash scerrn
    implementation ("androidx.core:core-splashscreen:1.0.1")

    //pusher
    implementation ("com.pusher:pusher-java-client:2.2.5")
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")

    implementation(project(":domain"))
    implementation(project(":data"))
    //implementation("com.pusher:pusher-http-java:1.8.0")

}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}
