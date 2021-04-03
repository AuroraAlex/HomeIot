var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};

var option;

option = {
	series: [{
			type: 'gauge',
			center: ["50%", "60%"],
			startAngle: 200,
			endAngle: -20,
			min: 0,
			max: 60,
			splitNumber: 12,
			itemStyle: {
				color: '#FFAB91'
			},
			progress: {
				show: true,
				width: 30
			},

			pointer: {
				show: false,
			},
			axisLine: {
				lineStyle: {
					width: 30
				}
			},
			axisTick: {
				distance: -45,
				splitNumber: 5,
				lineStyle: {
					width: 2,
					color: '#999'
				}
			},
			splitLine: {
				distance: -52,
				length: 14,
				lineStyle: {
					width: 3,
					color: '#999'
				}
			},
			axisLabel: {
				distance: -20,
				color: '#999',
				fontSize: 20
			},
			anchor: {
				show: false
			},
			title: {
				show: false
			},
			detail: {
				valueAnimation: true,
				width: '60%',
				lineHeight: 40,
				height: '15%',
				borderRadius: 8,
				offsetCenter: [0, '-15%'],
				fontSize: 40,
				fontWeight: 'bolder',
				formatter: '{value} °C',
				color: 'auto'
			},
			data: [{
				value: 20
			}]
		},

		{
			type: 'gauge',
			center: ["50%", "60%"],
			startAngle: 200,
			endAngle: -20,
			min: 0,
			max: 60,
			itemStyle: {
				color: '#FD7347',
			},
			progress: {
				show: true,
				width: 8
			},

			pointer: {
				show: false
			},
			axisLine: {
				show: false
			},
			axisTick: {
				show: false
			},
			splitLine: {
				show: false
			},
			axisLabel: {
				show: false
			},
			detail: {
				show: false
			},
			data: [{
				value: 20,
			}]

		}
	],
};



if (option && typeof option === 'object') {
	myChart.setOption(option);
}


layui.use(['echarts'], function() {
	var $ = layui.$;
	var line = echarts.init(document.getElementById('line'));
	const colorList = ["#9E87FF", '#73DDFF', '#fe9a8b', '#F56948', '#9E87FF']
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
	        source:[]
            
	    },
	    // 声明一个 X 轴，类目轴（category）。默认情况下，类目轴对应到 dataset 第一列。
	    xAxis: {type: 'category'},
	    // 声明一个 Y 轴，数值轴。
	    yAxis: {type: 'value'},
	    // 声明多个 bar 系列，默认情况下，每个系列会自动对应到 dataset 的每一列。
	    series: []
	};
	
	function getData(data){
		let i;
		var res =[];
		var series=[];
		for(i=0;i<Object.keys(data).length;i++)
		{
			res.push(data[i].payload);
			series.push({
				type:'line',
				stack:'line',
				areaStyle: {},
				 emphasis: {
					focus: 'series'
				}
			});
		} 
		option_line.dataset.source = res;
		option_line.series = series;
		
	}
	

	
	$.ajax({
		url: "/getOneDayData",
		type: "GET",
		data:{"did":"89cac68c4497466c8e36e92f1207e97c"},
		success: function(data){
			console.log(data);
			if(data.msg==="OK")
			{
				getData(data.data);
				console.log(option_line.series);
				console.log(option_line.dataset.source);
	
			}else {
			   
			}
		},
		error:function (data) {
			console.log(data);
		}
	});
	
	setInterval(function() {
		let random = (Math.random() * 60).toFixed(2) - 0;
		option.series[0].data[0].value = random;
		option.series[1].data[0].value = random;
		myChart.setOption(option, true);
	}, 2000);
	
	line.setOption(option_line);
	window.onresize = function() {
		line.resize();
	}
	
	
	})
	