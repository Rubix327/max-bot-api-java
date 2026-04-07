plugins {
    kotlin("jvm") version "2.1.0"
    id("com.gradleup.shadow") version "8.3.5"
}

dependencies {
    implementation(project(":max-bot-api-client"))
    implementation(project(":max-bot-api-core"))
    implementation(project(":max-bot-api-jackson"))
    implementation(project(":max-bot-api-spring-boot"))
    implementation(project(":max-bot-api-webhook"))
    implementation(project(":max-bot-api-longpolling"))
}

tasks.shadowJar {
    archiveClassifier.set("all")

    // убираем лишние файлы
    mergeServiceFiles()
}