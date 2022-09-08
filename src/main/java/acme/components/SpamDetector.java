package acme.components;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import acme.entities.systemConfiguration.SystemConfiguration;

public class SpamDetector {

	public static Boolean isSpam(final String text, final SystemConfiguration systemConfiguration) {
		boolean result = false;

		final List<String> wordsChecking = SpamDetector.getWords(text);		
		final Map<String,Double> enTuples = SpamDetector.getSpamWords(systemConfiguration.getSpamTuplesEn());		
		final Map<String,Double> esTuples = SpamDetector.getSpamWords(systemConfiguration.getSpamTuplesEs());
		final Map<String,Double> spamTerms = new HashMap<>();
		spamTerms.putAll(enTuples);
		spamTerms.putAll(esTuples);

		final Double spamThreshold = systemConfiguration.getSpamThreshold();

		final Double spamRatio = SpamDetector.spam(wordsChecking, spamTerms);

		if(spamRatio >= spamThreshold) {
			result = true;
		}

		return result;
	}

	private static List<String> getWords(final String originalText){
		return Arrays.asList(originalText.replaceAll("[.,:;/*=|()¡!¿?{}`´<>]"," ").replace("\""," ").replace("\\"," ")
			.trim().split("\\s+"));		
	}

	private static Map<String,Double> getSpamWords(final String spamTuples){
		final Map<String,Double> spamWords = new HashMap<String,Double>();
		
		for(final String keyValue : spamTuples.split(",")) {
			final String[] pair = keyValue.replace("("," ").replace(")"," ").replace("'", "").trim().split(":");
			spamWords.put(pair[0], Double.valueOf(pair[1]));
			}

		return spamWords;
	}

	private static Double spam(final List<String> words, final Map<String,Double> spamTerms) {
		Double spamWeight = 0.;
		Integer palabrasDobles = 0;
		String palabraAnterior= "";
		
		for(final String word: words) {
			if(spamTerms.keySet().contains(word.toLowerCase())) {
				spamWeight += spamTerms.get(word.toLowerCase());
			}
			if(spamTerms.keySet().contains(palabraAnterior.toLowerCase() + " " + word.toLowerCase())) {
				spamWeight += spamTerms.get(palabraAnterior.toLowerCase() + " " + word.toLowerCase());
				palabrasDobles += 1;
			}
			palabraAnterior = word;
		}
		
		return (spamWeight/(words.size() - 1 * palabrasDobles)) * 10;
	}	
}