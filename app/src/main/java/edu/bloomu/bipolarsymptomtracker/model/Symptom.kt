package edu.bloomu.bipolarsymptomtracker.model

class Symptom(name: String, desc: String, indicates: State, symptomatic: Boolean) {
    private var name = name;
    private var desc = desc;
    private var indicates = indicates
    private var symptomatic = symptomatic;

    constructor(name: String, desc: String, indicates: State)  : this(name, desc, indicates, false)

    fun toggleSymptomatic() { symptomatic = !symptomatic; }
    fun isSymptomatic(): Boolean { return symptomatic; }
    fun getName(): String { return name; }
    fun getDesc(): String { return desc; }
    fun getIndicates(): State { return indicates }
}