import org.jetbrains.kotlin.gradle.plugin.extraProperties

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.gradle.plugin) apply false
    alias(libs.plugins.jetbrains.android.kotlin) apply false
}
