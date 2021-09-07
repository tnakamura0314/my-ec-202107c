'use strict';
$(function () {
	
$("#delte-user-button").on('click',(e)=>{
	let result = confirm("本当に退会しますか？");
	//キャンセルが押された場合、完了画面遷移しない
	if(result){
		return ture;
	}else{
		return false;
	}
});

});