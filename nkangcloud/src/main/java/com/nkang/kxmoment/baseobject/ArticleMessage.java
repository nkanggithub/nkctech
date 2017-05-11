package com.nkang.kxmoment.baseobject;

import java.util.List;

public class ArticleMessage {
	public String num;
	public String title;
	public String type;
	public String content;
	public String time;
	public String picture;
	public List<String> visited;
	public String visitedNum;
	public String getVisitedNum() {
		return visitedNum;
	}
	public void setVisitedNum(String visitedNum) {
		this.visitedNum = visitedNum;
	}
	public String author;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public List<String> getVisited() {
		return visited;
	}
	public void setVisited(List<String> visited) {
		this.visited = visited;
	}
	public String webUrl;
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

}
