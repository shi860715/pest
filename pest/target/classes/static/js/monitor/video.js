var gAppKey; // app key
var gAccessToken; // accesstoken
var gPlaybackSearchRecord; // search record
function checkBrowser() {
	var userAgent = navigator.userAgent; // 取得浏览器的userAgent字符串
	if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1) {
		return true;
	}
	return false;
}
function init() {
	var flag = checkBrowser();
	if (flag) {
		TestActiveX();
		var text = '{ \n'
				+ ' "AppKey":"efb627cdda53458f9e189bb1ff9396b6", \n'
				+ ' "AccessToken":"at.4dmq4nbadtojjk07avpgnheu5igtdz9b-77at8x3o46-1axogzp-lufk7ufpl", \n'
				+ ' "Url":"ezopen://open.ys7.com/105017968/1.hd.live" \n}';
		var showpanel = document.getElementById("dataPanel"); // 获取显示的窗口

		showpanel.value = text;
		document.getElementById('starttalkbtn').style.display = 'none';
		document.getElementById('stoptalkbtn').style.display = 'none';
		document.getElementById('ptzupbtn').style.display = 'none';
		document.getElementById('ptzdownbtn').style.display = 'none';
		document.getElementById('ptzleftbtn').style.display = 'none';
		document.getElementById('ptzrightbtn').style.display = 'none';
	} else {
		alert('该功能目前只支持IE浏览器或带兼容模式的浏览器！');
	}
}

// 预览函数
function StartPlay() {
	gPlaybackSearchRecord = "";
	// 得到控件引用
	var playOcx = document.getElementById("EZUIKit");
	if (!playOcx) {
		alert("EZUIKit can't find!");
		return;
	}
	// 获取取流参数
	var streamparams = document.getElementById("dataPanel").value;
	var streamobj = JSON.parse(streamparams);
	if (!streamobj) {
		alert("Get stream params invalid!");
		return;
	}
	var appkey = streamobj.AppKey;
	var accesstoken = streamobj.AccessToken;
	var ezurl = streamobj.Url;
	// 检测取流参数
	if (appkey == "") {
		alert("Appkey is empty!");
		return;
	}
	if (accesstoken == "") {
		alert("Accesstoken is empty!");
		return;
	}
	if (ezurl == "") {
		alert("EzUrl is empty!");
		return;
	}
	// alert(appkey);
	// alert(accesstoken);
	// alert(ezurl);
	// 设置appkey
	// 判断参数是否初始化过
	if (gAppKey != appkey) {
		var res = playOcx.InitWithAppKey(appkey);
		if (0 != res) {
			alert("Init appkey Error!");
			return;
		}
		gAppKey = appkey;
		// alert("Init appkey success.");
	}
	// 设置
	if (gAccessToken != accesstoken) {
		var res = playOcx.SetAccessToken(accesstoken);
		if (0 != res) {
			alert("Init accesstoken Error!");
			return;
		}
		gAccessToken = accesstoken;
		// alert("Init accesstoken success.");
	}
	// 清理播放结果窗口
	var showpanel = document.getElementById("showPanel"); // 获取显示的窗口
	showpanel.value = "";
	// 开始播放, 播放结果 根据 PluginEventHandler 回调函数
	var res = playOcx.StartPlay(ezurl);
	if (0 != res) {
		alert("startplay failed! check ezurl!");
		return;
	}
	// alert("Startplay success.");
}

function StopPlay() {
	// 清理播放结果窗口
	var showpanel = document.getElementById("showPanel"); // 获取显示的窗口
	showpanel.value = "";
	var playOcx = document.getElementById("EZUIKit");// 得到控件引用
	var res = playOcx.StopPlay();
	if (0 != res) {
		alert("StopPlay Error！");
	}
}

function CapturePicture() {
	var picNameText = document.getElementById("picturename"); // 获取picture
																// name 输入框
	if (picNameText.value == "") {
		alert("please input the name of picture.");
		return;
	}
	var picname = picNameText.value;
	var playOcx = document.getElementById("EZUIKit");// 得到控件引用
	var res = playOcx.CapturePicture(picname);
	if (res == "") {
		alert("capture picture Error！");
	} else {
		alert("picture save at " + res);
	}
}

