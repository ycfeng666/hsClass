package com.sy5.helper;

public class ScoreBean {
	private int chinses;
	private int math;
	private int english;
	private int political;
	private int physical;
	private int sports;
	public void setChinses(int chinses){this.chinses = chinses;}
	public void setMath(int math){this.math = math;}
	public void setEnglish(int english){this.english = english;}
	public void setPolitical(int political){this.political = political;}
	public void setPhysical(int physical){this.physical = physical;}
	public void setSports(int sports){this.sports = sports;}
	public int CountScore(){return chinses+math+english+political+physical+sports;}
	public int MeanScore(){
		int temp = CountScore()>0?CountScore()/6:0;return temp;
	}
}
