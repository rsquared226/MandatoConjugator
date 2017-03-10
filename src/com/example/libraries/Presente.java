package com.example.libraries;

import java.util.HashMap;

import static com.example.libraries.ConjugatorHelper.ShoeVerbType.*;

/**
 * Created by Rahul on 3/8/2017.
 */
public class Presente {
	public enum Subject {FIRST, THIRD}
	private static final HashMap<String, String> irregulars;
	private static final HashMap<String, ConjugatorHelper.ShoeVerbType> shoeVerbs;

	public static String conjugate(String infinitive, Subject subject) {
		String infinitiveBuilder = infinitive;

		// If one of the extreme irregulars, take the conjugation from the map and return immediately
		if (subject == Subject.FIRST && irregulars.containsKey(infinitive)) {
			return irregulars.get(infinitive);
		}

		// For shoe verbs
		if (shoeVerbs.containsKey(infinitive)) {
			if (shoeVerbs.get(infinitive) == E_TO_IE) {
				infinitiveBuilder = ConjugatorHelper.replaceLastOccurrenceExceptEnding(infinitiveBuilder, "e", "ie");
			} else if (shoeVerbs.get(infinitive) == O_TO_UE) {
				infinitiveBuilder = ConjugatorHelper.replaceLastOccurrenceExceptEnding(infinitiveBuilder, "o", "ue");
			} else if (shoeVerbs.get(infinitive) == E_TO_I) {
				infinitiveBuilder = ConjugatorHelper.replaceLastOccurrenceExceptEnding(infinitiveBuilder, "e", "i");
			} else if (shoeVerbs.get(infinitive) == O_TO_HUE) {
				infinitiveBuilder = ConjugatorHelper.replaceLastOccurrenceExceptEnding(infinitiveBuilder, "o", "hue");
			} else if (shoeVerbs.get(infinitive) == U_TO_UE) {
				infinitiveBuilder = ConjugatorHelper.replaceLastOccurrenceExceptEnding(infinitiveBuilder, "u", "ue");
			}
		}

		// Change ending of the verb
		if (subject == Subject.FIRST &&
				(ConjugatorHelper.getLastNumberOfLetters(infinitive, 3).equals("cer") ||
				ConjugatorHelper.getLastNumberOfLetters(infinitive, 3).equals("cir"))) {
			infinitiveBuilder = specialCerCir(infinitiveBuilder);
		} else if (subject == Subject.FIRST &&
				(ConjugatorHelper.getLastNumberOfLetters(infinitive, 3).equals("ger") ||
				ConjugatorHelper.getLastNumberOfLetters(infinitive, 3).equals("gir"))) {
			infinitiveBuilder = specialGerGir(infinitiveBuilder);
		} else if (subject == Subject.FIRST &&
				(ConjugatorHelper.getLastNumberOfLetters(infinitive, 4).equals("guir"))) {
			infinitiveBuilder = specialGuir(infinitiveBuilder);
		} else if (ConjugatorHelper.getLastNumberOfLetters(infinitive, 3).equals("uir")) {
			infinitiveBuilder = specialUir(infinitiveBuilder);
		} else if (ConjugatorHelper.getLastNumberOfLetters(infinitive, 3).equals("iar") ||
				ConjugatorHelper.getLastNumberOfLetters(infinitive, 3).equals("uar")) {
			infinitiveBuilder = specialIarUar(infinitiveBuilder);
		}
		// This isn't right for present tense, but it's more convinient for the mandato conjugations
		else if (subject == Subject.FIRST &&
				(ConjugatorHelper.getLastNumberOfLetters(infinitive, 3).equals("car"))) {
			infinitiveBuilder = ConjugatorHelper.replaceLastOccurrence(infinitiveBuilder, "car", "quo");
		} else if (subject == Subject.FIRST &&
				(ConjugatorHelper.getLastNumberOfLetters(infinitive, 3).equals("gar"))) {
			infinitiveBuilder = ConjugatorHelper.replaceLastOccurrence(infinitiveBuilder, "gar", "guo");
		} else if (subject == Subject.FIRST &&
				(ConjugatorHelper.getLastNumberOfLetters(infinitive, 3).equals("zar"))) {
			infinitiveBuilder = ConjugatorHelper.replaceLastOccurrence(infinitiveBuilder, "zar", "co");
		}
		else {
			// For regular ending verbs
			infinitiveBuilder = ConjugatorHelper.replaceLastOccurrence(infinitiveBuilder,
					ConjugatorHelper.getLastNumberOfLetters(infinitiveBuilder, 2),
					"o");
		}

		return infinitiveBuilder;
	}

