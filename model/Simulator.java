package ro.utcn.tp.Assig5.model;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.sql.DataSource;
import javax.swing.plaf.synth.SynthSpinnerUI;
import javax.swing.text.html.MinimalHTMLWriter;

import ro.utcn.tp.Assig5.interfaces.ActivityCountForEachDay;
import ro.utcn.tp.Assig5.interfaces.CountAtivities;
import ro.utcn.tp.Assig5.interfaces.CountDays;
import ro.utcn.tp.Assig5.interfaces.LastTask_90;
import ro.utcn.tp.Assig5.interfaces.RecordDurationForEachLine;
import ro.utcn.tp.Assig5.interfaces.RecordDurationOfEachActivity;

public class Simulator {
	
	public Simulator() {
		List<String> fileContent=null;
		try {
			fileContent=FileReader.readFile();
			for (String s:fileContent) {
			//	System.out.println(s);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<MonitoredData> simple=createAListOfMonitoredData(fileContent);
		for (MonitoredData m:simple) {
			String s;
			s=m.getActivityLabel().replaceAll("	", "");
			m.setActivityLabel(s);
					
		}
		ArrayList<DetailedMonitoredData> complex=createDatasMoreDetailed(simple);
		int nrDays=countDays.countDays(complex);
		System.out.println("--------------------");
		System.out.println("Total number of days:"+nrDays);
		System.out.println("--------------------");
		Map<String, Long> activities= countEachActivity.countActivityApparance(simple);
		System.out.println("Map of activities:");
		System.out.println("-----------------");
		System.out.println(activities);
		System.out.println("----------------------------------------------------------------------------------------------");
		ActivitiesEachDay[] days=countActivitiesforDays.countActivitiesForEachDay(complex, nrDays);
		System.out.println("Counting activities each day:");
		System.out.println("-----------------------------");
		for (int i=0;i<days.length;i++) {
			System.out.println("Day:"+days[i].getDay());
			System.out.println("Leaving: "+days[i].getLeaving());
			System.out.println("Toileting: "+days[i].getToiletting());
			System.out.println("Showering: "+days[i].getShowering());
			System.out.println("Sleeping: "+days[i].getSleeping());
			System.out.println("Breakfast: "+days[i].getBreakfast());
			System.out.println("Lunch: "+days[i].getLaunch());
			System.out.println("Dinner : "+days[i].getDinner());
			System.out.println("Snack: "+days[i].getSnack());
			System.out.println("Spare_Time/TV: "+days[i].getSpare_TimeTV());
			System.out.println("Grooming: "+days[i].getGrooming());
			System.out.println();
		}
		System.out.println("Duration for each line:");
		System.out.println("----------------------");
		int times[]=recordDurationForEachLine.calculateDuration(complex);
		for (int i=0;i<times.length;i++)
			System.out.println(simple.get(i).getActivityLabel()+": "+times[i]+" seconds");
		System.out.println("--------------");
		System.out.println("Duration of each activity:");
		System.out.println("---------------------------");
		String header[]= {"Leaving","Toileting","Showering","Sleeping","Breakfast","Lunch","Dinner","Snack","Spare_Time/TV","Grooming"};
		int[] durationOfEach=actRec.durationOfActivities(times, simple);
		for (int i=0;i<10;i++) {
			System.out.println(header[i]+": "+durationOfEach[i]);
		}
		System.out.println("---------------------------------------------------------------------");
		System.out.println("The activities that have 90% of records less than 5 minutes:");
		ArrayList<String> strings=task.Less_5_mins_90(activities, times, header, simple);
		for (String s:strings) {
			System.out.println(s);
		}
	}
	public int DayNrOfMonth(int monthNR) {
		if (monthNR==1 || monthNR==3 || monthNR==5 ||monthNR==7 ||monthNR==8 ||monthNR==10 ||monthNR==12)
			return 31;
		else if (monthNR==2)
			return 28;
		else return 30;
	}
	public ArrayList<MonitoredData> createAListOfMonitoredData(List<String> content) {
		ArrayList<MonitoredData> monitoredDatas=new ArrayList<MonitoredData>();
		String[] smallStrings=null;
		for (String s:content) {
			smallStrings=s.split("		");
			smallStrings[2]=smallStrings[2].replaceAll(" ","");
			MonitoredData m=new MonitoredData(smallStrings[0], smallStrings[1], smallStrings[2]);
			monitoredDatas.add(m);
		}
		return monitoredDatas;
		
	}
	public ArrayList<DetailedMonitoredData> createDatasMoreDetailed(ArrayList<MonitoredData> datas) {
		ArrayList<DetailedMonitoredData> detailedDatas=new ArrayList<DetailedMonitoredData>();
		for (MonitoredData m:datas) {
			String toSplitbegin[]=m.getStartTime().split(" ");
			String toSplitend[]=m.getEndTime().split(" ");
			String[] beginDate=toSplitbegin[0].split("-");
			String[] beginHour=toSplitbegin[1].split(":");
			String[] endDate=toSplitend[0].split("-");
			String[] endHour=toSplitend[1].split(":");
			int beginYear=Integer.parseInt(beginDate[0]);
			int beginMonth=Integer.parseInt(beginDate[1]);
			int beginDay=Integer.parseInt(beginDate[2]);
			int beginHoour=Integer.parseInt(beginHour[0]);
			int beginMins=Integer.parseInt(beginHour[1]);
			int beginSecs=Integer.parseInt(beginHour[2]);
			
			int endYear=Integer.parseInt(endDate[0]);
			int endMonth=Integer.parseInt(endDate[1]);
			int endDay=Integer.parseInt(endDate[2]);
			int endHoour=Integer.parseInt(endHour[0]);
			int endMins=Integer.parseInt(endHour[1]);
			int endSecs=Integer.parseInt(endHour[2]);
			detailedDatas.add(new DetailedMonitoredData(beginYear, beginMonth, beginDay, beginHoour, beginMins, beginSecs, endYear, endMonth, endDay, endHoour, endMins, endSecs,m.getActivityLabel()));
		}
		return detailedDatas;
	}

	CountDays countDays=(ArrayList<DetailedMonitoredData> datas)->{
		
		int days=0;
		days=(int) datas.stream().mapToInt(p->p.getBeginDay()).distinct().count();
		return days;
	};

	public int p(ArrayList<MonitoredData> datas,String name) {
		int cnt=(int) datas.stream().filter(m->m.getActivityLabel().equals(name)).count();
		return cnt;
	}
	CountAtivities countEachActivity=(ArrayList<MonitoredData> datas)->{
		Map<String,Long> activityMap =new HashMap<String,Long>();
	/*	activityMap=datas.stream().collect(Collectors.toMap(s->MonitoredData::getActivityLabel,Collectors.groupingBy()));*/
		/*int leaving=0, toiletting = 0, showering=0, sleeping=0, breakfast=0, launch=0, dinner=0, snack=0, Spare_TimeTV=0, grooming=0;
		for (MonitoredData d:datas) {
			if (d.getActivityLabel().equals("Leaving"))
				leaving++;
			if (d.getActivityLabel().equals("Toileting"))
				toiletting++;
			if (d.getActivityLabel().equals("Showering"))
				showering++;
			if (d.getActivityLabel().equals("Sleeping"))
				sleeping++;
			if (d.getActivityLabel().equals("Breakfast"))
				breakfast++;
			if (d.getActivityLabel().equals("Lunch"))
				launch++;
			if (d.getActivityLabel().equals("Dinner"))
				dinner++;
			if (d.getActivityLabel().equals("Snack"))
				snack++;
			if (d.getActivityLabel().equals("Spare_Time/TV"))
				Spare_TimeTV++;
			if (d.getActivityLabel().equals("Grooming"))
				grooming++;
			}
			
		activityMap.put("Leaving", leaving);
		activityMap.put("Toileting", toiletting);
		activityMap.put("Showering", showering);
		activityMap.put("Sleeping", sleeping);
		activityMap.put("Breakfast",breakfast);
		activityMap.put("Lunch",launch);
		activityMap.put("Dinner", dinner);
		activityMap.put("Snack",snack );
		activityMap.put("Spare_Time/TV",Spare_TimeTV);
		activityMap.put("Grooming",grooming);*/
		
		activityMap= datas.stream().collect(Collectors.groupingBy(s->s.getActivityLabel(),Collectors.counting()));
		return activityMap;
	};
	ActivityCountForEachDay countActivitiesforDays=(ArrayList<DetailedMonitoredData>datas,int nrDays)->{
		ActivitiesEachDay[] days = new ActivitiesEachDay[nrDays];
		int p=28;
		for (int i=0;i<3;i++)
			days[i]=new ActivitiesEachDay(11,p++);
		p=1;
		for (int i=3;i<nrDays;i++) {
			days[i]=new ActivitiesEachDay(12,p++);
		}
		for (DetailedMonitoredData d:datas) {
			for (int i=0;i<nrDays;i++) {
				if (d.getBeginDay()==days[i].getDay()||d.getEndDay()==days[i].getDay()) {
					if (d.getActivityName().equals("Leaving"))
						days[i].setLeaving(days[i].getLeaving()+1);
					if (d.getActivityName().equals("Toileting"))
						days[i].setToiletting(days[i].getToiletting()+1);
					if (d.getActivityName().equals("Showering"))
						days[i].setShowering(days[i].getShowering()+1);
					if (d.getActivityName().equals("Sleeping"))
						days[i].setSleeping(days[i].getSleeping()+1);
					if (d.getActivityName().equals("Breakfast"))
						days[i].setBreakfast(days[i].getBreakfast()+1);
					if (d.getActivityName().equals("Lunch"))
						days[i].setLaunch(days[i].getLaunch()+1);
					if (d.getActivityName().equals("Dinner"))
						days[i].setDinner(days[i].getDinner()+1);
					if (d.getActivityName().equals("Snack"))
						days[i].setSnack(days[i].getSnack()+1);
					if (d.getActivityName().equals("Spare_Time/TV"))
						days[i].setSpare_TimeTV(days[i].getSpare_TimeTV()+1);
					if (d.getActivityName().equals("Grooming"))
						days[i].setGrooming(days[i].getGrooming()+1);
				}
			}
		}
		return days;
		
	};
	RecordDurationForEachLine recordDurationForEachLine=(ArrayList<DetailedMonitoredData>datas)->{
		int[] times=new int[datas.size()];
		
		int i=0;
		for (DetailedMonitoredData d:datas) {
			int hour = 0;
			int min = 0;
			int sec = 0;
			if (d.getBeginHour()<d.getEndHour())
				hour=d.getEndHour()-d.getBeginHour();
			else if(d.getBeginHour()>d.getEndHour())
				hour=(24-d.getBeginHour())+d.getEndHour();
			else {
				hour=0;
			}
			if (d.getBeginMins()<d.getEndMins())
				min=d.getEndMins()-d.getBeginMins();
			else if(d.getBeginMins()>d.getEndMins()) 
				min=(60-d.getBeginMins())+d.getEndMins();
			else {
				min=0;
			}
			if (d.getBeginSecs()<d.getEndSecs())
				sec=d.getEndSecs()-d.getBeginSecs();
			else if(d.getBeginSecs()>d.getEndSecs()) 
				sec=(60-d.getBeginSecs())+d.getEndSecs();
			else {
				sec=0;
			}
			if (d.getBeginMins()>d.getEndMins())
				--hour;
			if (d.getBeginSecs()>d.getEndSecs())
				--min;
			int time=3600*hour+60*min+sec;
			times[i++]=time;
		}
		return times;
	};
	RecordDurationOfEachActivity actRec=(int [] durations,ArrayList<MonitoredData>datas)->{
		int[] actDurations=new int[10];
		for (int i=0;i<10;i++)
			actDurations[i]=0;
		for (int i=0;i<datas.size();i++) {
			if (datas.get(i).getActivityLabel().equals("Leaving"))
				actDurations[0]+=durations[i];
			if (datas.get(i).getActivityLabel().equals("Toileting"))
				actDurations[1]+=durations[i];
			if (datas.get(i).getActivityLabel().equals("Showering"))
				actDurations[2]+=durations[i];
			if (datas.get(i).getActivityLabel().equals("Sleeping"))
				actDurations[3]+=durations[i];
			if (datas.get(i).getActivityLabel().equals("Breakfast"))
				actDurations[4]+=durations[i];
			if (datas.get(i).getActivityLabel().equals("Lunch"))
				actDurations[5]+=durations[i];
			if (datas.get(i).getActivityLabel().equals("Dinner"))
				actDurations[6]+=durations[i];
			if (datas.get(i).getActivityLabel().equals("Snack"))
				actDurations[7]+=durations[i];
			if (datas.get(i).getActivityLabel().equals("Spare_Time/TV"))
				actDurations[8]+=durations[i];
			if (datas.get(i).getActivityLabel().equals("Grooming"))
				actDurations[9]+=durations[i];
			}
		
		
		return actDurations;
	};
	LastTask_90 task=(Map<String, Long> activityApparance, int[] durations,String[]names, ArrayList<MonitoredData> lines)->{
		ArrayList<String> toReturned=new ArrayList<String>();
		for (int i=0;i<names.length;i++) {
			
			int cnt=0;
			Long totalApparance=activityApparance.get(names[i]);
			for (int j=0;j<durations.length;j++) {
				if (lines.get(j).getActivityLabel().equals(names[i]))
				{
					if (durations[j]<300)
						cnt++;
				}
			}
			if (totalApparance!=null) {
				if (cnt>=totalApparance*9/10 && totalApparance>0)
					toReturned.add(names[i]);
			}
			
		}
		return toReturned;
		};

	
}
