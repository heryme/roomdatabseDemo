package com.example.pavneet_singh.roomdemo.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import javax.annotation.Generated;

import com.example.pavneet_singh.roomdemo.util.Constants;
import com.google.gson.annotations.SerializedName;

import java.util.List;
@Generated("com.robohorse.robopojogenerator")
@Entity(tableName = Constants.TABLE_NAME_VENUE)
public class Response{

	@PrimaryKey(autoGenerate = true)
	public int rId;

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("response")
	private Response response;

	@SerializedName("confident")
	private boolean confident;

	@SerializedName("venues")
	private List<VenuesItem> venues;

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public void setMeta(Meta meta){
		this.meta = meta;
	}

	public Meta getMeta(){
		return meta;
	}

	public void setResponse(Response response){
		this.response = response;
	}

	public Response getResponse(){
		return response;
	}

	public void setConfident(boolean confident){
		this.confident = confident;
	}

	public boolean isConfident(){
		return confident;
	}

	public void setVenues(List<VenuesItem> venues){
		this.venues = venues;
	}

	public List<VenuesItem> getVenues(){
		return venues;
	}



}