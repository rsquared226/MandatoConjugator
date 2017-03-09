package com.example.libraries;

/**
 * Created by Rahul on 3/8/2017.
 */
public class ConjugatorHelper {
	public enum VerbType {AFFIRMATIVE, NEGATIVE}

	public static String replaceLastOccurrence(String string, String substring, String replacement) {
		int index = string.lastIndexOf(substring);
		if (index == -1) {
			return string;
		}

		return string.substring(0, index) + replacement
				+ string.substring(index + substring.length());
	}
}
