package ro.utcn.tp.Assig5.interfaces;

import java.util.ArrayList;

import ro.utcn.tp.Assig5.model.DetailedMonitoredData;

public interface RecordDurationForEachLine {
	public int[] calculateDuration(ArrayList<DetailedMonitoredData>datas);
}
