package ro.utcn.tp.Assig5.interfaces;

import java.util.ArrayList;

import ro.utcn.tp.Assig5.model.ActivitiesEachDay;
import ro.utcn.tp.Assig5.model.DetailedMonitoredData;

public interface ActivityCountForEachDay {
	public ActivitiesEachDay[] countActivitiesForEachDay(ArrayList<DetailedMonitoredData>datas,int nrOfDays);
}
