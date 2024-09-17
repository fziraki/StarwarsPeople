package com.example.starwarspeople.utils

fun Int.toFeetAndInches(): String {
    // Constants for conversions
    val cmToInches = 0.393701
    val inchesPerFoot = 12

    // Convert cm to inches
    val totalInches = this * cmToInches

    // Calculate feet and remaining inches
    val feet = (totalInches / inchesPerFoot).toInt()
    val inches = (totalInches % inchesPerFoot).toInt()

    // Return formatted result
    return "$feet ft $inches in"
}