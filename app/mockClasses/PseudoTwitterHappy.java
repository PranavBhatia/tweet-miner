package mockClasses;

import twitter4j.*;
import twitter4j.api.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.Authorization;
import twitter4j.auth.OAuth2Token;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.util.function.Consumer;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import services.TwitterApi;

public class PseudoTwitterHappy implements TwitterApi,Twitter {
	
	private static Twitter twitterInstance;
	
	@Override
	public Twitter getTwitterInstance() {
		
		if (twitterInstance==null)
		{ 
			twitterInstance=new PseudoTwitterHappy();
		}
		return twitterInstance;
	}
	
	
	
    @Override
    public void setOAuthConsumer(String consumerKey, String consumerSecret) {

    }

    @Override
    public OAuth2Token getOAuth2Token() throws TwitterException {
        return null;
    }

    @Override
    public void setOAuth2Token(OAuth2Token oauth2Token) {

    }

    @Override
    public void invalidateOAuth2Token() throws TwitterException {

    }

    @Override
    public RequestToken getOAuthRequestToken() throws TwitterException {
        return null;
    }

    @Override
    public RequestToken getOAuthRequestToken(String callbackURL) throws TwitterException {
        return null;
    }

    @Override
    public RequestToken getOAuthRequestToken(String callbackURL, String xAuthAccessType) throws TwitterException {
        return null;
    }

    @Override
    public RequestToken getOAuthRequestToken(String callbackURL, String xAuthAccessType, String xAuthMode) throws TwitterException {
        return null;
    }

    @Override
    public AccessToken getOAuthAccessToken() throws TwitterException {
        return null;
    }

    @Override
    public AccessToken getOAuthAccessToken(String oauthVerifier) throws TwitterException {
        return null;
    }

    @Override
    public AccessToken getOAuthAccessToken(RequestToken requestToken) throws TwitterException {
        return null;
    }

    @Override
    public AccessToken getOAuthAccessToken(RequestToken requestToken, String oauthVerifier) throws TwitterException {
        return null;
    }

    @Override
    public AccessToken getOAuthAccessToken(String screenName, String password) throws TwitterException {
        return null;
    }

    @Override
    public void setOAuthAccessToken(AccessToken accessToken) {

    }

    @Override
    public TimelinesResources timelines() {
        return null;
    }

    @Override
    public TweetsResources tweets() {
        return null;
    }

    @Override
    public SearchResource search() {
        return null;
    }

    @Override
    public DirectMessagesResources directMessages() {
        return null;
    }

    @Override
    public FriendsFollowersResources friendsFollowers() {
        return null;
    }

    @Override
    public UsersResources users() {
        return null;
    }

    @Override
    public SuggestedUsersResources suggestedUsers() {
        return null;
    }

    @Override
    public FavoritesResources favorites() {
        return null;
    }

    @Override
    public ListsResources list() {
        return null;
    }

    @Override
    public SavedSearchesResources savedSearches() {
        return null;
    }

    @Override
    public PlacesGeoResources placesGeo() {
        return null;
    }

    @Override
    public TrendsResources trends() {
        return null;
    }

    @Override
    public SpamReportingResource spamReporting() {
        return null;
    }

    @Override
    public HelpResources help() {
        return null;
    }

    @Override
    public String getScreenName() throws TwitterException, IllegalStateException {
        return null;
    }

    @Override
    public long getId() throws TwitterException, IllegalStateException {
        return 0;
    }

    @Override
    public void addRateLimitStatusListener(RateLimitStatusListener listener) {

    }

    @Override
    public void onRateLimitStatus(Consumer<RateLimitStatusEvent> action) {

    }

    @Override
    public void onRateLimitReached(Consumer<RateLimitStatusEvent> action) {

    }

