package com.belajar.assignmentdatabinding.models.users;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UserModel{

	@SerializedName("per_page")
	private int perPage;

	@SerializedName("total")
	private int total;

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("ad")
	private Ad ad;

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")

	private int totalPages;

	public int getPerPage(){
		return perPage;
	}

	public int getTotal(){
		return total;
	}

	public Ad getAd(){
		return ad;
	}

	public List<DataItem> getData(){
		return data;
	}

	public int getPage(){
		return page;
	}

	public int getTotalPages(){
		return totalPages;
	}
}