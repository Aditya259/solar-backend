package com.solar.usefullClasses;

public class SectrorAndPercent {

	private String name;
	private Integer percent;
	
	public SectrorAndPercent() {
		super();
	}

	public SectrorAndPercent(String name, Integer percent) {
		super();
		this.name = name;
		this.percent = percent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPercent() {
		return percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	@Override
	public String toString() {
		return "SectrorAndPercent [name=" + name + ", percent=" + percent + "]";
	}
	
	
	
}
