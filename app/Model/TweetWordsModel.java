package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.stream.Collectors;
import static java.util.Comparator.reverseOrder;


public class TweetWordsModel {

	public static Map<String, Long> tweetWords(ArrayNode tweetList) {

		List<String>tweetTexts = new ArrayList<String>();

		for (JsonNode on: tweetList) {
			tweetTexts.add(on.get("tweetsText").textValue());
		}

		return findWordLevelStatistic(tweetTexts);
	}

	public static Map<String, Long> findWordLevelStatistic(List<String> tweetTexts) {
		List <String> words = tweetTexts.stream()
				.map(tweet -> tweet.split("\\s+"))
				.flatMap(Arrays::stream)
				.map(tweet-> tweet.toLowerCase())
				//.filter(tweet->tweet.matches(""))
				.collect(Collectors.toList());

		Map<String, Long> result =
				words.stream()
						.map(String::toLowerCase)
						.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
						.entrySet().stream()
						.sorted(Map.Entry.<String, Long> comparingByValue(reverseOrder()).thenComparing(Map.Entry.comparingByKey()))
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
								(oldValue, newValue) -> oldValue, LinkedHashMap::new));

		return result;
	}


}