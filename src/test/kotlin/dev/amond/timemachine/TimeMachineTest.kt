package dev.amond.timemachine

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

import java.time.LocalDateTime
import java.time.LocalTime


class TimeMachineTest {
    val twoWeeksAgo = LocalDateTime.now().minusWeeks(2)
    val app = TimeMachineApp()

    @Test
    fun test() {
        val dateTime = LocalDateTime.of(2011, 1, 1, 1, 1)
        TimeMachine.timeTravelAt(LocalTime.of(11, 59));
        assertTrue(app.isAM());
        TimeMachine.timeTravelAt(LocalTime.of(1, 59));
        assertTrue(TimeMachine.timeOfNow().isBefore(LocalTime.of(3,0)))
    }



}

class TimeMachineApp {
    fun isAM(): Boolean {
        return TimeMachine.timeOfNow().isBefore(LocalTime.of(12, 0))
    }

    fun isBeforeAm3(time: LocalTime): Boolean {
        return time.isBefore(LocalTime.of(3, 0))
    }
}