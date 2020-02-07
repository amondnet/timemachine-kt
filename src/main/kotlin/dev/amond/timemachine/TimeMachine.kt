package dev.amond.timemachine

import java.time.*


object TimeMachine {
    @JvmStatic
    private var clock: Clock = Clock.systemDefaultZone()
    @JvmStatic
    private val zoneId = ZoneId.systemDefault()

    @JvmStatic
    fun dateTimeOfNow(): LocalDateTime {
        return LocalDateTime.now(clock)
    }

    @JvmStatic
    fun timeOfNow(): LocalTime {
        return LocalTime.now(clock)
    }

    @JvmStatic
    fun dateOfNow(): LocalDate {
        return LocalDate.now(clock)
    }

    @JvmStatic
    fun useFixedClockAt(date: LocalDateTime) {
        clock = Clock.fixed(date.atZone(zoneId).toInstant(), zoneId)
    }

    @JvmStatic
    fun useSystemDefaultZoneClock() {
        clock = Clock.systemDefaultZone()
    }

    @JvmStatic
    fun useUtcZone() {
        clock = Clock.systemUTC()
    }

    @JvmStatic
    fun timeTravelAt(dateTime: LocalDateTime) {
        useFixedClockAt(dateTime)
    }

    @JvmStatic
    fun timeTravelAt(time: LocalTime) {
        useFixedClockAt(time.atDate(LocalDate.now()))
    }

    @JvmStatic
    fun timeTravelAt(date: LocalDate) {
        useFixedClockAt(date.atStartOfDay())
    }

    @JvmStatic
    fun reset() {
        clock = Clock.systemDefaultZone()
    }
}