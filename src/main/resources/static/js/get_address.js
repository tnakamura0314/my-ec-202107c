/**
 * 郵便番号から住所を自動取得
 */
'use strict';

$(function(){
	// 「住所検索」ボタンクリックで非同期処理開始
	$(document).on('click', '#get_address_btn', function(){
		$.ajax({
			url: 'http://zipcoda.net/api',	// 送信先WebAPIのURL
			dataType: 'jsonp',	// レスポンスデータ形式
			data: {	// リクエストパラメータ情報
				zipcode: $('#zipcode').val()
			},
			async: true
		}).done(function(data){
			// 検索成功時にはページに結果を反映
			// コンソールに取得したJSONデータを表示
			console.dir(JSON.stringify(data));
			$('#address').val(data.items[0].address);
		}).fail(function(XMLHttpRequest, textStatus, errorThrown){
			// 検索失敗時には、その旨をダイアログ表示しエラー情報をコンソールに記載
			alert('正しい結果を得られませんでした。');
			console.log('XMLHttpRequest：' + XMLHttpRequest.status);
			console.log('textStatus    ：' + textStatus);
			console.log('errorThrown   ：' + errorThrown.message);
		});
	});
});

