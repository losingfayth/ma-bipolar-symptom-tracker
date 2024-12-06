package edu.bloomu.bipolarsymptomtracker.model

import edu.bloomu.bipolarsymptomtracker.ui.theme.Strings

/**
 * Initializes and collects all the symptoms that the user can log
 */
class Symptoms {

	var li: Array<Symptom>

	constructor() {
		li = arrayOf(
			Symptom(
				name = Strings.Model.Symptoms.Mania.Temper.title,
				desc = Strings.Model.Symptoms.Mania.Temper.body,
				indicates = State.FULL_MANIA
			),
			Symptom(
				name = Strings.Model.Symptoms.Mania.Energy.title,
				desc = Strings.Model.Symptoms.Mania.Energy.body,
				indicates = State.FULL_MANIA
			),
			Symptom(
				name = Strings.Model.Symptoms.Mania.Ego.title,
				desc = Strings.Model.Symptoms.Mania.Ego.body,
				indicates = State.FULL_MANIA
			),
			Symptom(
				name = Strings.Model.Symptoms.Mania.Sleep.title,
				desc = Strings.Model.Symptoms.Mania.Sleep.body,
				indicates = State.FULL_MANIA
			),
			Symptom(
				name = Strings.Model.Symptoms.Mania.Speech.title,
				desc = Strings.Model.Symptoms.Mania.Speech.body,
				indicates = State.FULL_MANIA
			),
			Symptom(
				name = Strings.Model.Symptoms.Mania.Racing.title,
				desc = Strings.Model.Symptoms.Mania.Racing.body,
				indicates = State.FULL_MANIA
			),
			Symptom(
				name = Strings.Model.Symptoms.Mania.Distract.title,
				desc = Strings.Model.Symptoms.Mania.Distract.body,
				indicates = State.FULL_MANIA
			),
			Symptom(
				name = Strings.Model.Symptoms.Mania.Reckless.title,
				desc = Strings.Model.Symptoms.Mania.Reckless.body,
				indicates = State.FULL_MANIA
			),
			Symptom(
				name = Strings.Model.Symptoms.Mania.Sex.title,
				desc = Strings.Model.Symptoms.Mania.Sex.body,
				indicates = State.FULL_MANIA
			),
			Symptom(
				name = Strings.Model.Symptoms.Mania.Money.title,
				desc = Strings.Model.Symptoms.Mania.Money.body,
				indicates = State.FULL_MANIA
			),
			Symptom(
				name = Strings.Model.Symptoms.Depression.Motivation.title,
				desc = Strings.Model.Symptoms.Depression.Motivation.body,
				indicates = State.FULL_DEPRESSIVE
			),
			Symptom(
				name = Strings.Model.Symptoms.Depression.Hobbies.title,
				desc = Strings.Model.Symptoms.Depression.Hobbies.body,
				indicates = State.FULL_DEPRESSIVE
			),
			Symptom(
				name = Strings.Model.Symptoms.Depression.Restless.title,
				desc = Strings.Model.Symptoms.Depression.Restless.body,
				indicates = State.FULL_DEPRESSIVE
			),
			Symptom(
				name = Strings.Model.Symptoms.Depression.Slow.title,
				desc = Strings.Model.Symptoms.Depression.Slow.body,
				indicates = State.FULL_DEPRESSIVE
			),
			Symptom(
				name = Strings.Model.Symptoms.Depression.Energy.title,
				desc = Strings.Model.Symptoms.Depression.Energy.body,
				indicates = State.FULL_DEPRESSIVE
			),
			Symptom(
				name = Strings.Model.Symptoms.Depression.Think.title,
				desc = Strings.Model.Symptoms.Depression.Think.body,
				indicates = State.FULL_DEPRESSIVE
			),
			Symptom(
				name = Strings.Model.Symptoms.Depression.Concentrate.title,
				desc = Strings.Model.Symptoms.Depression.Concentrate.body,
				indicates = State.FULL_DEPRESSIVE
			),
			Symptom(
				name = Strings.Model.Symptoms.Depression.Suicidal.title,
				desc = Strings.Model.Symptoms.Depression.Suicidal.body,
				indicates = State.FULL_DEPRESSIVE
			),
			Symptom(
				name = Strings.Model.Symptoms.Depression.Sex.title,
				desc = Strings.Model.Symptoms.Depression.Sex.body,
				indicates = State.FULL_DEPRESSIVE
			),
			Symptom(
				name = Strings.Model.Symptoms.Depression.Sleep.title,
				desc = Strings.Model.Symptoms.Depression.Sleep.body,
				indicates = State.FULL_DEPRESSIVE
			)
		)
	}

	/**
	 * Used when loading saved data into an entry
	 *
	 * @param symptoms An array of already initialized symptoms
	 */
	constructor(symptoms: Array<Symptom>) { li = symptoms }
}