	static {
		// Fill all the irregulars up
		irregulars = new HashMap<>();

		// Los go-gos
		irregulars.put("caer", "caigo");
		irregulars.put("hacer", "hago");
		irregulars.put("poner", "pongo");
		irregulars.put("salir", "salgo");
		irregulars.put("traer", "traigo");
		irregulars.put("valer", "valgo");

		// Los yo-yos
		irregulars.put("decir", "digo");
		irregulars.put("oír", "oigo");
		irregulars.put("tener", "tengo");
		irregulars.put("venir", "vengo");
		irregulars.put("ir", "voy");
		irregulars.put("dar", "doy");
		irregulars.put("ser", "soy");
		irregulars.put("estar", "estoy");

		// Oh no!
		irregulars.put("caber", "quepo");
		irregulars.put("saber", "sé");
		irregulars.put("ver", "veo");
		irregulars.put("cocer", "cuezo");

		// Does rest using logic in a method below, no hardcoding needed


		// Fill all shoe verbs up
		shoeVerbs = new HashMap<>();

		shoeVerbs.put("atravesar", E_TO_IE);
		shoeVerbs.put("cerrar", E_TO_IE);
		shoeVerbs.put("comenzar", E_TO_IE);
		shoeVerbs.put("confesar", E_TO_IE);
		shoeVerbs.put("defender", E_TO_IE);
		shoeVerbs.put("despertar", E_TO_IE);
		shoeVerbs.put("empezar", E_TO_IE);
		shoeVerbs.put("encender", E_TO_IE);
		shoeVerbs.put("encerrar", E_TO_IE);
		shoeVerbs.put("entender", E_TO_IE);
		shoeVerbs.put("merendar", E_TO_IE);
		shoeVerbs.put("nevar", E_TO_IE);
		shoeVerbs.put("pensar", E_TO_IE);
		shoeVerbs.put("perder", E_TO_IE);
		shoeVerbs.put("quebrar", E_TO_IE);
		shoeVerbs.put("querer", E_TO_IE);
		shoeVerbs.put("recomendar", E_TO_IE);
		shoeVerbs.put("sentar", E_TO_IE);
		shoeVerbs.put("tener", E_TO_IE);

		shoeVerbs.put("acordar", O_TO_UE);
		shoeVerbs.put("acostar", O_TO_UE);
		shoeVerbs.put("almorzar", O_TO_UE);
		shoeVerbs.put("cocer", O_TO_UE);
		shoeVerbs.put("contar", O_TO_UE);
		shoeVerbs.put("costar", O_TO_UE);
		shoeVerbs.put("demostrar", O_TO_UE);
		shoeVerbs.put("devolver", O_TO_UE);
		shoeVerbs.put("doler", O_TO_UE);
		shoeVerbs.put("encontrar", O_TO_UE);
		shoeVerbs.put("envolver", O_TO_UE);
		shoeVerbs.put("jugar", U_TO_UE);
		shoeVerbs.put("llover", O_TO_UE);
		shoeVerbs.put("mostrar", O_TO_UE);
		shoeVerbs.put("oler", O_TO_HUE);
		shoeVerbs.put("poder", O_TO_UE);
		shoeVerbs.put("probar", O_TO_UE);
		shoeVerbs.put("recordar", O_TO_UE);
		shoeVerbs.put("resolver", O_TO_UE);
		shoeVerbs.put("revolver", O_TO_UE);
		shoeVerbs.put("rogar", O_TO_UE);
		shoeVerbs.put("soler", O_TO_UE);
		shoeVerbs.put("sonarse", O_TO_UE);
		shoeVerbs.put("torcer", O_TO_UE);
		shoeVerbs.put("tronar", O_TO_UE);
		shoeVerbs.put("volar", O_TO_UE);
		shoeVerbs.put("volver", O_TO_UE);

		shoeVerbs.put("advertir", E_TO_IE);
		shoeVerbs.put("convertir", E_TO_IE);
		shoeVerbs.put("divertir", E_TO_IE);
		shoeVerbs.put("hervir", E_TO_IE);
		shoeVerbs.put("mentir", E_TO_IE);
		shoeVerbs.put("preferir", E_TO_IE);
		shoeVerbs.put("referir", E_TO_IE);
		shoeVerbs.put("sentir", E_TO_IE);
		shoeVerbs.put("sugerir", E_TO_IE);
		shoeVerbs.put("venir", E_TO_IE);

		shoeVerbs.put("dormir", O_TO_UE);
		shoeVerbs.put("morir", O_TO_UE);

		shoeVerbs.put("corregir", E_TO_I);
		shoeVerbs.put("decir", E_TO_I);
		shoeVerbs.put("despedir", E_TO_I);
		shoeVerbs.put("elegir", E_TO_I);
		shoeVerbs.put("reñir", E_TO_I);
		shoeVerbs.put("freír", E_TO_I);
		shoeVerbs.put("gemir", E_TO_I);
		shoeVerbs.put("repetir", E_TO_I);
		shoeVerbs.put("impedir", E_TO_I);
		shoeVerbs.put("seguir", E_TO_I);
		shoeVerbs.put("medir", E_TO_I);
		shoeVerbs.put("pedir", E_TO_I);
		shoeVerbs.put("reír", E_TO_I);
		shoeVerbs.put("servir", E_TO_I);
		shoeVerbs.put("sonreír", E_TO_I);
		shoeVerbs.put("vestir", E_TO_I);
	}

	private static String specialCerCir(String infinitive) {
		if (ConjugatorHelper.isVowel(infinitive.charAt(infinitive.length() - 4))) {
			return ConjugatorHelper.replaceLastOccurrence(infinitive, ConjugatorHelper.getLastNumberOfLetters(infinitive, 3), "zco");
		} else {
			return ConjugatorHelper.replaceLastOccurrence(infinitive, ConjugatorHelper.getLastNumberOfLetters(infinitive, 3), "zo");
		}
	}

	private static String specialGerGir(String infinitive) {
		return ConjugatorHelper.replaceLastOccurrence(infinitive, ConjugatorHelper.getLastNumberOfLetters(infinitive, 3), "jo");
	}

	private static String specialGuir(String infinitive) {
		return ConjugatorHelper.replaceLastOccurrence(infinitive, "guir", "go");
	}

	private static String specialUir(String infinitive) {
		return ConjugatorHelper.replaceLastOccurrence(infinitive, "uir", "uyo");
	}

	private static String specialIarUar(String infinitive) {
		if (infinitive.charAt(infinitive.length() - 3) == 'i') {
			return ConjugatorHelper.replaceLastOccurrence(infinitive, "iar", "ío");
		} else {
			return ConjugatorHelper.replaceLastOccurrence(infinitive, "uar", "úo");
		}
	}
}
