package com.gyan.model;

import java.util.Arrays;

public class Disease {

	private String title;
	private String url;
	private String[] facts;
	
	public Disease(){}

	public Disease(String title, String url, String[] facts) {
		super();
		this.title = title;
		this.url = url;
		this.facts = facts;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String[] getFacts() {
		return facts;
	}

	public void setFacts(String[] facts) {
		this.facts = facts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(facts);
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disease other = (Disease) obj;
		if (!Arrays.equals(facts, other.facts))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Disease [title=" + title + ", url=" + url + ", facts=" + Arrays.toString(facts) + "]";
	}
	
}