function StartTalk() {
	var playOcx = document.getElementById("EZUIKit");// 得到控件引用
	var res = playOcx.StartTalk();
	if (0 != res) {
		alert("StartTalk Error！");
	}
}

function StopTalk() {
	var playOcx = document.getElementById("EZUIKit");// 得到控件引用
	var res = playOcx.StopTalk();
	if (0 != res) {
		alert("StopTalk Error！");
	}
}

// ptz command
var cmd_up = 0;
var cmd_down = 1;
var cmd_left = 2;
var cmd_right = 3;
// ptz action
var action_start = 0;
var action_stop = 1;
// ptz speed
var ptz_speed = 7;

function StartUpPTZCtrl() {
	var playOcx = document.getElementById("EZUIKit");// 得到控件引用
	var res = playOcx.PTZCtrl(cmd_up, action_start, ptz_speed);
	if (0 != res) {
		alert("StopTalk Error！");
	}
}

function StopUpPTZCtrl() {
	var playOcx = document.getElementById("EZUIKit");// 得到控件引用
	var res = playOcx.PTZCtrl(cmd_up, action_stop, ptz_speed);
	if (0 != res) {
		alert("StopTalk Error！");
	}
}

function StartDownPTZCtrl() {
	var playOcx = document.getElementById("EZUIKit");// 得到控件引用
	var res = playOcx.PTZCtrl(cmd_down, action_start, ptz_speed);
	if (0 != res) {
		alert("StopTalk Error！");
	}
}

function StopDownPTZCtrl() {
	var playOcx = document.getElementById("EZUIKit");// 得到控件引用
	var res = playOcx.PTZCtrl(cmd_down, action_stop, ptz_speed);
	if (0 != res) {
		alert("StopTalk Error！");
	}
}

function StartLeftPTZCtrl() {
	var playOcx = document.getElementById("EZUIKit");// 得到控件引用
	var res = playOcx.PTZCtrl(cmd_left, action_start, ptz_speed);
	if (0 != res) {
		alert("StopTalk Error！");
	}
}

function StopLeftPTZCtrl() {
	var playOcx = document.getElementById("EZUIKit");// 得到控件引用
	var res = playOcx.PTZCtrl(cmd_left, action_stop, ptz_speed);
	if (0 != res) {
		alert("StopTalk Error！");
	}
}

function StartRightPTZCtrl() {
	var playOcx = document.getElementById("EZUIKit");// 得到控件引用
	var res = playOcx.PTZCtrl(cmd_right, action_start, ptz_speed);
	if (0 != res) {
		alert("StopTalk Error！");
	}
}

function StopRightPTZCtrl() {
	var playOcx = document.getElementById("EZUIKit");// 得到控件引用
	var res = playOcx.PTZCtrl(cmd_right, action_stop, ptz_speed);
	if (0 != res) {
		alert("StopTalk Error！");
	}
}

function TestActiveX() {

	var ax;
	try {
		if (window.ActiveXObject) {
			ax = new ActiveXObject("EZOPENUIACTIVEXK.EzOpenUIActiveXKCtrl.1");
			bInstall = true;
		} else if (document.implementation
				&& document.implementation.createDocument) {
			try {
				ax = document.implementation.createDocument('', '', null);
				ax.async = false;
				bInstall = true;
			} catch (e) {
				var xmlhttp = new window.XMLHttpRequest();
				xmlhttp.send(null);
				ax = xmlhttp.responseXML;
			}
		} else {
			alert("load data error");
		}
	} catch (e) {
		alert(e.message);
	}

}

// handle message msgtype
var EZUI_MSGID_PLAY_EXCEPTION = 0; // 播放异常
var EZUI_MSGID_PLAY_RECONNECT = 1; // 播放重连
var EZUI_MSGID_PLAY_RECONNECT_EXCEPTION = 2; // 播放重连异常
var EZUI_MSGID_PLAY_START = 3; // 播放开始
var EZUI_MSGID_PLAY_STOP = 4; // 播放停止
var EZUI_MSGID_PLAY_ARCHIVE_END = 5; // 回放结束
var EZUI_MSGID_VOICETALK_START = 16; // 语音对讲开始
var EZUI_MSGID_VOICETALK_STOP = 17; // 语音对讲停止
var EZUI_MSGID_VOICETALK_EXCEPTION = 18; // 语音对讲异常
var EZUI_MSGID_RECORD_FILE = 20; // 查询的录像文件
var EZUI_MSGID_PTZCTRL_SUCCESS = 46; // 云台控制命令发送成功
var EZUI_MSGID_PTZCTRL_FAILED = 47; // 云台控制失败

