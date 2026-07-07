plugins {
    id("java-library")
    id("xyz.jpenilla.run-paper") version "3.0.2"
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

sourceSets {
    main {
        java.srcDir("../common/src/main/java")
        resources.srcDir("../common/src/main/resources")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.1.2.build.+")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(25)
}

tasks {
    runServer {
        minecraftVersion("26.1.2")
        jvmArgs("-Xms2G", "-Xmx2G")
    }
    processResources {
        val props = mapOf(
            "version" to version,
            "description" to project.description,
            "apiVersion" to "26.1"
        )
        filesMatching("plugin.yml") { expand(props) }
    }
}

tasks.jar {
    archiveBaseName.set("deathBanPlugin")
    archiveClassifier.set("26.1")
    archiveVersion.set("")
}