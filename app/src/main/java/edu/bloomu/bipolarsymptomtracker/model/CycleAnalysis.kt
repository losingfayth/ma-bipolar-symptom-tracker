package edu.bloomu.bipolarsymptomtracker.model

/**
 * Stores the information collected by the Cycle in a way that the rest of the app can work with
 * more easily
 *
 * @param length The length of a cycle
 * @param state The state category of a cycle
 * @param position Where in their cycle the user (probably) is
 * @param drugs Whether the user took drugs a lot this cycle
 * @param meds Whether the user missed their meds a lot this cycle
 */
data class CycleAnalysis(
    val length: Int = 0,
    val state: State = State.UNKNOWN,
    val position: CyclePosition = CyclePosition.UNKNOWN,
    val drugs: Boolean = false,
    val meds: Boolean = false
)