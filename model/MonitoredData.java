package ro.utcn.tp.Assig5.model;

public class MonitoredData {
	private String startTime;
	private String endTime;
	private String activityLabel;
	public MonitoredData(String startTime, String endTime, String activityLabel) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.activityLabel = activityLabel;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getActivityLabel() {
		return activityLabel;
	}
	public void setActivityLabel(String activityLabel) {
		this.activityLabel = activityLabel;
	}


}
