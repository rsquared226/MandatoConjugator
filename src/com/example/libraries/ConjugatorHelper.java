package com.example.libraries;

/**
 * Created by Rahul on 3/8/2017.
 */
public class ConjugatorHelper {
	public enum VerbType {AFFIRMATIVE, NEGATIVE}
	public enum ShoeVerbType {E_TO_IE, O_TO_UE, E_TO_I, O_TO_HUE, U_TO_UE}

	public static String replaceLastOccurrence(String string, String substring, String replacement) {
		int index = string.lastIndexOf(substring);
		if (index == -1) {
			return string;
		}

		return string.substring(0, index) + replacement
				+ string.substring(index + substring.length());
	}

	public static String replaceLastOccurrenceExceptEnding(String string, String substring, String replacement) {
		// Save ending for later when necessary to be added back
		String ending = getLastNumberOfLetters(string, 2);

		// Take out the ending
		string = replaceLastOccurrence(string, getLastNumberOfLetters(string, 2), "");

		string = replaceLastOccurrence(string, substring, replacement);

		return string + ending; // Put everything together
	}

	public static String getLastNumberOfLetters(String string, int characters) {
		return string.substring(string.length() - characters);
	}

	public static boolean isVowel(char c) {
		return "aeiou".indexOf(c) != -1;
	}
}