var EZUI_ERROR_ACCESSTOKEN_EXPIRE = "UE001"; // /<
												// accesstoken异常或失效，需要重新获取accesstoken，并传入到sdk
var EZUI_ERROR_APPKEY_TOKEN_NOT_MATCH = "UE002"; // /<
													// appkey和AccessToken不匹配,建议更换appkey或者AccessToken
var EZUI_ERROR_CHANNEL_NOT_EXIST = "UE004"; // /< 通道不存在，设备参数错误，建议重新获取播放地址
var EZUI_ERROR_DEVICE_NOT_EXIST = "UE005"; // /< 设备不存在，设备参数错误，建议重新获取播放地址
var EZUI_ERROR_PARAM_INVALID = "UE006"; // /< 参数错误，建议重新获取播放地址
var EZUI_ERROR_EZOPEN_URL_INVALID = "UE007"; // /< 播放地址错误,建议重新获取播放地址
var EZUI_ERROR_NO_RESOURCE = "UE101"; // /< 设备连接数过大，停止其他连接后再试试
var EZUI_ERROR_DEVICE_OFFLINE = "UE102"; // /< 设备不在线，确认设备上线之后重试
var EZUI_ERROR_CONNECT_DEVICE_TIMEOUT = "UE103"; // /<
													// 播放失败，请求连接设备超时，检测设备网路连接是否正常.
var EZUI_ERROR_INNER_VERIFYCODE = "UE104"; // /< 视频验证码错误，建议查看设备上标记的验证码
var EZUI_ERROR_PLAY_FAIL = "UE105"; // /< 视频播放失败
var EZUI_ERROR_TERMINAL_BINDING = "UE106"; // /< 当前账号开启了终端绑定，只允许指定设备登录操作
var EZUI_ERROR_DEVICE_INFO_INVALID = "UE107"; // /< 设备信息异常为空，建议重新获取播放地址
var EZUI_ERROR_VIDEO_RECORD_NOTEXIST = "UE108"; // /< 未查找到录像文件
var EZUI_ERROR_VTDU_NO_RESOURCE = "UE109"; // /< 取流并发路数限制,请升级为企业版.
var EZUI_ERROR_UNSUPPORTED = "UE110"; // /< 设备不支持的清晰度类型, 请根据设备预览能力级选择.

