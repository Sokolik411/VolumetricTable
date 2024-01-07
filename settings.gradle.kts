pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "VolumetricTable"
include(":app")
include(":core")
include(":core_api")
include(":feature_gost")
include(":feature_density")
include(":feature_volume")
