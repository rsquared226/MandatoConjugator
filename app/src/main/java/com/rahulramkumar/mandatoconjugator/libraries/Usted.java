package com.rahulramkumar.mandatoconjugator.libraries;

import java.util.HashMap;

/**
 * Created by Rahul on 3/9/2017.
 */
public class Usted {
	private static HashMap<String, IrregularConjugation> irregulars;
	public enum Type {SINGULAR, PLURAL}

	public static String conjugate(String infinitive, Type type) {
		if (irregulars.containsKey(infinitive)) {
			return irregulars.get(infinitive).getConjugation(type);
		} else {
			String builder = Presente.conjugate(infinitive, Presente.Subject.FIRST);

			if (ConjugatorHelper.getLastNumberOfLetters(infinitive, 2).equals("ar")) {
				builder = ConjugatorHelper.replaceLastOccurrence(builder, "o", "e");
			} else {
				builder = ConjugatorHelper.replaceLastOccurrence(builder, "o", "a");
			}
			if (type == Type.SINGULAR) {
				return builder;
			} else {
				return builder + "n";
			}
		}
	}

	static {
		irregulars = new HashMap<>();

		irregulars.put("dar", new IrregularConjugation("dé", "den"));
		irregulars.put("estar", new IrregularConjugation("esté", "estén"));
		irregulars.put("ir", new IrregularConjugation("vaya", "vayan"));
		irregulars.put("ser", new IrregularConjugation("sea", "sean"));
		irregulars.put("saber", new IrregularConjugation("sepa", "sepan"));
	}

	private static class IrregularConjugation {
		private String singular;
		private String plural;

		public IrregularConjugation(String singular, String plural) {
			this.singular = singular;
			this.plural = plural;
		}

		public String getConjugation(Type type) {
			if (type == Type.SINGULAR) {
				return singular;
			} else {
				return plural;
			}
		}
	}
}
