import java.util.Properties
import java.io.FileInputStream

        plugins {
            id("com.android.application")
            id("org.jetbrains.kotlin.android")
            id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"
        }

android {
    namespace = "com.mcandle.member"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mcandle.member"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }

        val localProps = Properties().apply {
            val localFile = rootProject.file("local.properties")
            if (localFile.exists()) {
                load(FileInputStream(localFile))
            }
        }

        buildConfigField("String", "SUPABASE_URL", "\"${localProps.getProperty("SUPABASE_URL") ?: ""}\"")
        buildConfigField("String", "SUPABASE_API_KEY", "\"${localProps.getProperty("SUPABASE_API_KEY") ?: ""}\"")
    }

    buildFeatures {

        buildConfig = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    testImplementation("junit:junit:4.13.2")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("io.github.jan-tennert.supabase:postgrest-kt:1.2.0")


    // ✅ Ktor HTTP client engine (OkHttp)
    implementation("io.ktor:ktor-client-okhttp:2.3.5")  // 버전은 supabase 버전에 맞춰 사용

    // 권장: Kotlin serialization과 충돌 방지를 위해 JSON 설정도 같이 추가
    implementation("io.ktor:ktor-client-content-negotiation:2.3.5")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5")

    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}