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
const connectUrl = 'ws://192.168.220.128:8083/mqtt'
const client = mqtt.connect(connectUrl, options)

client.on('reconnect', (error) => {
    console.log('正在重连:', error)
})

//建立连接
client.on('connect', function () {
  console.log("connect success!")
 
})
//订阅主题
client.subscribe(['test','test1','test2'], function (err) {
if (!err) {
  console.log("subscribe success!")
  //发布主题presence,消息内容为Hello mqtt
}else{
//打印错误
  console.log(err)
}
})

client.on('error', (error) => {
    console.log('连接失败:', error)
})

client.on('message', (topic, message) => {
  console.log('收到消息：', topic, message.toString())
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



layui.use(['popup'],function() {
	var $ = layui.$;
	var popup = layui.popup;
	var now = document.getElementById('now');
	function init(){
		$.ajax({
			url: "/getKeys?did="+did,
			type: "GET",
			success: function(data){
				console.log(data);
				if(data.msg==="OK")
				{
					
				}else {
				   
				}
			},
			error:function (data) {
				console.log(data);
			}
		});
		
	}
	
	init();
	
	while(true){
		
	}
	
})