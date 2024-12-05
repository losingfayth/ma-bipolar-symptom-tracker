package edu.bloomu.bipolarsymptomtracker.model

class CycleAnalysis(
    val length: Int = 0,
    val state: State = State.UNKNOWN,
    val position: CyclePosition = CyclePosition.UNKNOWN,
) {
    constructor(cycle: Cycle, position: CyclePosition) :
            this(length = cycle.length, state = cycle.getState(), position = position)
}