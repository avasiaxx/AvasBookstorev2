package com.example.storeapp.domain

import java.time.LocalDateTime

interface DateFormatter {
    fun formatDate(date: LocalDateTime): String
}