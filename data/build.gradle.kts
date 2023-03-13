import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id(Plugins.library)
    id(Plugins.jetbrain)
    id(Plugins.hilt)
    id(Plugins.kotlinKapt)
}

android {
    compileSdk = AppConfig.compileSdkVersion
    defaultConfig {
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.compileSdkVersion
        buildConfigField("String", "kakao_key", getApiKey("kakao.key"))
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(Libraries.hilt)
    kapt(Libraries.hiltCompiler)
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitGson)
    implementation(Libraries.okhttp)
    implementation(Libraries.okhttpInterceptor)
    implementation(Libraries.coroutinesCore)
    implementation(Libraries.pagingRuntime)
}


fun getApiKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}