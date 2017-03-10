package com.rahulramkumar.mandatoconjugator.libraries;

import java.util.HashMap;

import static com.rahulramkumar.mandatoconjugator.libraries.ConjugatorHelper.VerbType.AFFIRMATIVE;
import static com.rahulramkumar.mandatoconjugator.libraries.ConjugatorHelper.VerbType.NEGATIVE;
import static com.rahulramkumar.mandatoconjugator.libraries.ConjugatorHelper.replaceLastOccurrence;

/**
 * Created by Rahul on 3/5/2017.
 */
public class Vosotros {
	// Contains irregular negative mandatos (there are no irregular affirmative in vosotros)
	private static final HashMap<String, String> irregularMap;

	static {
		// Fill up irregulars
		irregularMap = new HashMap<>();
		irregularMap.put("ir", "vayáis");
		irregularMap.put("ser", "seais");
		irregularMap.put("saber", "sepáis");
		irregularMap.put("estar", "estéis");
		irregularMap.put("dar", "deis");
	}

	public static String conjugate(String infinitive, ConjugatorHelper.VerbType verbType) {
		// Have to check if it is an irregular first
		if (!(irregularMap.containsKey(infinitive) && verbType == NEGATIVE)) {
			if (verbType == AFFIRMATIVE) {
				// Affirmative vosotros verbs only need to replace the ending r with a d
				return replaceLastOccurrence(infinitive, "r", "d");
			} else {
				// Check the ending of the verb so we can switch it
				if (infinitive.endsWith("ar")) {
					return replaceLastOccurrence(infinitive, "ar", "éis");
				} else {
					// Ending could either be -er or -ir, so we need to find what it is to replace it
					return replaceLastOccurrence(infinitive, infinitive.substring(infinitive.length() - 2), "áis");
				}
			}
		} else {
			// If it is irregular, return the conjugation from the irregular hashmap
			return irregularMap.get(infinitive);
		}
	}
}
