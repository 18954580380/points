//自定义事件格式化
    function timetostr(date){
		var time=new Date(date);
		var str="";
		var month=time.getMonth()+1;
		if(month<10){
			month="0"+month
		}
		var day=time.getDate();
		if(day<10){
			day="0"+day
		}
		str=time.getFullYear()+"-"+month+"-"+day;
		return str;
	}
