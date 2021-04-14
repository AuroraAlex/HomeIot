// 连接选项
const options = {
      clean: true, // true: 清除会话, false: 保留会话
      connectTimeout: 4000, // 超时时间
      // 认证信息
      clientId: 'js',
      username: '',
      password: '',
}

var flag = false;

// 连接字符串, 通过协议指定使用的连接方式
// ws 未加密 WebSocket 连接
// wss 加密 WebSocket 连接
// mqtt 未加密 TCP 连接
// mqtts 加密 TCP 连接
// wxs 微信小程序连接
// alis 支付宝小程序连接
const connectUrl = 'ws://192.168.43.23:8083/mqtt'
const client = mqtt.connect(connectUrl, options)

client.on('reconnect', (error) => {
    console.log('正在重连:', error)
})

//建立连接
client.on('connect', function () {
  console.log("connect success!")
 
})


client.on('error', (error) => {
    console.log('连接失败:', error)
})


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



layui.use(['form','popup','element'],function() {
	var $ = layui.$;
	var popup = layui.popup;
	var form = layui.form;
	var element = layui.element;
	
	client.on('message', (topic, message) => {
	  console.log('收到消息：', topic, message.toString())
	  json = JSON.parse(message.toString());
	  for(var key in json){
		  $('#'+key).text(json[key]);
		  console.log(json[key]);
	  }
	  $('#status').css("background-color","lightgreen");
	})
	
	
	onClick = function (id){
		console.log(id);
		var str = '#'+id;
		$('#status').css("background-color","red");
		if($(str).text()==='ON'){
			client.publish('cmd/'+did, '{\"' +id+'\":0}');
		}else{
			client.publish('cmd/'+did, '{\"'+id+'\":1}');
		}
		
		
		 
	}
	
	
	
	function makeSwitch(data,count){
		var i;
		for(i=0;i<count;i++){
			$("#now").append("<label>"+data[i].toString()+"&ensp;&ensp;</label>");
			$("#now").append("<button class='pear-btn pear-btn-primary pear-btn-sm' onclick=\"onClick('"+data[i].toString()+"')\" id='"+data[i].toString()+"' >OFF</button><br>");
			$("#now").append("<hr class='layui-border-blue'>");
		}
		
	}
	

	
	function init(){
		
		//订阅主题
		client.subscribe(['status/'+did], function (err) {
		if (!err) {
		  console.log("subscribe success!")
		  //发布主题presence,消息内容为Hello mqtt
		}else{
		//打印错误
		  console.log(err)
		}
		})
		
		$.ajax({
			url: "/getKeys?did="+did,
			type: "GET",
			success: function(data){
				console.log(data);
				if(data.msg==="OK")
				{
					client.publish('cmd/'+did, '\{\"cmd\":1\}');
					makeSwitch(data.data,data.count);
				}else {
				   
				}
			},
			error:function (data) {
				console.log(data);
			}
		});
		
		
	}
	
	init();
	
	
})