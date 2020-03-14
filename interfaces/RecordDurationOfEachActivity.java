package ro.utcn.tp.Assig5.interfaces;

import java.util.ArrayList;

import ro.utcn.tp.Assig5.model.MonitoredData;

public interface RecordDurationOfEachActivity {
	public int[] durationOfActivities(int[] durations, ArrayList<MonitoredData> datas);
}
