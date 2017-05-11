package com.nkang.kxmoment.baseobject;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoClientCollection {
	private MongoClient mongoClient;
	private DBCollection mongoCollection;
	private DB mongoDB;
	
	public DB getMongoDB() {
		return mongoDB;
	}
	public void setMongoDB(DB mongoDB) {
		this.mongoDB = mongoDB;
	}
	public MongoClient getMongoClient() {
		return mongoClient;
	}
	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}
	public DBCollection getMongoCollection() {
		return mongoCollection;
	}
	public void setMongoCollection(DBCollection mongoCollection) {
		this.mongoCollection = mongoCollection;
	}
	
}
