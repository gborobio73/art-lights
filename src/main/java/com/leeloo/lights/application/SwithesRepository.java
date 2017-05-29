package com.leeloo.lights.application;

import org.bson.Document;
import org.springframework.stereotype.Repository;

import com.leeloo.lights.api.SwitchDto;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Repository
public class SwithesRepository {

	public void store(SwitchDto switchDto, String address){
		String mongoConnectionString = System.getenv("MONGODB_URI");
		if (mongoConnectionString != null) {
			Document doc = new Document("id", switchDto.getIndex())
		            .append("state", switchDto.getState())
		            .append("address", address);
			MongoClient client = null;
			try {
				MongoClientURI uri  = new MongoClientURI(mongoConnectionString);
		        client = new MongoClient(uri);
		        MongoDatabase db = client.getDatabase(uri.getDatabase());
		        MongoCollection<Document> switches = db.getCollection("switches");
		        switches.insertOne(doc);
			}catch(Exception e){
				System.err.println(String.format("An error ocurred when accesing mongodb: %s", e.getMessage()));
			} finally {
				if (client!= null) {
					client.close();
				}
			}
		}	
		else{
			System.out.println("** Missing DB configuration **");
		}	
	}
}