    @Override
    public Authorization getAuthorization() {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public ResponseList<DirectMessage> getDirectMessages() throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<DirectMessage> getDirectMessages(Paging paging) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<DirectMessage> getSentDirectMessages() throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<DirectMessage> getSentDirectMessages(Paging paging) throws TwitterException {
        return null;
    }

    @Override
    public DirectMessage showDirectMessage(long id) throws TwitterException {
        return null;
    }

    @Override
    public DirectMessage destroyDirectMessage(long id) throws TwitterException {
        return null;
    }

    @Override
    public DirectMessage sendDirectMessage(long userId, String text) throws TwitterException {
        return null;
    }

    @Override
    public DirectMessage sendDirectMessage(String screenName, String text) throws TwitterException {
        return null;
    }

    @Override
    public InputStream getDMImageAsStream(String url) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getFavorites() throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getFavorites(long userId) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getFavorites(String screenName) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getFavorites(Paging paging) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getFavorites(long userId, Paging paging) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getFavorites(String screenName, Paging paging) throws TwitterException {
        return null;
    }

    @Override
    public Status createFavorite(long id) throws TwitterException {
        return null;
    }

    @Override
    public Status destroyFavorite(long id) throws TwitterException {
        return null;
    }

    @Override
    public IDs getNoRetweetsFriendships() throws TwitterException {
        return null;
    }

    @Override
    public IDs getFriendsIDs(long cursor) throws TwitterException {
        return null;
    }

    @Override
    public IDs getFriendsIDs(long userId, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public IDs getFriendsIDs(long userId, long cursor, int count) throws TwitterException {
        return null;
    }

    @Override
    public IDs getFriendsIDs(String screenName, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public IDs getFriendsIDs(String screenName, long cursor, int count) throws TwitterException {
        return null;
    }

    @Override
    public IDs getFollowersIDs(long cursor) throws TwitterException {
        return null;
    }

    @Override
    public IDs getFollowersIDs(long userId, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public IDs getFollowersIDs(long userId, long cursor, int count) throws TwitterException {
        return null;
    }

    @Override
    public IDs getFollowersIDs(String screenName, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public IDs getFollowersIDs(String screenName, long cursor, int count) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Friendship> lookupFriendships(long... ids) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Friendship> lookupFriendships(String... screenNames) throws TwitterException {
        return null;
    }

    @Override
    public IDs getIncomingFriendships(long cursor) throws TwitterException {
        return null;
    }

    @Override
    public IDs getOutgoingFriendships(long cursor) throws TwitterException {
        return null;
    }

    @Override
    public User createFriendship(long userId) throws TwitterException {
        return null;
    }

    @Override
    public User createFriendship(String screenName) throws TwitterException {
        return null;
    }

    @Override
    public User createFriendship(long userId, boolean follow) throws TwitterException {
        return null;
    }

    @Override
    public User createFriendship(String screenName, boolean follow) throws TwitterException {
        return null;
    }

    @Override
    public User destroyFriendship(long userId) throws TwitterException {
        return null;
    }

    @Override
    public User destroyFriendship(String screenName) throws TwitterException {
        return null;
    }

    @Override
    public Relationship updateFriendship(long userId, boolean enableDeviceNotification, boolean retweets) throws TwitterException {
        return null;
    }

    @Override
    public Relationship updateFriendship(String screenName, boolean enableDeviceNotification, boolean retweets) throws TwitterException {
        return null;
    }

    @Override
    public Relationship showFriendship(long sourceId, long targetId) throws TwitterException {
        return null;
    }

    @Override
    public Relationship showFriendship(String sourceScreenName, String targetScreenName) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getFriendsList(long userId, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getFriendsList(long userId, long cursor, int count) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getFriendsList(String screenName, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getFriendsList(String screenName, long cursor, int count) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getFriendsList(long userId, long cursor, int count, boolean skipStatus, boolean includeUserEntities) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getFriendsList(String screenName, long cursor, int count, boolean skipStatus, boolean includeUserEntities) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getFollowersList(long userId, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getFollowersList(String screenName, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getFollowersList(long userId, long cursor, int count) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getFollowersList(String screenName, long cursor, int count) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getFollowersList(long userId, long cursor, int count, boolean skipStatus, boolean includeUserEntities) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getFollowersList(String screenName, long cursor, int count, boolean skipStatus, boolean includeUserEntities) throws TwitterException {
        return null;
    }

    @Override
    public TwitterAPIConfiguration getAPIConfiguration() throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Language> getLanguages() throws TwitterException {
        return null;
    }

    @Override
    public String getPrivacyPolicy() throws TwitterException {
        return null;
    }

    @Override
    public String getTermsOfService() throws TwitterException {
        return null;
    }

    @Override
    public Map<String, RateLimitStatus> getRateLimitStatus() throws TwitterException {
        return null;
    }

    @Override
    public Map<String, RateLimitStatus> getRateLimitStatus(String... resources) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<UserList> getUserLists(String listOwnerScreenName) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<UserList> getUserLists(String listOwnerScreenName, boolean reverse) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<UserList> getUserLists(long listOwnerUserId) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<UserList> getUserLists(long listOwnerUserId, boolean reverse) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getUserListStatuses(long listId, Paging paging) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getUserListStatuses(long ownerId, String slug, Paging paging) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getUserListStatuses(String ownerScreenName, String slug, Paging paging) throws TwitterException {
        return null;
    }

    @Override
    public UserList destroyUserListMember(long listId, long userId) throws TwitterException {
        return null;
    }

    @Override
    public UserList destroyUserListMember(long listId, String screenName) throws TwitterException {
        return null;
    }

    @Override
    public UserList destroyUserListMembers(long listId, String[] screenNames) throws TwitterException {
        return null;
    }

    @Override
    public UserList destroyUserListMembers(long listId, long[] userIds) throws TwitterException {
        return null;
    }

    @Override
    public UserList destroyUserListMembers(String ownerScreenName, String slug, String[] screenNames) throws TwitterException {
        return null;
    }

    @Override
    public UserList destroyUserListMember(long ownerId, String slug, long userId) throws TwitterException {
        return null;
    }

    @Override
    public UserList destroyUserListMember(String ownerScreenName, String slug, long userId) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<UserList> getUserListMemberships(long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<UserList> getUserListMemberships(int count, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<UserList> getUserListMemberships(long listMemberId, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<UserList> getUserListMemberships(long listMemberId, int count, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<UserList> getUserListMemberships(String listMemberScreenName, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<UserList> getUserListMemberships(String listMemberScreenName, int count, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<UserList> getUserListMemberships(String listMemberScreenName, long cursor, boolean filterToOwnedLists) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<UserList> getUserListMemberships(String listMemberScreenName, int count, long cursor, boolean filterToOwnedLists) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<UserList> getUserListMemberships(long listMemberId, long cursor, boolean filterToOwnedLists) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<UserList> getUserListMemberships(long listMemberId, int count, long cursor, boolean filterToOwnedLists) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getUserListSubscribers(long listId, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getUserListSubscribers(long listId, int count, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getUserListSubscribers(long listId, int count, long cursor, boolean skipStatus) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getUserListSubscribers(long ownerId, String slug, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getUserListSubscribers(long ownerId, String slug, int count, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getUserListSubscribers(long ownerId, String slug, int count, long cursor, boolean skipStatus) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getUserListSubscribers(String ownerScreenName, String slug, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getUserListSubscribers(String ownerScreenName, String slug, int count, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getUserListSubscribers(String ownerScreenName, String slug, int count, long cursor, boolean skipStatus) throws TwitterException {
        return null;
    }

    @Override
    public UserList createUserListSubscription(long listId) throws TwitterException {
        return null;
    }

    @Override
    public UserList createUserListSubscription(long ownerId, String slug) throws TwitterException {
        return null;
    }

    @Override
    public UserList createUserListSubscription(String ownerScreenName, String slug) throws TwitterException {
        return null;
    }

    @Override
    public User showUserListSubscription(long listId, long userId) throws TwitterException {
        return null;
    }

    @Override
    public User showUserListSubscription(long ownerId, String slug, long userId) throws TwitterException {
        return null;
    }

    @Override
    public User showUserListSubscription(String ownerScreenName, String slug, long userId) throws TwitterException {
        return null;
    }

    @Override
    public UserList destroyUserListSubscription(long listId) throws TwitterException {
        return null;
    }

    @Override
    public UserList destroyUserListSubscription(long ownerId, String slug) throws TwitterException {
        return null;
    }

    @Override
    public UserList destroyUserListSubscription(String ownerScreenName, String slug) throws TwitterException {
        return null;
    }

    @Override
    public UserList createUserListMembers(long listId, long... userIds) throws TwitterException {
        return null;
    }

    @Override
    public UserList createUserListMembers(long ownerId, String slug, long... userIds) throws TwitterException {
        return null;
    }

    @Override
    public UserList createUserListMembers(String ownerScreenName, String slug, long... userIds) throws TwitterException {
        return null;
    }

    @Override
    public UserList createUserListMembers(long listId, String... screenNames) throws TwitterException {
        return null;
    }

    @Override
    public UserList createUserListMembers(long ownerId, String slug, String... screenNames) throws TwitterException {
        return null;
    }

    @Override
    public UserList createUserListMembers(String ownerScreenName, String slug, String... screenNames) throws TwitterException {
        return null;
    }

    @Override
    public User showUserListMembership(long listId, long userId) throws TwitterException {
        return null;
    }

    @Override
    public User showUserListMembership(long ownerId, String slug, long userId) throws TwitterException {
        return null;
    }

    @Override
    public User showUserListMembership(String ownerScreenName, String slug, long userId) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getUserListMembers(long listId, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getUserListMembers(long listId, int count, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getUserListMembers(long listId, int count, long cursor, boolean skipStatus) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getUserListMembers(long ownerId, String slug, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getUserListMembers(long ownerId, String slug, int count, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getUserListMembers(long ownerId, String slug, int count, long cursor, boolean skipStatus) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getUserListMembers(String ownerScreenName, String slug, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getUserListMembers(String ownerScreenName, String slug, int count, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getUserListMembers(String ownerScreenName, String slug, int count, long cursor, boolean skipStatus) throws TwitterException {
        return null;
    }

    @Override
    public UserList createUserListMember(long listId, long userId) throws TwitterException {
        return null;
    }

    @Override
    public UserList createUserListMember(long ownerId, String slug, long userId) throws TwitterException {
        return null;
    }

    @Override
    public UserList createUserListMember(String ownerScreenName, String slug, long userId) throws TwitterException {
        return null;
    }

    @Override
    public UserList destroyUserList(long listId) throws TwitterException {
        return null;
    }

    @Override
    public UserList destroyUserList(long ownerId, String slug) throws TwitterException {
        return null;
    }

    @Override
    public UserList destroyUserList(String ownerScreenName, String slug) throws TwitterException {
        return null;
    }

    @Override
    public UserList updateUserList(long listId, String newListName, boolean isPublicList, String newDescription) throws TwitterException {
        return null;
    }

    @Override
    public UserList updateUserList(long ownerId, String slug, String newListName, boolean isPublicList, String newDescription) throws TwitterException {
        return null;
    }

    @Override
    public UserList updateUserList(String ownerScreenName, String slug, String newListName, boolean isPublicList, String newDescription) throws TwitterException {
        return null;
    }

    @Override
    public UserList createUserList(String listName, boolean isPublicList, String description) throws TwitterException {
        return null;
    }

    @Override
    public UserList showUserList(long listId) throws TwitterException {
        return null;
    }

    @Override
    public UserList showUserList(long ownerId, String slug) throws TwitterException {
        return null;
    }

    @Override
    public UserList showUserList(String ownerScreenName, String slug) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<UserList> getUserListSubscriptions(String listSubscriberScreenName, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<UserList> getUserListSubscriptions(String listSubscriberScreenName, int count, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<UserList> getUserListSubscriptions(long listSubscriberId, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<UserList> getUserListSubscriptions(long listSubscriberId, int count, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<UserList> getUserListsOwnerships(String listOwnerScreenName, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<UserList> getUserListsOwnerships(String listOwnerScreenName, int count, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<UserList> getUserListsOwnerships(long listOwnerId, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<UserList> getUserListsOwnerships(long listOwnerId, int count, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public Place getGeoDetails(String placeId) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Place> reverseGeoCode(GeoQuery query) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Place> searchPlaces(GeoQuery query) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Place> getSimilarPlaces(GeoLocation location, String name, String containedWithin, String streetAddress) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<SavedSearch> getSavedSearches() throws TwitterException {
        return null;
    }

    @Override
    public SavedSearch showSavedSearch(long id) throws TwitterException {
        return null;
    }

    @Override
    public SavedSearch createSavedSearch(String query) throws TwitterException {
        return null;
    }

    @Override
    public SavedSearch destroySavedSearch(long id) throws TwitterException {
        return null;
    }

    @Override
    public QueryResult search(Query query) throws TwitterException {
        return new QueryResult() {
            @Override
            public long getSinceId() {
                return 0;
            }

            @Override
            public long getMaxId() {
                return 0;
            }

            @Override
            public String getRefreshURL() {
                return null;
            }

            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public double getCompletedIn() {
                return 0;
            }

            @Override
            public String getQuery() {
                return null;
            }

            @Override
            public List<Status> getTweets() {
                ArrayList<Status> list = new ArrayList<Status>();
                Status status = new Status() {
                    @Override
                    public Date getCreatedAt() {
                        return null;
                    }

                    @Override
                    public long getId() {
                        return 0;
                    }

                    @Override
                    public String getText() {
                        return "I am happy \uD83D\uDE42";
                    }

                    @Override
                    public String getSource() {
                        return null;
                    }

                    @Override
                    public boolean isTruncated() {
                        return false;
                    }

                    @Override
                    public long getInReplyToStatusId() {
                        return 0;
                    }

                    @Override
                    public long getInReplyToUserId() {
                        return 0;
                    }

                    @Override
                    public String getInReplyToScreenName() {
                        return null;
                    }

                    @Override
                    public GeoLocation getGeoLocation() {
                        return null;
                    }

                    @Override
                    public Place getPlace() {
                        return null;
                    }

                    @Override
                    public boolean isFavorited() {
                        return false;
                    }

                    @Override
                    public boolean isRetweeted() {
                        return false;
                    }

                    @Override
                    public int getFavoriteCount() {
                        return 0;
                    }

                    @Override
                    public User getUser() {
                        User user = new User() {
                            @Override
                            public long getId() {
                                return 0;
                            }

                            @Override
                            public String getName() {
                                return "Rodolfo";
                            }

                            @Override
                            public String getScreenName() {
                                return null;
                            }

                            @Override
                            public String getLocation() {
                                return null;
                            }

                            @Override
                            public String getDescription() {
                                return null;
                            }

                            @Override
                            public boolean isContributorsEnabled() {
                                return false;
                            }

                            @Override
                            public String getProfileImageURL() {
                                return null;
                            }

                            @Override
                            public String getBiggerProfileImageURL() {
                                return null;
                            }

                            @Override
                            public String getMiniProfileImageURL() {
                                return null;
                            }

                            @Override
                            public String getOriginalProfileImageURL() {
                                return null;
                            }

                            @Override
                            public String getProfileImageURLHttps() {
                                return null;
                            }

                            @Override
                            public String getBiggerProfileImageURLHttps() {
                                return null;
                            }

                            @Override
                            public String getMiniProfileImageURLHttps() {
                                return null;
                            }

                            @Override
                            public String getOriginalProfileImageURLHttps() {
                                return null;
                            }

                            @Override
                            public boolean isDefaultProfileImage() {
                                return false;
                            }

                            @Override
                            public String getURL() {
                                return null;
                            }

                            @Override
                            public boolean isProtected() {
                                return false;
                            }

                            @Override
                            public int getFollowersCount() {
                                return 0;
                            }

                            @Override
                            public Status getStatus() {
                                return null;
                            }

                            @Override
                            public String getProfileBackgroundColor() {
                                return null;
                            }

                            @Override
                            public String getProfileTextColor() {
                                return null;
                            }

                            @Override
                            public String getProfileLinkColor() {
                                return null;
                            }

                            @Override
                            public String getProfileSidebarFillColor() {
                                return null;
                            }

                            @Override
                            public String getProfileSidebarBorderColor() {
                                return null;
                            }

                            @Override
                            public boolean isProfileUseBackgroundImage() {
                                return false;
                            }

                            @Override
                            public boolean isDefaultProfile() {
                                return false;
                            }

                            @Override
                            public boolean isShowAllInlineMedia() {
                                return false;
                            }

                            @Override
                            public int getFriendsCount() {
                                return 0;
                            }

                            @Override
                            public Date getCreatedAt() {
                                return null;
                            }

                            @Override
                            public int getFavouritesCount() {
                                return 0;
                            }

                            @Override
                            public int getUtcOffset() {
                                return 0;
                            }

                            @Override
                            public String getTimeZone() {
                                return null;
                            }

                            @Override
                            public String getProfileBackgroundImageURL() {
                                return null;
                            }

                            @Override
                            public String getProfileBackgroundImageUrlHttps() {
                                return null;
                            }

                            @Override
                            public String getProfileBannerURL() {
                                return null;
                            }

                            @Override
                            public String getProfileBannerRetinaURL() {
                                return null;
                            }

                            @Override
                            public String getProfileBannerIPadURL() {
                                return null;
                            }

                            @Override
                            public String getProfileBannerIPadRetinaURL() {
                                return null;
                            }

                            @Override
                            public String getProfileBannerMobileURL() {
                                return null;
                            }

                            @Override
                            public String getProfileBannerMobileRetinaURL() {
                                return null;
                            }

                            @Override
                            public boolean isProfileBackgroundTiled() {
                                return false;
                            }

                            @Override
                            public String getLang() {
                                return null;
                            }

                            @Override
                            public int getStatusesCount() {
                                return 0;
                            }

                            @Override
                            public boolean isGeoEnabled() {
                                return false;
                            }

                            @Override
                            public boolean isVerified() {
                                return false;
                            }

                            @Override
                            public boolean isTranslator() {
                                return false;
                            }

                            @Override
                            public int getListedCount() {
                                return 0;
                            }

                            @Override
                            public boolean isFollowRequestSent() {
                                return false;
                            }

                            @Override
                            public URLEntity[] getDescriptionURLEntities() {
                                return new URLEntity[0];
                            }

                            @Override
                            public URLEntity getURLEntity() {
                                return null;
                            }

                            @Override
                            public String[] getWithheldInCountries() {
                                return new String[0];
                            }

                            @Override
                            public int compareTo(User user) {
                                return 0;
                            }

                            @Override
                            public RateLimitStatus getRateLimitStatus() {
                                return null;
                            }

                            @Override
                            public int getAccessLevel() {
                                return 0;
                            }
                        };
                        return user;
                    }

                    @Override
                    public boolean isRetweet() {
                        return false;
                    }

                    @Override
                    public Status getRetweetedStatus() {
                        return null;
                    }

                    @Override
                    public long[] getContributors() {
                        return new long[0];
                    }

                    @Override
                    public int getRetweetCount() {
                        return 0;
                    }

                    @Override
                    public boolean isRetweetedByMe() {
                        return false;
                    }

                    @Override
                    public long getCurrentUserRetweetId() {
                        return 0;
                    }

                    @Override
                    public boolean isPossiblySensitive() {
                        return false;
                    }

                    @Override
                    public String getLang() {
                        return null;
                    }

                    @Override
                    public Scopes getScopes() {
                        return null;
                    }

                    @Override
                    public String[] getWithheldInCountries() {
                        return new String[0];
                    }

                    @Override
                    public long getQuotedStatusId() {
                        return 0;
                    }

                    @Override
                    public Status getQuotedStatus() {
                        return null;
                    }

                    @Override
                    public int compareTo(Status status) {
                        return 0;
                    }

                    @Override
                    public UserMentionEntity[] getUserMentionEntities() {
                        return new UserMentionEntity[0];
                    }

                    @Override
                    public URLEntity[] getURLEntities() {
                        return new URLEntity[0];
                    }

                    @Override
                    public HashtagEntity[] getHashtagEntities() {
                        return new HashtagEntity[0];
                    }

                    @Override
                    public MediaEntity[] getMediaEntities() {
                        return new MediaEntity[0];
                    }

                    @Override
                    public ExtendedMediaEntity[] getExtendedMediaEntities() {
                        return new ExtendedMediaEntity[0];
                    }

                    @Override
                    public SymbolEntity[] getSymbolEntities() {
                        return new SymbolEntity[0];
                    }

                    @Override
                    public RateLimitStatus getRateLimitStatus() {
                        return null;
                    }

                    @Override
                    public int getAccessLevel() {
                        return 0;
                    }
                };
                list.add(status);
                return list;
            }

            @Override
            public Query nextQuery() {
                return null;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public RateLimitStatus getRateLimitStatus() {
                return null;
            }

            @Override
            public int getAccessLevel() {
                return 0;
            }
        };
    }

    @Override
    public User reportSpam(long userId) throws TwitterException {
        return null;
    }

    @Override
    public User reportSpam(String screenName) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<User> getUserSuggestions(String categorySlug) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Category> getSuggestedUserCategories() throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<User> getMemberSuggestions(String categorySlug) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getMentionsTimeline() throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getMentionsTimeline(Paging paging) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getUserTimeline(String screenName, Paging paging) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getUserTimeline(long userId, Paging paging) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getUserTimeline(String screenName) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getUserTimeline(long userId) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getUserTimeline() throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getUserTimeline(Paging paging) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getHomeTimeline() throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getHomeTimeline(Paging paging) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getRetweetsOfMe() throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getRetweetsOfMe(Paging paging) throws TwitterException {
        return null;
    }

    @Override
    public Trends getPlaceTrends(int woeid) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Location> getAvailableTrends() throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Location> getClosestTrends(GeoLocation location) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> getRetweets(long statusId) throws TwitterException {
        return null;
    }

    @Override
    public IDs getRetweeterIds(long statusId, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public IDs getRetweeterIds(long statusId, int count, long cursor) throws TwitterException {
        return null;
    }

    @Override
    public Status showStatus(long id) throws TwitterException {
        return null;
    }

    @Override
    public Status destroyStatus(long statusId) throws TwitterException {
        return null;
    }

    @Override
    public Status updateStatus(String status) throws TwitterException {
        return null;
    }

    @Override
    public Status updateStatus(StatusUpdate latestStatus) throws TwitterException {
        return null;
    }

    @Override
    public Status retweetStatus(long statusId) throws TwitterException {
        return null;
    }

    @Override
    public OEmbed getOEmbed(OEmbedRequest req) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<Status> lookup(long... ids) throws TwitterException {
        return null;
    }

    @Override
    public UploadedMedia uploadMedia(File mediaFile) throws TwitterException {
        return null;
    }

    @Override
    public UploadedMedia uploadMedia(String fileName, InputStream media) throws TwitterException {
        return null;
    }

    @Override
    public AccountSettings getAccountSettings() throws TwitterException {
        return null;
    }

    @Override
    public User verifyCredentials() throws TwitterException {
        return null;
    }

    @Override
    public AccountSettings updateAccountSettings(Integer trendLocationWoeid, Boolean sleepTimeEnabled, String startSleepTime, String endSleepTime, String timeZone, String lang) throws TwitterException {
        return null;
    }

    @Override
    public User updateProfile(String name, String url, String location, String description) throws TwitterException {
        return null;
    }

    @Override
    public User updateProfileBackgroundImage(File image, boolean tile) throws TwitterException {
        return null;
    }

    @Override
    public User updateProfileBackgroundImage(InputStream image, boolean tile) throws TwitterException {
        return null;
    }

    @Override
    public User updateProfileColors(String profileBackgroundColor, String profileTextColor, String profileLinkColor, String profileSidebarFillColor, String profileSidebarBorderColor) throws TwitterException {
        return null;
    }

    @Override
    public User updateProfileImage(File image) throws TwitterException {
        return null;
    }

    @Override
    public User updateProfileImage(InputStream image) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getBlocksList() throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getBlocksList(long cursor) throws TwitterException {
        return null;
    }

    @Override
    public IDs getBlocksIDs() throws TwitterException {
        return null;
    }

    @Override
    public IDs getBlocksIDs(long cursor) throws TwitterException {
        return null;
    }

    @Override
    public User createBlock(long userId) throws TwitterException {
        return null;
    }

    @Override
    public User createBlock(String screenName) throws TwitterException {
        return null;
    }

    @Override
    public User destroyBlock(long userId) throws TwitterException {
        return null;
    }

    @Override
    public User destroyBlock(String screen_name) throws TwitterException {
        return null;
    }

    @Override
    public PagableResponseList<User> getMutesList(long cursor) throws TwitterException {
        return null;
    }

    @Override
    public IDs getMutesIDs(long cursor) throws TwitterException {
        return null;
    }

    @Override
    public User createMute(long userId) throws TwitterException {
        return null;
    }

    @Override
    public User createMute(String screenName) throws TwitterException {
        return null;
    }

    @Override
    public User destroyMute(long userId) throws TwitterException {
        return null;
    }

    @Override
    public User destroyMute(String screenName) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<User> lookupUsers(long... ids) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<User> lookupUsers(String... screenNames) throws TwitterException {
        return null;
    }

    @Override
    public User showUser(long userId) throws TwitterException {
        return null;
    }

    @Override
    public User showUser(String screenName) throws TwitterException {
        User user = new User() {
            @Override
            public long getId() {
                return 0;
            }

            @Override
            public String getName() {
                return "Rodolfo";
            }

            @Override
            public String getScreenName() {
                return null;
            }

            @Override
            public String getLocation() {
                return null;
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public boolean isContributorsEnabled() {
                return false;
            }

            @Override
            public String getProfileImageURL() {
                return null;
            }

            @Override
            public String getBiggerProfileImageURL() {
                return null;
            }

            @Override
            public String getMiniProfileImageURL() {
                return null;
            }

            @Override
            public String getOriginalProfileImageURL() {
                return null;
            }

            @Override
            public String getProfileImageURLHttps() {
                return null;
            }

            @Override
            public String getBiggerProfileImageURLHttps() {
                return null;
            }

            @Override
            public String getMiniProfileImageURLHttps() {
                return null;
            }

            @Override
            public String getOriginalProfileImageURLHttps() {
                return null;
            }

            @Override
            public boolean isDefaultProfileImage() {
                return false;
            }

            @Override
            public String getURL() {
                return null;
            }

            @Override
            public boolean isProtected() {
                return false;
            }

            @Override
            public int getFollowersCount() {
                return 0;
            }

            @Override
            public Status getStatus() {
                return null;
            }

            @Override
            public String getProfileBackgroundColor() {
                return null;
            }

            @Override
            public String getProfileTextColor() {
                return null;
            }

            @Override
            public String getProfileLinkColor() {
                return null;
            }

            @Override
            public String getProfileSidebarFillColor() {
                return null;
            }

            @Override
            public String getProfileSidebarBorderColor() {
                return null;
            }

            @Override
            public boolean isProfileUseBackgroundImage() {
                return false;
            }

            @Override
            public boolean isDefaultProfile() {
                return false;
            }

            @Override
            public boolean isShowAllInlineMedia() {
                return false;
            }

            @Override
            public int getFriendsCount() {
                return 0;
            }

            @Override
            public Date getCreatedAt() {
                return null;
            }

            @Override
            public int getFavouritesCount() {
                return 0;
            }

            @Override
            public int getUtcOffset() {
                return 0;
            }

            @Override
            public String getTimeZone() {
                return null;
            }

            @Override
            public String getProfileBackgroundImageURL() {
                return null;
            }

            @Override
            public String getProfileBackgroundImageUrlHttps() {
                return null;
            }

            @Override
            public String getProfileBannerURL() {
                return null;
            }

            @Override
            public String getProfileBannerRetinaURL() {
                return null;
            }

            @Override
            public String getProfileBannerIPadURL() {
                return null;
            }

            @Override
            public String getProfileBannerIPadRetinaURL() {
                return null;
            }

            @Override
            public String getProfileBannerMobileURL() {
                return null;
            }

            @Override
            public String getProfileBannerMobileRetinaURL() {
                return null;
            }

            @Override
            public boolean isProfileBackgroundTiled() {
                return false;
            }

            @Override
            public String getLang() {
                return null;
            }

            @Override
            public int getStatusesCount() {
                return 0;
            }

            @Override
            public boolean isGeoEnabled() {
                return false;
            }

            @Override
            public boolean isVerified() {
                return false;
            }

            @Override
            public boolean isTranslator() {
                return false;
            }

            @Override
            public int getListedCount() {
                return 0;
            }

            @Override
            public boolean isFollowRequestSent() {
                return false;
            }

            @Override
            public URLEntity[] getDescriptionURLEntities() {
                return new URLEntity[0];
            }

            @Override
            public URLEntity getURLEntity() {
                return null;
            }

            @Override
            public String[] getWithheldInCountries() {
                return new String[0];
            }

            @Override
            public int compareTo(User user) {
                return 0;
            }

            @Override
            public RateLimitStatus getRateLimitStatus() {
                return null;
            }

            @Override
            public int getAccessLevel() {
                return 0;
            }
        };
        return user;
    }

    @Override
    public ResponseList<User> searchUsers(String query, int page) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<User> getContributees(long userId) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<User> getContributees(String screenName) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<User> getContributors(long userId) throws TwitterException {
        return null;
    }

    @Override
    public ResponseList<User> getContributors(String screenName) throws TwitterException {
        return null;
    }

    @Override
    public void removeProfileBanner() throws TwitterException {

    }

    @Override
    public void updateProfileBanner(File image) throws TwitterException {

    }

    @Override
    public void updateProfileBanner(InputStream image) throws TwitterException {

    }

	
}
