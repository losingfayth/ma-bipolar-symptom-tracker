package edu.bloomu.bipolarsymptomtracker.model

class Symptom(
    private val name: String,
    private val desc: String,
    private val state: State,
    private var checked: Boolean) {

    constructor(
        name: String,
        desc: String,
        indicates: State
    ) : this(name, desc, indicates, false)

    fun toggleChecked() { checked = !checked; }
    fun isChecked(): Boolean { return checked; }
    fun getName(): String { return name; }
    fun getDesc(): String { return desc; }
    fun getState(): State { return state }
}