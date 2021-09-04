'use strict';
$(function () {
	
$("#order-confirm-button").on('click',(e)=>{
	let result = confirm("注文を確定してよろしいですか？");
	//キャンセルが押された場合、完了画面遷移しない
	if(result){
		return ture;
	}else{
		return false;
	}
});

});