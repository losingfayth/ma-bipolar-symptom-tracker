package edu.bloomu.bipolarsymptomtracker.model

class Symptom(
    private val name: String,
    private val desc: String,
    private val indicates: State,
    private var symptomatic: Boolean) {

    constructor(
        name: String,
        desc: String,
        indicates: State)  : this(name, desc, indicates, false)

    fun toggleSymptomatic() { symptomatic = !symptomatic; }
    fun isSymptomatic(): Boolean { return symptomatic; }
    fun getName(): String { return name; }
    fun getDesc(): String { return desc; }
    fun getIndicates(): State { return indicates }
}