function PluginEventHandler(lEventType, strErrorCode, lInterErrorCode) {

	var showpanel = document.getElementById("showPanel"); // 获取显示的窗口
	switch (lEventType) {
	case EZUI_MSGID_PLAY_START: // 播放开始
	{
		var info;
		if (gPlaybackSearchRecord != "") {
			info = "回放成功!" + gPlaybackSearchRecord;
		} else {
			info = "播放成功!";
		}
		showpanel.value = info;
		document.getElementById('startplaybtn').style.display = 'none';
		document.getElementById('stopplaybtn').style.display = 'block';
		document.getElementById('starttalkbtn').style.display = 'block';
		document.getElementById('stoptalkbtn').style.display = 'none';
		document.getElementById('ptzupbtn').style.display = 'block';
		document.getElementById('ptzdownbtn').style.display = 'block';
		document.getElementById('ptzleftbtn').style.display = 'block';
		document.getElementById('ptzrightbtn').style.display = 'block';
	}
		break;
	case EZUI_MSGID_PLAY_EXCEPTION: // 播放异常
	{
		var errinfo;
		if (strErrorCode == EZUI_ERROR_ACCESSTOKEN_EXPIRE) {
			errinfo = "accesstoken异常或失效，需要重新获取accesstoken，并传入到sdk";
		} else if (strErrorCode == EZUI_ERROR_APPKEY_TOKEN_NOT_MATCH) {
			errinfo = "ppkey和AccessToken不匹配,建议更换appkey或者AccessToken";
		} else if (strErrorCode == EZUI_ERROR_CHANNEL_NOT_EXIST) {
			errinfo = "通道不存在，设备参数错误，建议重新获取播放地址";
		} else if (strErrorCode == EZUI_ERROR_DEVICE_NOT_EXIST) {
			errinfo = "设备不存在，设备参数错误，建议重新获取播放地址";
		} else if (strErrorCode == EZUI_ERROR_PARAM_INVALID) {
			errinfo = "参数错误，建议重新获取播放地址";
		} else if (strErrorCode == EZUI_ERROR_EZOPEN_URL_INVALID) {
			errinfo = "播放地址错误,建议重新获取播放地址";
		} else if (strErrorCode == EZUI_ERROR_NO_RESOURCE) {
			errinfo = "设备连接数过大，停止其他连接后再试试";
		} else if (strErrorCode == EZUI_ERROR_DEVICE_OFFLINE) {
			errinfo = "设备不在线，确认设备上线之后重试";
		} else if (strErrorCode == EZUI_ERROR_CONNECT_DEVICE_TIMEOUT) {
			errinfo = "播放失败，请求连接设备超时，检测设备网路连接是否正常.";
		} else if (strErrorCode == EZUI_ERROR_INNER_VERIFYCODE) {
			errinfo = "视频验证码错误，建议查看设备上标记的验证码";
		} else if (strErrorCode == EZUI_ERROR_PLAY_FAIL) {
			errinfo = "视频播放失败";
		} else if (strErrorCode == EZUI_ERROR_TERMINAL_BINDING) {
			errinfo = "当前账号开启了终端绑定，只允许指定设备登录操作";
		} else if (strErrorCode == EZUI_ERROR_DEVICE_INFO_INVALID) {
			errinfo = "设备信息异常为空，建议重新获取播放地址";
		} else if (strErrorCode == EZUI_ERROR_VIDEO_RECORD_NOTEXIST) {
			errinfo = "未查找到录像文件";
		} else if (strErrorCode == EZUI_ERROR_VTDU_NO_RESOURCE) {
			errinfo = "取流并发路数限制,请升级为企业版.";
		} else if (strErrorCode == EZUI_ERROR_UNSUPPORTED) {
			errinfo = "设备不支持的清晰度类型, 请根据设备预览能力级选择";
		}

		var info = "播放失败," + errinfo + ".错误码:" + strErrorCode + ", 内部错误码:"
				+ lInterErrorCode;
		showpanel.value = info;
	}
		break;
	case EZUI_MSGID_PLAY_STOP: // 播放停止
	{
		document.getElementById('startplaybtn').style.display = 'block';
		document.getElementById('stopplaybtn').style.display = 'none';
		document.getElementById('starttalkbtn').style.display = 'none';
		document.getElementById('stoptalkbtn').style.display = 'none';
		document.getElementById('ptzupbtn').style.display = 'none';
		document.getElementById('ptzdownbtn').style.display = 'none';
		document.getElementById('ptzleftbtn').style.display = 'none';
		document.getElementById('ptzrightbtn').style.display = 'none';
	}
		break;
	case EZUI_MSGID_RECORD_FILE: // 录像搜索成功
	{
		gPlaybackSearchRecord = "录像搜索成功:" + strErrorCode;
	}
		break;
	case EZUI_MSGID_VOICETALK_START: // 对讲开启
	{
		var info = "对讲开启成功";
		showpanel.value = info;
		document.getElementById('starttalkbtn').style.display = 'none';
		document.getElementById('stoptalkbtn').style.display = 'block';
	}
		break;
	case EZUI_MSGID_VOICETALK_STOP: // 对讲开启
	{
		var info = "对讲停止成功";
		showpanel.value = info;
		document.getElementById('starttalkbtn').style.display = 'block';
		document.getElementById('stoptalkbtn').style.display = 'none';
	}
		break;
	case EZUI_MSGID_PTZCTRL_SUCCESS: // 云台控制成功
	{
		var info = "云台控制信令发送成功";
		showpanel.value = info;
	}
		break;
	case EZUI_MSGID_PTZCTRL_FAILED: // 云台控制失败
	{
		var info = "云台控制失败";
		showpanel.value = info;
	}
		break;
	default:
	}

}

window.unload = function() {
	alert("unload");
}