package Model;

import java.util.List;

import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import static java.util.stream.Collectors.toList;


public class TweetModel {
	private String text;
	private String author;
	private List<HashtagEntity> hashtags;
	private String sentiment;
	private String location;
	
	public TweetModel(String text, String author, String location, List<HashtagEntity> hashTags) {
		this.text = text;
		this.author = author;
		this.location = location;
		this.hashtags = hashTags;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setText(String name) {
		this.text = name;
	}
	/**
	 * @return the name
	 */
	public String getText() {
		return text;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * @return the hashtags
	 */
	public List<String> getHashtags() {
		return hashtags.stream()
			.map(h->"#"+h.getText())
			.collect(toList());
	}
	
	public String getOneHashtag() {
		if (hashtags.size() == 0) return "No hashtags";
		return "#" + hashtags.get(0).getText();
	}
	/**
	 * @param hashtags the hashtags to set
	 */
	public void setHashtags(List<HashtagEntity> hashtags) {
		this.hashtags = hashtags;
	}
	/**
	 * @return the sentiment
	 */
	public String getSentiment() {
		return sentiment;
	}
	/**
	 * @param sentiment the sentiment to set
	 */
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
	/**
	 * @return the geoLocation
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param geoLocation the geoLocation to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	

	
}
