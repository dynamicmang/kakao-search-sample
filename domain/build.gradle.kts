plugins {
    id(Plugins.javaLibrary)
    id(Plugins.jetbrainJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(Libraries.pagingCommon)
    implementation(Libraries.javaxInject)
}