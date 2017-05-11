package com.nkang.kxmoment.baseobject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.DBObject;


/**
 * Knowledge Meta Value Object
 * @author xue-ke.du
 *
 */
public class KMVo {
    public static Gson gson = new GsonBuilder().create();
	private String id;
	private String title;
	private String[] tags;
	private String desc;
	private Float rating;
	private Integer count;
	private SMEVo[] SME;
	
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Float getRating() {
		return rating;
	}
	public void setRating(Float rating) {
		this.rating = rating;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public SMEVo[] getSME() {
		return SME;
	}
	public void setSME(SMEVo[] sME) {
		SME = sME;
	}
	@Override
    public String toString() {
        return gson.toJson(this);
    }
	public static KMVo json2Vo(DBObject dbObj){
		KMVo kmVo =  gson.fromJson(dbObj.toString(), KMVo.class);
		kmVo.setId(dbObj.get("_id").toString());
		return kmVo;
	}
}
