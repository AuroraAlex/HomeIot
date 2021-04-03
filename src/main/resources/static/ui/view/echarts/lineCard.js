function getQueryVariable(variable)
		{
			var query = window.location.search.substring(1);
			var vars = query.split("&");
			for (var i=0;i<vars.length;i++) {
				var pair = vars[i].split("=");
				if(pair[0] == variable){return pair[1];}
			}
			return(false);
		}
		
var did = getQueryVariable("did");

layui.use(['popup'],function() {
	var $ = layui.$;
	var now = document.getElementById('now');
	var popup = layui.popup;
	var line = echarts.init(document.getElementById('line'));
	const colorList = ["#9E87FF", '#73DDFF', '#fe9a8b', '#F56948', '#9E87FF']
	console.log(did);
	//line
	var option_line = {
		color:colorList,
	    legend: {},
	    tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'cross',
				label: {
					backgroundColor: '#6a7985'
				}
			}	
		},
		grid: {
			left: '3%',
			right: '4%',
			bottom: '3%',
			containLabel: true
		},
	    dataset: {
	        // 提供一份数据。
	        source: []
	    },
		toolbox: {
			show: true, //是否显示工具箱
			right: 50,
			//要显示的工具箱内容
			feature: {
				dataView: {//数据视图
					show: true
				},
				dataZoom: {//数据区域缩放
					show: true
				},
				saveAsImage: {//保存为图片
					show: true
				},
				magicType: {//动态类型切换
					type: ['bar','line']
				}
			}
		},
	    // 声明一个 X 轴，类目轴（category）。默认情况下，类目轴对应到 dataset 第一列。
	    xAxis: {
			type: 'category',
			axisLine:{//坐标轴轴线相关设置
				lineStyle:{
					color:'#aaaaaa',
					// opacity:0.2
				}
			},
			splitLine: { //坐标轴在 grid 区域中的分隔线。
				show: true,
				lineStyle: {
					color: '#E5E9ED',
				// 	opacity:0.1
				}
			},
			axisLabel: {  
			   interval:0,  
			   rotate:40  
			}  
			},
	    // 声明一个 Y 轴，数值轴。
	    yAxis: {
			type: 'value',
			splitNumber: 5,
			axisLabel: {
				textStyle: {
					color: '#a8aab0',
					fontStyle: 'normal',
					fontFamily: '微软雅黑',
					fontSize: 12,
				}
			},
			axisLine:{
				show: false
			},
			axisTick:{
				show: false
			},
			splitLine: {
				show: true,
				lineStyle: {
					color: '#E5E9ED',
				// 	opacity:0.1
				}
			}

			},
	    // 声明多个 bar 系列，默认情况下，每个系列会自动对应到 dataset 的每一列。
	    series: []
	};
	
	
	
	function setData(data,count){
		var i;
		var res=[];
		for(i=0;i<count;i++){
			res.push(JSON.parse(data[i]));
		}
		return res;
	}
	
	function setSeries(data){
		var i;
		var res=[];
		for(i=0;i<Object.keys(JSON.parse(data[0])).length-1;i++){
			res.push({
				type:'line',
				// stack:'T',
				 emphasis: {
					focus: 'series'
				},
				markPoint: {
					data: [
						{type: 'max', name: '最大值'},
						{type: 'min', name: '最小值'}
					]
				},
				 markLine: {
					data: [
						{type: 'average', name: '平均值'}
					]
				}
				});
		}
		console.log(res);
		return res;
	}
	
	function makeNow(data,count,now){
		var i;
		var json;
		json = JSON.parse(data[count-1]);
		for (var key in json)
		{
			var item = document.createElement("p");
            item.appendChild(document.createTextNode(key+"  "));
			item.className = "title";
			item.style = "display: inline; white-space:pre;color:#9E87FF;"
			var item2 = document.createElement("p");
            item2.appendChild(document.createTextNode(json[key]+"   "));
			item2.className = "footer";
			item2.style = "display: inline; white-space:pre;color:#888888;"
            now.appendChild(item);	
			now.appendChild(item2);	
		}
	}
	
	
	
	$.ajax({
		url: "/getOneDayData?did="+did,
		type: "GET",
		success: function(data){
			console.log(data);
			if(data.msg==="OK")
			{
				if(data.count === 0)
				{
					 popup.failure("无数据")
				}else{
					line.setOption(option_line);
					line.setOption({
						dataset:{
							source:setData(data.data,data.count)
						},
						series:setSeries(data.data)
					});
					makeNow(data.data,data.count,now);
				
				}
				// console.log(setData(data.data,data.count));
				// console.log(setSeries(data.data));
				
	
			}else {
			   
			}
		},
		error:function (data) {
			console.log(data);
		}
	});
	
	

	window.onresize = function() {
		line.resize();
	}
	
	
	})
	