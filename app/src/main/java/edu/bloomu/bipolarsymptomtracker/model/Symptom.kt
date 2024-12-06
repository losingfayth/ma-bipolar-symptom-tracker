package edu.bloomu.bipolarsymptomtracker.model

/**
 * Tracks whether or not the user is having a given symptom this entry
 *
 * @param name The name of the symptom
 * @param desc The description of the symptom
 * @param state What State this symptom usually indicates
 * @param checked Whether the user logged this symptom
 */
class Symptom(
    private val name: String,
    private val desc: String,
    private val state: State,
    private var checked: Boolean
) {

    constructor(
        name: String,
        desc: String,
        indicates: State
    ) : this(name, desc, indicates, false)

    /**
     * Toggles whether the user logged this symptom
     */
    fun toggleChecked() { checked = !checked; }

    /**
     * @return whether the user logged this symptom
     */
    fun isChecked(): Boolean { return checked; }

    /**
     * @return the name of this symptom
     */
    fun getName(): String { return name; }

    /**
     * @return the description for this symptom
     */
    fun getDesc(): String { return desc; }

    /**
     * @return the State this symptom is usually associated with
     */
    fun getState(): State { return state }
}