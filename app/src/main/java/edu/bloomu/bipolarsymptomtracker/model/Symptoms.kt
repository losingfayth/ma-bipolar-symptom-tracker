package edu.bloomu.bipolarsymptomtracker.model;

import android.content.Context;

import edu.bloomu.bipolarsymptomtracker.R;

public class Symptoms {
	public Symptom[] li;

	public Symptoms(Context context) {
		li = new Symptom[20];

		li[0] = new Symptom(
				context.getString(R.string.md_ref_symptom_mania_temper_title),
				context.getString(R.string.md_ref_symptom_mania_temper_desc),
				State.MANIC);
		li[1] = new Symptom(
				context.getString(R.string.md_ref_symptom_mania_energy_title),
				context.getString(R.string.md_ref_symptom_mania_energy_desc),
				State.MANIC);
		li[2] = new Symptom(
				context.getString(R.string.md_ref_symptom_mania_ego_title),
				context.getString(R.string.md_ref_symptom_mania_ego_desc),
				State.MANIC);
		li[3] = new Symptom(
				context.getString(R.string.md_ref_symptom_mania_sleep_title),
				context.getString(R.string.md_ref_symptom_mania_sleep_desc),
				State.MANIC);
		li[4] = new Symptom(
				context.getString(R.string.md_ref_symptom_mania_speech_title),
				context.getString(R.string.md_ref_symptom_mania_speech_desc),
				State.MANIC);
		li[5] = new Symptom(
				context.getString(R.string.md_ref_symptom_mania_racing_title),
				context.getString(R.string.md_ref_symptom_mania_racing_desc),
				State.MANIC);
		li[6] = new Symptom(
				context.getString(R.string.md_ref_symptom_mania_distract_title),
				context.getString(R.string.md_ref_symptom_mania_distract_desc),
				State.MANIC);
		li[7] = new Symptom(
				context.getString(R.string.md_ref_symptom_mania_reckless_title),
				context.getString(R.string.md_ref_symptom_mania_reckless_desc),
				State.MANIC);
		li[8] = new Symptom(
				context.getString(R.string.md_ref_symptom_mania_sex_title),
				context.getString(R.string.md_ref_symptom_mania_sex_desc),
				State.MANIC);
		li[9] = new Symptom(
				context.getString(R.string.md_ref_symptom_mania_money_title),
				context.getString(R.string.md_ref_symptom_mania_money_desc),
				State.MANIC);
		li[10] = new Symptom(
				context.getString(R.string.md_ref_symptom_depression_motivation_title),
				context.getString(R.string.md_ref_symptom_depression_motivation_desc),
				State.DEPRESSIVE);
		li[11] = new Symptom(
				context.getString(R.string.md_ref_symptom_depression_hobbies_title),
				context.getString(R.string.md_ref_symptom_depression_hobbies_desc),
				State.DEPRESSIVE);
		li[12] = new Symptom(
				context.getString(R.string.md_ref_symptom_depression_sleep_title),
				context.getString(R.string.md_ref_symptom_depression_sleep_desc),
				State.DEPRESSIVE);
		li[13] = new Symptom(
				context.getString(R.string.md_ref_symptom_depression_restless_title),
				context.getString(R.string.md_ref_symptom_depression_restless_desc),
				State.DEPRESSIVE);
		li[14] = new Symptom(
				context.getString(R.string.md_ref_symptom_depression_slow_title),
				context.getString(R.string.md_ref_symptom_depression_slow_desc),
				State.DEPRESSIVE);
		li[15] = new Symptom(
				context.getString(R.string.md_ref_symptom_depression_energy_title),
				context.getString(R.string.md_ref_symptom_depression_energy_desc),
				State.DEPRESSIVE);
		li[16] = new Symptom(
				context.getString(R.string.md_ref_symptom_depression_think_title),
				context.getString(R.string.md_ref_symptom_depression_think_desc),
				State.DEPRESSIVE);
		li[17] = new Symptom(
				context.getString(R.string.md_ref_symptom_depression_concentrate_title),
				context.getString(R.string.md_ref_symptom_depression_concentrate_desc),
				State.DEPRESSIVE);
		li[18] = new Symptom(
				context.getString(R.string.md_ref_symptom_depression_suicidal_title),
				context.getString(R.string.md_ref_symptom_depression_suicidal_desc),
				State.DEPRESSIVE);
		li[19] = new Symptom(
				context.getString(R.string.md_ref_symptom_depression_sex_title),
				context.getString(R.string.md_ref_symptom_depression_sex_desc),
				State.DEPRESSIVE);
	}

	public Symptoms(Symptom[] symptoms) {
		li = symptoms;
	}
}
