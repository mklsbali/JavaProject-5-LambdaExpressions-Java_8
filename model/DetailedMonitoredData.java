package ro.utcn.tp.Assig5.model;

public class DetailedMonitoredData {
	private int beginYear;
	private int beginMonth;
	private int beginDay;
	private int beginHour;
	private int beginMins;
	private int beginSecs;
	private int endYear;
	private int endMonth;
	private int endDay;
	private int endHour;
	private int endMins;
	private int endSecs;
	private String activityName;
	
	public DetailedMonitoredData(int beginYear, int beginMonth, int beginDay, int beginHour, int beginMins, int beginSecs,int endYear, int endMonth, int endDay, int endHour,
			int endMins, int endSecs, String activityName) {
		super();
		this.beginDay = beginDay;
		this.beginHour = beginHour;
		this.beginMins = beginMins;
		this.beginSecs = beginSecs;
		this.endDay = endDay;
		this.endHour = endHour;
		this.endMins = endMins;
		this.endSecs = endSecs;
		this.activityName = activityName;
		this.beginMonth=beginMonth;
		this.endMonth=endMonth;
		this.beginYear=beginYear;
		this.endYear=endYear;
	}
	public int getBeginDay() {
		return beginDay;
	}
	public void setBeginDay(int beginDay) {
		this.beginDay = beginDay;
	}
	public int getBeginHour() {
		return beginHour;
	}
	public void setBeginHour(int beginHour) {
		this.beginHour = beginHour;
	}
	public int getBeginMins() {
		return beginMins;
	}
	public void setBeginMins(int beginMins) {
		this.beginMins = beginMins;
	}
	public int getBeginSecs() {
		return beginSecs;
	}
	public void setBeginSecs(int beginSecs) {
		this.beginSecs = beginSecs;
	}
	public int getEndDay() {
		return endDay;
	}
	public void setEndDay(int endDay) {
		this.endDay = endDay;
	}
	public int getEndHour() {
		return endHour;
	}
	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}
	public int getEndMins() {
		return endMins;
	}
	public void setEndMins(int endMins) {
		this.endMins = endMins;
	}
	public int getEndSecs() {
		return endSecs;
	}
	public void setEndSecs(int endSecs) {
		this.endSecs = endSecs;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public int getBeginMonth() {
		return beginMonth;
	}
	public void setBeginMonth(int beginMonth) {
		this.beginMonth = beginMonth;
	}
	public int getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}
	public int getBeginYear() {
		return beginYear;
	}
	public void setBeginYear(int beginYear) {
		this.beginYear = beginYear;
	}
	public int getEndYear() {
		return endYear;
	}
	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}
	
}
