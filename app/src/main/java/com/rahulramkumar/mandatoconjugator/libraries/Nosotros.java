package com.rahulramkumar.mandatoconjugator.libraries;

import java.util.HashMap;

/**
 * Created by Rahul on 3/8/2017.
 */
public class Nosotros {
	private static HashMap<String, String> irregulars;

	public static String conjugate(String infinitive, ConjugatorHelper.VerbType verbType) {
		// Because ir is a special snowflake
		if (infinitive.equals("ir")) {
			if (verbType == ConjugatorHelper.VerbType.AFFIRMATIVE) {
				return "vamos";
			} else {
				return "vayamos";
			}
		}

		if (irregulars.containsKey(infinitive)) {
			return irregulars.get(infinitive);
		}

		String builder = infinitive;

		// -ir zapatilla
		if (infinitive.endsWith("ir") && Presente.getShoeVerbsMap().containsKey(infinitive)) {
			if (Presente.getShoeVerbsMap().get(infinitive) == ConjugatorHelper.ShoeVerbType.O_TO_UE) {
				builder = ConjugatorHelper.replaceLastOccurrenceExceptEnding(infinitive,"o", "u");
			} else if (Presente.getShoeVerbsMap().get(infinitive) == ConjugatorHelper.ShoeVerbType.E_TO_I ||
					Presente.getShoeVerbsMap().get(infinitive) == ConjugatorHelper.ShoeVerbType.E_TO_IE) {
				builder = ConjugatorHelper.replaceLastOccurrenceExceptEnding(infinitive, "e", "i");
			}
		}

		// Change ending of the verb
		if (ConjugatorHelper.getLastNumberOfLetters(infinitive, 3).equals("cer") ||
				ConjugatorHelper.getLastNumberOfLetters(infinitive, 3).equals("cir")) {
			if (ConjugatorHelper.isVowel(infinitive.charAt(infinitive.length() - 4))) {
				builder = ConjugatorHelper.replaceLastOccurrence(builder, "c", "zc");
			} else {
				builder = ConjugatorHelper.replaceLastOccurrence(builder, "c", "z");
			}
		} else if (ConjugatorHelper.getLastNumberOfLetters(infinitive, 3).equals("ger") ||
				ConjugatorHelper.getLastNumberOfLetters(infinitive, 3).equals("gir")) {
			builder = ConjugatorHelper.replaceLastOccurrence(builder, "g", "j");
		} else if (ConjugatorHelper.getLastNumberOfLetters(infinitive, 4).equals("guir")) {
			builder = ConjugatorHelper.replaceLastOccurrence(builder, "gu", "g");
		} else if (ConjugatorHelper.getLastNumberOfLetters(infinitive, 3).equals("uir")) {
			builder = ConjugatorHelper.replaceLastOccurrence(builder, "u", "uy");
		} else if (ConjugatorHelper.getLastNumberOfLetters(infinitive, 3).equals("iar") ||
				ConjugatorHelper.getLastNumberOfLetters(infinitive, 3).equals("uar")) {
			if (infinitive.charAt(infinitive.length() - 3) == 'i') {
				builder = ConjugatorHelper.replaceLastOccurrence(builder, "i", "í");
			} else {
				builder = ConjugatorHelper.replaceLastOccurrence(builder, "u", "ú");
			}
		}
		// This isn't right for present tense, but it's more convinient for the mandato conjugations
		else if (ConjugatorHelper.getLastNumberOfLetters(infinitive, 3).equals("car")) {
			builder = ConjugatorHelper.replaceLastOccurrence(builder, "c", "qu");
		} else if (ConjugatorHelper.getLastNumberOfLetters(infinitive, 3).equals("gar")) {
			builder = ConjugatorHelper.replaceLastOccurrence(builder, "g", "gu");
		} else if (ConjugatorHelper.getLastNumberOfLetters(infinitive, 3).equals("zar")) {
			builder = ConjugatorHelper.replaceLastOccurrence(builder, "z", "c");
		}

		if (infinitive.endsWith("ar")) {
			builder = ConjugatorHelper.replaceLastOccurrence(builder, "ar", "emos");
		} else {
			builder = ConjugatorHelper.replaceLastOccurrence(builder, ConjugatorHelper.getLastNumberOfLetters(infinitive, 2), "amos");
		}

		return builder;
	}

	static {
		irregulars = new HashMap<>();

		irregulars.put("haber", "hayamos");
		irregulars.put("saber", "sepamos");
		irregulars.put("dar", "demos");
		irregulars.put("ser", "seamos");
		irregulars.put("estar", "estemos");
		irregulars.put("ver", "veamos");
	}
}

