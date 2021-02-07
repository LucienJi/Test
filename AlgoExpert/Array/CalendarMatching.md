# Calendar Matching

真的难，两个日历表明的是被占用的时间，然后给定需要的开会时长，输入所有可能的creneau

```
{
  "calendar1": [["9:00", "10:30"], ["12:00", "13:00"], ["16:00", "18:00"]],
  "dailyBounds1": ["9:00", "20:00"],
  "calendar2": [
    ["10:00", "11:30"],
    ["12:30", "14:30"],
    ["14:30", "15:00"],
    ["16:00", "17:00"]
  ],
  "dailyBounds2": ["10:00", "18:30"],
  "meetingDuration": 30
}
```

# 核心思想

1. 真的是自定义类，自定义转换

# Code

```cpp
#include <vector>

using namespace std;

struct StringMeeting {
  string start;
  string end;
};

struct Meeting{
	int start;
	int end;
};

vector<Meeting> updateCalendar(vector<StringMeeting> calendar, StringMeeting dailyBounds); 
// 加上开始工作，结束工作的时间
vector<Meeting> mergeCalendars(vector<Meeting> calendar1, vector<Meeting> calendar2);
// 合并两个日历
vector<Meeting> flattenCalendars(vector<Meeting> calendars);
// 将有重合的unavailable的时间合并
vector<StringMeeting> getMatchAvailities(vector<Meeting> calendar, int meetingDuration);
// 将 unavailable 日历中挑出来一个duration不会再calendar中

int timeToMinutes(string time);
string minutesToTime(int minutes);

vector<StringMeeting> calendarMatching(vector<StringMeeting> calendar1,
                                       StringMeeting dailyBounds1,
                                       vector<StringMeeting> calendar2,
                                       StringMeeting dailyBounds2,
                                       int meetingDuration) {
  // Write your code here.
	vector<Meeting> updatedCalendars1 = updateCalendar(calendar1,dailyBounds1);
	vector<Meeting> updatedCalendars2 = updateCalendar(calendar2,dailyBounds2);
	vector<Meeting> mergedCalendar = mergeCalendars(updatedCalendars1,updatedCalendars2);
	vector<Meeting> flattenedCalendar = flattenCalendars(mergedCalendar);
	
  return getMatchAvailities(flattenedCalendar,meetingDuration);
}

vector<Meeting> updateCalendar(vector<StringMeeting> calendar, StringMeeting dailyBounds){
	vector<StringMeeting> updatedCalendar;
	updatedCalendar.push_back({"0:00",dailyBounds.start});
	updatedCalendar.insert(updatedCalendar.end(),calendar.begin(),calendar.end());
	updatedCalendar.push_back({dailyBounds.end,"23:59"});
	vector<Meeting> calendarinminutes;
	for(int i = 0;i<updatedCalendar.size();i++){
		calendarinminutes.push_back({timeToMinutes(updatedCalendar[i].start),timeToMinutes(updatedCalendar[i].end)});
	}
	
	return calendarinminutes;
} 

vector<Meeting> mergeCalendars(vector<Meeting> calendar1,vector<Meeting> calendar2){
	vector<Meeting> merged;
	int i = 0;
	int j = 0;
	while( i < calendar1.size() && j < calendar2.size()){
		// 按照开始的时间来排序
		Meeting meeting1 = calendar1[i];
		Meeting meeting2 = calendar2[j];
		if(meeting1.start < meeting2.start){
			merged.push_back(meeting1);
			i++;
		}else{
			merged.push_back(meeting2);
			j++;
		}
	}
	
	while(i < calendar1.size()){
		merged.push_back(calendar1[i++]);
	}
	while(j<calendar2.size()){
		merged.push_back(calendar2[j++]);
	}
	return merged;
}

vector<Meeting> flattenCalendars(vector<Meeting> calendar){
	vector<Meeting> flattened = {calendar[0]};
	for(int i = 1;i<calendar.size();i++){
		Meeting currentMeeting = calendar[i]; // to be added
		Meeting previousMeeting = flattened[flattened.size()-1]; // last element added
		if(previousMeeting.end >= currentMeeting.start){
			// 说明时间重叠了，我们可以合并这两个
			Meeting newMeeting = {previousMeeting.start,max(previousMeeting.end,currentMeeting.end)};
			flattened[flattened.size()-1] = newMeeting;
		}else{
			// 无重叠，直接加
		flattened.push_back(currentMeeting);
		}
		
		
	}
	return flattened;
}

vector<StringMeeting> getMatchAvailities(vector<Meeting> calendar, int meetingDuration){
	vector<Meeting> available;
	// 0 是 boundary
	for(int i = 1; i < calendar.size();i++){
		// 以他人的结束为开始
		int start = calendar[i-1].end;
		int end = calendar[i].start;
		int dure = end - start;
		if(dure >= meetingDuration){
			available.push_back({start,end});
		}
	}
	
	vector<StringMeeting> res ;
	for (int i = 0;i<available.size();i++){
		res.push_back({minutesToTime(available[i].start),minutesToTime(available[i].end)});
	}
	
	return res;

}

int timeToMinutes(string time){
	int delimiter = time.find(":");
	int hours = stoi(time.substr(0,delimiter));
	int minutes = stoi(time.substr(delimiter+1,time.length()));
	return hours*60 + minutes;
}

string minutesToTime(int minutes){
	int h = minutes / 60;
	int m = minutes % 60;
	string hstring = to_string(h);
	string minstring = m < 10 ? "0" + to_string(m) : to_string(m);
	return hstring + ":" + minstring;
}

```