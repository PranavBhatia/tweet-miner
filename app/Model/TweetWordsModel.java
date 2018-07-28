package Model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TweetWordsModel {
	
	public static List<String> wordLevelStatistic(List<TweetModel> tweets) {
		List <String> words = tweets.stream() //Stream<TweetModel>
		.map(TweetModel::getText) //Stream<String>
		.map(tweet -> tweet.split(" "))
		.flatMap(Arrays::stream)
		.filter(word -> word.matches("[a-zA-Z]+"))
		.collect(Collectors.toList());
		
		
		 Map<String, Long> result = words.stream().collect(
				 						Collectors.groupingBy(
				 								Function.identity(), Collectors.counting()
				 						 ));
		 System.out.println(result);
		 return words;
	}
	
	
}
