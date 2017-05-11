package com.nkang.kxmoment.baseobject;

import com.nkang.kxmoment.util.NumberUtil;

public class FaceObj {

	String smile ;
	String gender ;
	String moustache ;
	String beard;
	String age ;
	String glasses ;
	String anger;
	String contempt;
	String disgust;
	String fear;
	String happiness;
	String neutral;
	String sadness;
	String surprise;
	String currentUrl;
	
	public String getCurrentUrl() {
		return currentUrl;
	}
	public void setCurrentUrl(String currentUrl) {
		this.currentUrl = currentUrl;
	}
	float levelNum ;
	
	public float getLevelNum() {
		return levelNum;
	}
	public void setLevelNum(float levelNum) {
		this.levelNum = levelNum;
	}
	public String getSmile() {
		return smile;
	}
	public void setSmile(String smile) {
		this.smile = smile;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMoustache() {
		return moustache;
	}
	public void setMoustache(String moustache) {
		this.moustache = moustache;
	}
	public String getBeard() {
		return beard;
	}
	public void setBeard(String beard) {
		this.beard = beard;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGlasses() {
		return glasses;
	}
	public void setGlasses(String glasses) {
		this.glasses = glasses;
	}
	
	
	public String getAnger() {
		return anger;
	}
	public void setAnger(String anger) {
		this.anger = NumberUtil.scienceToNormal(anger);
	}
	public String getContempt() {
		return contempt;
	}
	public void setContempt(String contempt) {
		this.contempt = contempt;
	}
	public String getDisgust() {
		return disgust;
	}
	public void setDisgust(String disgust) {
		this.disgust = disgust;
	}
	public String getFear() {
		return fear;
	}
	public void setFear(String fear) {
		this.fear = fear;
	}
	public String getHappiness() {
		return happiness;
	}
	public void setHappiness(String happiness) {
		this.happiness = happiness;
	}
	public String getNeutral() {
		return neutral;
	}
	public void setNeutral(String neutral) {
		this.neutral = neutral;
	}
	public String getSadness() {
		return sadness;
	}
	public void setSadness(String sadness) {
		this.sadness = sadness;
	}
	public String getSurprise() {
		return surprise;
	}
	public void setSurprise(String surprise) {
		this.surprise = surprise;
	}
	public String Info(){
		String a = "smile : "+this.smile +"\n"+"age :"+this.age +"\n"+"glasses :"+this.glasses +"\n"+"gender :"+this.gender +"\n"+"moustache :"+this.moustache +
				"\n"+"beard :"+this.beard+"\n"+"anger :"+this.anger+"\n"+"contempt :"+this.contempt+"\n"+"disgust :"+this.disgust+"\n"+"fear :"+this.fear+
				"\n"+"happiness :"+this.happiness+"\n"+"neutral :"+this.neutral+"\n"+"sadness :"+this.sadness+"\n"+"surprise :"+this.surprise;
		return a;
	}
}
