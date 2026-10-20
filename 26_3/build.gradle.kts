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
    test {
        java.srcDir("../common/src/test/java")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.3.build.+")
    testImplementation("io.papermc.paper:paper-api:26.3.build.+")
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
    testImplementation("org.mockito:mockito-core:5.20.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(25)
}

tasks {
    runServer {
        minecraftVersion("26.3")
        jvmArgs("-Xms2G", "-Xmx2G")
    }

    processResources {
        val props = mapOf(
            "version" to version,
            "description" to project.description,
            "apiVersion" to "26.3"
        )
        filesMatching("plugin.yml") { expand(props) }
    }
}

tasks.jar {
    archiveBaseName.set("deathBanPlugin")
    archiveClassifier.set("26.3")
    archiveVersion.set("")
}
tasks.test {
    useJUnitPlatform()
}
