package edu.bloomu.bipolarsymptomtracker.model

class Cycle(
    private var state: State = State.UNKNOWN,
    var length: Int = 0
) {
    private var hypoCount = if (state.isHypo()) 1 else 0
    private var fullCount = if (state.isFull()) 1 else 0
    private var neutralCount = if (state.isUnknownOrNeutral()) 1 else 0
    private var unstableCount = if (state == State.UNSTABLE) 1 else 0

    fun iterate(state: State) { if (state.isHypo()) hypoCount++ else fullCount++; updateState() }

    private fun isOther(): Boolean { return isUnstable() || isUnknownOrNeutral() }
    fun isUnstable(): Boolean { return state == State.UNSTABLE }
    fun isUnknownOrNeutral(): Boolean { return state == State.UNKNOWN || state == State.NEUTRAL }

    private fun updateState() {
        if (fullCount < (0.4 * length)) setStateHypo(true)
        else setStateHypo(false)
    }

    fun getState(): State {
        return if (this.state == State.NEUTRAL
                    || (neutralCount > (length * 0.7)
                    && fullCount < (length * 0.3)))
                        State.NEUTRAL
        else if (unstableCount > (length * 4)) State.UNSTABLE
        else state
    }

    private fun getBaseState(): State { return state }

    private fun setStateHypo(isHypo: Boolean) {
        if (isHypo && state.isHypo() || !isHypo && state.isFull()) return
        else if (isHypo) {
            state = when (state) {
                State.FULL_MANIC -> State.HYPO_MANIC
                State.FULL_DEPRESSIVE -> State.HYPO_DEPRESSIVE
                State.UNKNOWN -> State.NEUTRAL
                else -> return
            }
        }
        else {
            state = when (state) {
                State.HYPO_MANIC -> State.FULL_MANIC
                State.HYPO_DEPRESSIVE -> State.FULL_DEPRESSIVE
                State.NEUTRAL -> State.UNKNOWN
                else -> return
            }
        }
    }

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