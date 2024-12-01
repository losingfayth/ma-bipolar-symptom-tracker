package edu.bloomu.bipolarsymptomtracker.model

class Symptom(name: String, desc: String, symptomatic: Boolean) {
    private var name = name;
    private var desc = desc;
    private var symptomatic = symptomatic;

    constructor(name: String, desc: String)  : this(name, desc, false)

    fun toggleSymptomatic() { symptomatic = !symptomatic; }
    fun isSymptomatic(): Boolean { return symptomatic; }
    fun getName(): String { return name; }
    fun getDesc(): String { return desc; }
}