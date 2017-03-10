package com.example.libraries;

import java.util.HashMap;

/**
 * Created by Rahul on 3/9/2017.
 */
public class Tu {
	private static HashMap<String, IrregularConjugation> irregulars;

	public static String conjugate(String infinitive, ConjugatorHelper.VerbType verbType) {
		if (irregulars.containsKey(infinitive)) {
			return irregulars.get(infinitive).getConjugation(verbType);
		} else {
			if (verbType == ConjugatorHelper.VerbType.AFFIRMATIVE) {
				String builder = Presente.conjugate(infinitive, Presente.Subject.THIRD);
				if (ConjugatorHelper.getLastNumberOfLetters(infinitive, 2).equals("ar")) {
					builder = ConjugatorHelper.replaceLastOccurrence(builder, "o", "a");
				} else {
					builder = ConjugatorHelper.replaceLastOccurrence(builder, "o", "e");
				}

				return builder;
			} else {
				String builder = Presente.conjugate(infinitive, Presente.Subject.FIRST);

				if (ConjugatorHelper.getLastNumberOfLetters(infinitive, 2).equals("ar")) {
					builder = ConjugatorHelper.replaceLastOccurrence(builder, "o", "es");
				} else {
					builder = ConjugatorHelper.replaceLastOccurrence(builder, "o", "as");
				}

				return builder;
			}
		}
	}

	static {
		irregulars = new HashMap<>();

		irregulars.put("decir", new IrregularConjugation("di", "digas"));
		irregulars.put("hacer", new IrregularConjugation("haz", "hagas"));
		irregulars.put("ir", new IrregularConjugation("ve", "vayas"));
		irregulars.put("poner", new IrregularConjugation("pon", "pongas"));
		irregulars.put("salir", new IrregularConjugation("sal", "salgas"));
		irregulars.put("ser", new IrregularConjugation("sé", "seas"));
		irregulars.put("tener", new IrregularConjugation("ten", "tengas"));
		irregulars.put("venir", new IrregularConjugation("ven", "vengas"));
		irregulars.put("dar", new IrregularConjugation("da", "des"));
		irregulars.put("estar", new IrregularConjugation("está", "estés"));
		irregulars.put("saber", new IrregularConjugation("sabe", "sepas"));
	}

	private static class IrregularConjugation {
		private String affirmative;
		private String negative;

		public IrregularConjugation(String affirmative, String negative) {
			this.affirmative = affirmative;
			this.negative = negative;
		}

		public String getConjugation(ConjugatorHelper.VerbType verbType) {
			if (verbType == ConjugatorHelper.VerbType.AFFIRMATIVE) {
				return affirmative;
			} else {
				return negative;
			}
		}
	}
}
