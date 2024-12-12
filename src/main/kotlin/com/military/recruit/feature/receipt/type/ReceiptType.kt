package com.military.recruit.feature.receipt.type

enum class ReceiptType(
    val type: String,
    val cacheName: String
) {
    ARMY("육군", "ARMY"),
    NAVY("해군", "NAVY"),
    MARINE_CORPS("해병", "MARINE_CORPS"),
    AIR_FORCE("공군", "AIR_FORCE")
}