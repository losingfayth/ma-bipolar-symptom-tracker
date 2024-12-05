package edu.bloomu.bipolarsymptomtracker.model

import edu.bloomu.bipolarsymptomtracker.ui.theme.Strings

class Symptoms {

	var li: Array<Symptom>

	constructor() {
		li = arrayOf(
			Symptom(
				name = Strings.Symptoms.Mania.Temper.Title,
				desc = Strings.Symptoms.Mania.Temper.Desc,
				indicates = State.FULL_MANIC
			),
			Symptom(
				name = Strings.Symptoms.Mania.Energy.Title,
				desc = Strings.Symptoms.Mania.Energy.Desc,
				indicates = State.FULL_MANIC
			),
			Symptom(
				name = Strings.Symptoms.Mania.Ego.Title,
				desc = Strings.Symptoms.Mania.Ego.Desc,
				indicates = State.FULL_MANIC
			),
			Symptom(
				name = Strings.Symptoms.Mania.Sleep.Title,
				desc = Strings.Symptoms.Mania.Sleep.Desc,
				indicates = State.FULL_MANIC
			),
			Symptom(
				name = Strings.Symptoms.Mania.Speech.Title,
				desc = Strings.Symptoms.Mania.Speech.Desc,
				indicates = State.FULL_MANIC
			),
			Symptom(
				name = Strings.Symptoms.Mania.Racing.Title,
				desc = Strings.Symptoms.Mania.Racing.Desc,
				indicates = State.FULL_MANIC
			),
			Symptom(
				name = Strings.Symptoms.Mania.Distract.Title,
				desc = Strings.Symptoms.Mania.Distract.Desc,
				indicates = State.FULL_MANIC
			),
			Symptom(
				name = Strings.Symptoms.Mania.Reckless.Title,
				desc = Strings.Symptoms.Mania.Reckless.Desc,
				indicates = State.FULL_MANIC
			),
			Symptom(
				name = Strings.Symptoms.Mania.Sex.Title,
				desc = Strings.Symptoms.Mania.Sex.Desc,
				indicates = State.FULL_MANIC
			),
			Symptom(
				name = Strings.Symptoms.Mania.Money.Title,
				desc = Strings.Symptoms.Mania.Money.Desc,
				indicates = State.FULL_MANIC
			),
			Symptom(
				name = Strings.Symptoms.Depression.Motivation.Title,
				desc = Strings.Symptoms.Depression.Motivation.Desc,
				indicates = State.FULL_DEPRESSIVE
			),
			Symptom(
				name = Strings.Symptoms.Depression.Hobbies.Title,
				desc = Strings.Symptoms.Depression.Hobbies.Desc,
				indicates = State.FULL_DEPRESSIVE
			),
			Symptom(
				name = Strings.Symptoms.Depression.Restless.Title,
				desc = Strings.Symptoms.Depression.Restless.Desc,
				indicates = State.FULL_DEPRESSIVE
			),
			Symptom(
				name = Strings.Symptoms.Depression.Slow.Title,
				desc = Strings.Symptoms.Depression.Slow.Desc,
				indicates = State.FULL_DEPRESSIVE
			),
			Symptom(
				name = Strings.Symptoms.Depression.Energy.Title,
				desc = Strings.Symptoms.Depression.Energy.Desc,
				indicates = State.FULL_DEPRESSIVE
			),
			Symptom(
				name = Strings.Symptoms.Depression.Think.Title,
				desc = Strings.Symptoms.Depression.Think.Desc,
				indicates = State.FULL_DEPRESSIVE
			),
			Symptom(
				name = Strings.Symptoms.Depression.Concentrate.Title,
				desc = Strings.Symptoms.Depression.Concentrate.Desc,
				indicates = State.FULL_DEPRESSIVE
			),
			Symptom(
				name = Strings.Symptoms.Depression.Suicidal.Title,
				desc = Strings.Symptoms.Depression.Suicidal.Desc,
				indicates = State.FULL_DEPRESSIVE
			),
			Symptom(
				name = Strings.Symptoms.Depression.Sex.Title,
				desc = Strings.Symptoms.Depression.Sex.Desc,
				indicates = State.FULL_DEPRESSIVE
			),
			Symptom(
				name = Strings.Symptoms.Depression.Sleep.Title,
				desc = Strings.Symptoms.Depression.Sleep.Desc,
				indicates = State.FULL_DEPRESSIVE
			)
		)
	}

	constructor(symptoms: Array<Symptom>) {
		li = symptoms
	}
}
