package com.marshallstudio.imager;

import android.os.Parcel;
import android.os.Parcelable;

public class Hits implements Parcelable {
    public static final Creator<Hits> CREATOR = new Creator<Hits>() {
        @Override
        public Hits createFromParcel(Parcel in) {
            return new Hits(in);
        }

        @Override
        public Hits[] newArray(int size) {
            return new Hits[size];
        }
    };
    private String webformatHeight;
    private String imageWidth;
    private String favorites;
    private String previewHeight;
    private String webformatURL;
    private String userImageURL;
    private String previewURL;
    private String comments;
    private String type;
    private String imageHeight;
    private String tags;
    private String previewWidth;
    private String downloads;
    private String user_id;
    private String largeImageURL;
    private String pageURL;
    private String id;
    private String imageSize;
    private String webformatWidth;
    private String user;
    private String views;
    private String likes;

    protected Hits(Parcel in) {
        webformatHeight = in.readString();
        imageWidth = in.readString();
        favorites = in.readString();
        previewHeight = in.readString();
        webformatURL = in.readString();
        userImageURL = in.readString();
        previewURL = in.readString();
        comments = in.readString();
        type = in.readString();
        imageHeight = in.readString();
        tags = in.readString();
        previewWidth = in.readString();
        downloads = in.readString();
        user_id = in.readString();
        largeImageURL = in.readString();
        pageURL = in.readString();
        id = in.readString();
        imageSize = in.readString();
        webformatWidth = in.readString();
        user = in.readString();
        views = in.readString();
        likes = in.readString();
    }

    public String getWebformatHeight() {
        return webformatHeight;
    }

    public void setWebformatHeight(String webformatHeight) {
        this.webformatHeight = webformatHeight;
    }

    public String getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(String imageWidth) {
        this.imageWidth = imageWidth;
    }

    public String getFavorites() {
        return favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    public String getPreviewHeight() {
        return previewHeight;
    }

    public void setPreviewHeight(String previewHeight) {
        this.previewHeight = previewHeight;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    public String getUserImageURL() {
        return userImageURL;
    }

    public void setUserImageURL(String userImageURL) {
        this.userImageURL = userImageURL;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(String imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPreviewWidth() {
        return previewWidth;
    }

    public void setPreviewWidth(String previewWidth) {
        this.previewWidth = previewWidth;
    }

    public String getDownloads() {
        return downloads;
    }

    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public String getPageURL() {
        return pageURL;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageSize() {
        return imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    public String getWebformatWidth() {
        return webformatWidth;
    }

    public void setWebformatWidth(String webformatWidth) {
        this.webformatWidth = webformatWidth;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(webformatHeight);
        parcel.writeString(imageWidth);
        parcel.writeString(favorites);
        parcel.writeString(previewHeight);
        parcel.writeString(webformatURL);
        parcel.writeString(userImageURL);
        parcel.writeString(previewURL);
        parcel.writeString(comments);
        parcel.writeString(type);
        parcel.writeString(imageHeight);
        parcel.writeString(tags);
        parcel.writeString(previewWidth);
        parcel.writeString(downloads);
        parcel.writeString(user_id);
        parcel.writeString(largeImageURL);
        parcel.writeString(pageURL);
        parcel.writeString(id);
        parcel.writeString(imageSize);
        parcel.writeString(webformatWidth);
        parcel.writeString(user);
        parcel.writeString(views);
        parcel.writeString(likes);
    }
}
