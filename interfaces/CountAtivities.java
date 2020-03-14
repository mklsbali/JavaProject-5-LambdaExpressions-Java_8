package ro.utcn.tp.Assig5.interfaces;

import java.util.ArrayList;
import java.util.Map;

import ro.utcn.tp.Assig5.model.MonitoredData;

public interface CountAtivities {
	public Map<String,Long> countActivityApparance(ArrayList<MonitoredData> datas);
}
