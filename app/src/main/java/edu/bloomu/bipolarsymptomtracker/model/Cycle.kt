package edu.bloomu.bipolarsymptomtracker.model

import edu.bloomu.bipolarsymptomtracker.db.Entry

/**
 * Takes the information from multiple entries and summarizes them
 *
 * @param state The overall state for this cycle
 * @param length The length of this cycle
 * @param meds How many times the user has missed their meds this cycle
 * @param drugs How many times the user has used illicit drugs this cycle
 */
class Cycle(
    private var state: State = State.UNKNOWN,
    var length: Int = 0,
    private var meds: Int = 0,
    private var drugs: Int = 0
) {
    /**
     * @param entry The entry to begin this cycle
     * @param length The length of this cycle
     */
    constructor(entry: Entry, length: Int = 0) :
            this(
                state = entry.state,
                meds = if (entry.meds) 1 else 0,
                drugs = if (entry.drugs) 1 else 0,
                length = length
            )

    /**
     * These variables help determine whether or not the State of this cycle should be re-categorized
     */
    private var hypoCount = if (state.isHypo()) 1 else 0
    private var fullCount = if (state.isFull()) 1 else 0
    private var neutralCount = if (state.isUnknownOrNeutral()) 1 else 0
    private var unstableCount = if (state == State.UNSTABLE) 1 else 0

    /**
     * @param entry An entry to be compiled into the cycle
     */
    fun iterate(entry: Entry) {

        // Keep track of re-categorization considerations
        if (entry.state.isHypo()) hypoCount++
        else fullCount++;
        if (entry.meds) meds++
        if (entry.drugs) drugs++

        updateState()

        length++
    }

    /**
     * @return whether this cycle is Unstable
     */
    fun isUnstable(): Boolean { return state == State.UNSTABLE }

    /**
     * @return whether this cycle is not Manic, Depressive, or their partners
     */
    private fun isOther(): Boolean { return isUnstable() || isUnknownOrNeutral() }

    /**
     * @return whether this cycle is Neutral or unable to be determined
     */
    private fun isUnknownOrNeutral(): Boolean
        { return state == State.UNKNOWN || state == State.NEUTRAL }

    /**
     * Re-categorizes this state on update
     */
    private fun updateState() {
        if (fullCount < (0.4 * length)) setStateIntensity(true)
        else setStateIntensity(false)
    }

    /**
     * @return the state category for this cycle
     */
    fun getState(): State {
        return if (this.state == State.NEUTRAL
                    || (neutralCount > (length * 0.7)   // If user is Neutral 70% of the time
                    && fullCount < (length * 0.3)))     // ...and not full 30+% of the time
                        State.NEUTRAL                   // ...they are in a Neutral state
        else if (unstableCount > (length * 0.4)) State.UNSTABLE // 40+% of the time, state is Unstable
        else state  // The tracked state
    }

    /**
     * @return if the user has been forgetting their meds (this may effect their state)
     */
    fun getMeds(): Boolean { return (meds > (0.33 * length) && length > 3) }

    /**
     * @return if the user has been taking illicit drugs (this may effect their state)
     */
    fun getDrugs(): Boolean { return (drugs > (0.33 * length) && length > 3) }

    /**
     * @return the state that categorizes this cycle, without the getState() calculation
     */
    private fun getBaseState(): State { return state }

    /**
     * @param isHypo Whether the state should be re-categorized as hypo
     */
    private fun setStateIntensity(isHypo: Boolean) {
        if (isHypo && state.isHypo() || !isHypo && state.isFull()) return
        else if (isHypo) {
            state = when (state) {
                State.FULL_MANIA -> State.HYPO_MANIA
                State.FULL_DEPRESSIVE -> State.HYPO_DEPRESSIVE
                State.UNKNOWN -> State.NEUTRAL
                else -> return
            }
        }
        else {  // Categorize as full
            state = when (state) {
                State.HYPO_MANIA -> State.FULL_MANIA
                State.HYPO_DEPRESSIVE -> State.FULL_DEPRESSIVE
                State.NEUTRAL -> State.UNKNOWN
                else -> return
            }
        }
    }

    /**
     * Groups cycles together so that a few entries that don't fall into Manic, Depressive, or
     * Unstable don't break up those larger cycles and make it appear that the user's cycle length
     * is shorter than it is
     *
     * @param cycle The cycle to try and group with this one
     * @return if the cycle was able to be grouped with this one
     */
    fun groupCycle(cycle: Cycle): Boolean {
        if (this.isOther() || !cycle.isOther() || this.state.isEnemy(cycle.state)) return false
        else {
            if (cycle.getBaseState().isUnknownOrNeutral()) this.neutralCount += cycle.length
            else if (cycle.getBaseState().isUnstable()) this.unstableCount += cycle.length
            this.length += cycle.length
            return true
        }
    }
}