package edu.bloomu.bipolarsymptomtracker.model

class MonthlyAnalysis(
    val streakLength: Int = 0,
    val stateAnalysis: State = State.UNKNOWN,
    val stateStatus: StateStatus = StateStatus.UNKNOWN,
) {
}