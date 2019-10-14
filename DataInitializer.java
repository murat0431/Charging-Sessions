package com.hackathon.coolRunnings.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.hackathon.coolRunnings.models.Campaign;
import com.hackathon.coolRunnings.models.User;
import com.hackathon.coolRunnings.services.UserService;
import com.hackathon.coolRunnings.services.UserServiceImpl;

public class DataInitializer {
	
	public static Hashtable<String, User> initializeDB() throws Exception{
		File file = new File("C:\\DEV\\amadeus_hackathon19\\final_csv.txt"); 
		Hashtable<String, User> myDB = new Hashtable<String, User>();
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		  String st; 
		  while ((st = br.readLine()) != null) {
			  String[] campaignParts = st.split(",");
			  String memberID = campaignParts[0];
			  String title = campaignParts[1];
			  String description = campaignParts[2];
			  Campaign c = new Campaign(title, description);
			  if (myDB.containsKey(memberID)) {
				  User u = myDB.get(memberID);
				  List<Campaign> userCampaigns = u.getCampaigns();
				  userCampaigns.add(c);
				  myDB.get(memberID).setCampaigns(userCampaigns);
				  
			} else {
				User u = new User();
				List<Campaign> campaigns = new ArrayList<Campaign>();
				campaigns.add(c);
				u.setCampaigns(campaigns);
				u.setMemberId(memberID);
				myDB.put(memberID, u);
			}
		  }
		  		  
        return myDB;
	}

}
