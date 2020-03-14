package ro.utcn.tp.Assig5.model;

public class ActivitiesEachDay {
	private int day;
	private int month;
	private int leaving=0,toiletting = 0, showering=0, sleeping=0, breakfast=0, launch=0, dinner=0, snack=0, Spare_TimeTV=0, grooming=0;
	public ActivitiesEachDay(int month, int day) {
		super();
		this.month = month;
		this.day = day;
	}
	
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getLeaving() {
		return leaving;
	}
	public void setLeaving(int leaving) {
		this.leaving = leaving;
	}
	public int getToiletting() {
		return toiletting;
	}
	public void setToiletting(int toiletting) {
		this.toiletting = toiletting;
	}
	public int getShowering() {
		return showering;
	}
	public void setShowering(int showering) {
		this.showering = showering;
	}
	public int getSleeping() {
		return sleeping;
	}
	public void setSleeping(int sleeping) {
		this.sleeping = sleeping;
	}
	public int getBreakfast() {
		return breakfast;
	}
	public void setBreakfast(int breakfast) {
		this.breakfast = breakfast;
	}
	public int getLaunch() {
		return launch;
	}
	public void setLaunch(int launch) {
		this.launch = launch;
	}
	public int getDinner() {
		return dinner;
	}
	public void setDinner(int dinner) {
		this.dinner = dinner;
	}
	public int getSnack() {
		return snack;
	}
	public void setSnack(int snack) {
		this.snack = snack;
	}
	public int getSpare_TimeTV() {
		return Spare_TimeTV;
	}
	public void setSpare_TimeTV(int spare_TimeTV) {
		Spare_TimeTV = spare_TimeTV;
	}
	public int getGrooming() {
		return grooming;
	}
	public void setGrooming(int grooming) {
		this.grooming = grooming;
	}
	
}
