package com.snp.app.dtos;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document
@JsonIgnoreProperties
public class Posts {

	@Id
	private ObjectId _id;
	private String post;
	private String userId;
	private String userName;
	private String userPhotoId;
	private String postImageId;
	private int likes;
	private Boolean isActive = true;
	private Boolean isAdmin;
	private String profession;
	private Date createdDate = new Date();

	public String get_id() {
		return _id.toHexString();
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhotoId() {
		return userPhotoId;
	}

	public void setUserPhotoId(String userPhotoId) {
		this.userPhotoId = userPhotoId;
	}

	public String getPostImageId() {
		return postImageId;
	}

	public void setPostImageId(String postImageId) {
		this.postImageId = postImageId;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Posts [_id=" + _id + ", post=" + post + ", userId=" + userId + ", userName=" + userName
				+ ", userPhotoId=" + userPhotoId + ", postImageId=" + postImageId + ", likes=" + likes + ", isActive="
				+ isActive + ", isAdmin=" + isAdmin + ", profession=" + profession + ", createdDate=" + createdDate
				+ "]";
	}

}
