//初始化
function init(){
	initEvent();
	showHome(1);
}

//初始化事件
function initEvent(){
	//绑定点击条件事件
	$(".li-item").click(function(){
		var key = $(this).attr('key');
		var text = $(this).find('a').html();
		var val = $(this).find('a').attr('value');
		//alert(text+"--------"+val+"---------"+key);
		var isHave = $("#query-div").find("."+key);
		if(isHave.length!=0){
			isHave.attr("val",val);
			isHave.find(".item-text").html(text);
		}else{
			$("#query-div").append('<a style="margin-left:10px" key="'+key+'" val="'+val+'" href="javascript:;" class="am-btn am-btn-default '+key+'"><span class="item-text">'+text+'</span>  √</a>'
			);
		}

		showHome(1)
	});
	//删除点击条件事件
	$(".li-select a").click(function(){
		var key = $(this).attr('key')
		var is = $("#query-div").find("."+key);
		if(is.length!=0){
			$(".am-btn.am-btn-default."+key).remove();
		}
		showHome(1)
	});
}


//获取筛选条件
function getQueryParam(){
var jsonData={};
	$("#query-div a").each(function(index,item){
		var key=$(item).attr('key')
		var val=$(item).attr('val')
		jsonData[key]=val;
	})
	return jsonData;
}

//显示房屋
function showHome(pageNum) {
	var jsonData = getQueryParam();
	jsonData.pageNum=pageNum;
	console.log(jsonData)
	$.ajax({
		url:"/home/showHomeList",
		data:jsonData,
		type:"get",
		dataType:"json",
		success:function(data){
			/**
			 * ajax遍历房屋数据
			 */
			$('#home-list-ul').html('');// 清空
			var $html='';
			$.each(data.list,function(index,home){

				 var $li='<li class="hp-home-img-item">' +
       			'<div class="hp-img">' +
       				'<a href="#">' +
       					'<img style="width:442px;height:333px" class="am-thumbnail" src="'+home.imgs+'" />' +
   					'</a>' +
   					'<div>' +
   						'<div class="hp-fl">' +
       						'<div>' +
   							'<span class="hp-home-title">'+home.title+'</span>' +
   							'</div>' +
   								'<div>' +
   								'<span class="hp-home-type-address">地址：'+home.address+'</span>'+
								'<span class="hp-home-type-mark">'+home.mark+'/'+home.areas+'平/'+home.houseType.typedesc+'<span class="hp-home-price">'+home.price+'元/月</span></span>' +
								'</div>' +
						'</div>' +
							'<div class="hp-fr hp-am-circle-img">' +
							'<img src="'+home.users.headImg+'" class="am-circle hp-am-circle" />' +
							'</div>' +
					'</div>' +
				'</div>' +
				  '</li>';
				 		$html+=$li;
			})

		   $('#home-list-ul').append($html);


			/**
			 * 分页组件，初始化分
			 */

			var pagination = new Pagination({
				wrap:$('.am-pagination'),//存放分页内容
				count:parseInt(data.pages),//总页数
				current:parseInt(data.pageNum),//当前页
				prevText: '上一页', // prev 按钮的文本内容
				nextText: '下一页', // next 按钮的文本内容
				callback: function(current) { // 每一个页数按钮的回调事件
					  showHome(current)
				}
			});
			
		}
	});
}

