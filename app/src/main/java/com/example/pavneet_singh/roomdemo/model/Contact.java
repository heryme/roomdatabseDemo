package com.example.pavneet_singh.roomdemo.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Contact{

	@SerializedName("twitter")
	private String twitter;

	@SerializedName("phone")
	private String phone;

	@SerializedName("formattedPhone")
	private String formattedPhone;

	public void setTwitter(String twitter){
		this.twitter = twitter;
	}

	public String getTwitter(){
		return twitter;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setFormattedPhone(String formattedPhone){
		this.formattedPhone = formattedPhone;
	}

	public String getFormattedPhone(){
		return formattedPhone;
	}
}