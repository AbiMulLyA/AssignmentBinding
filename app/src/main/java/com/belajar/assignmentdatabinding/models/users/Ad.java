package com.belajar.assignmentdatabinding.models.users;

import com.google.gson.annotations.SerializedName;

public class Ad{

	@SerializedName("company")
	private String company;

	@SerializedName("text")
	private String text;

	@SerializedName("url")
	private String url;

	public String getCompany(){
		return company;
	}

	public String getText(){
		return text;
	}

	public String getUrl(){
		return url;
	}
}