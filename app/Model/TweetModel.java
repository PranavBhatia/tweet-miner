package Model;

import java.util.List;

public class TweetModel {
	private String text;
	private String author;
	private String geolocation;
	private List<String> hashtags;
	private String sentiment;
	
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
	 * @return the geolocation
	 */
	public String getGeolocation() {
		return geolocation;
	}
	/**
	 * @param geolocation the geolocation to set
	 */
	public void setGeolocation(String geolocation) {
		this.geolocation = geolocation;
	}
	/**
	 * @return the hashtags
	 */
	public List<String> getHashtags() {
		return hashtags;
	}
	/**
	 * @param hashtags the hashtags to set
	 */
	public void setHashtags(List<String> hashtags) {
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
	

	
}
