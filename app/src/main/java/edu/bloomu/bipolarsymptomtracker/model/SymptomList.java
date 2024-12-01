package edu.bloomu.bipolarsymptomtracker.model;

import android.content.Context;

import edu.bloomu.bipolarsymptomtracker.R;

public class SymptomList {
	public Symptom[] li;

	public SymptomList(Context context) {
		li = new Symptom[5];

		li[0] = new Symptom(context.getString(R.string.md_ref_symptom_temp1_title), context.getString(R.string.md_ref_symptom_temp1_desc));
		li[1] = new Symptom(context.getString(R.string.md_ref_symptom_temp2_title), context.getString(R.string.md_ref_symptom_temp2_desc));
		li[2] = new Symptom(context.getString(R.string.md_ref_symptom_temp3_title), context.getString(R.string.md_ref_symptom_temp3_desc));
		li[3] = new Symptom(context.getString(R.string.md_ref_symptom_temp4_title), context.getString(R.string.md_ref_symptom_temp4_desc));
		li[4] = new Symptom(context.getString(R.string.md_ref_symptom_temp5_title), context.getString(R.string.md_ref_symptom_temp5_desc));
	}
}
