package ro.utcn.tp.Assig5.interfaces;

import java.util.ArrayList;
import java.util.Map;

import ro.utcn.tp.Assig5.model.MonitoredData;

public interface LastTask_90 {
	public ArrayList<String> Less_5_mins_90(Map<String, Long> activityApparance, int[] durations,String[] names, ArrayList<MonitoredData>lines);
}
