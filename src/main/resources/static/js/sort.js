'use strict';

$(function(){

var array = [
	{no : 1, name : 'アンパンマンタウン ようこそ！たのしいパンこうじょうハウス', price: 2570},
	{no : 2, name : 'おはじきシール ゆめかわDX', price: 2700},
	{no : 3, name : '仮面ライダーエグゼイド　ダブル装填　DXガシャコン キースラッシャー', price: 2700},
	{no : 4, name : '機動戦士ガンダムZZ ダブルゼータガンダム', price: 2440},
	{no : 5, name : 'キラキラ プリキュアアラモード まぜまぜ変身!スイーツパクトDX', price: 2700},
	{no : 6, name : 'キラペットドーム <アクセパーティー>', price: 2440},
	{no : 7, name : 'こえだちゃん キキ & ララ 月のおうち', price:  1900},
	{no : 8, name : 'こえだちゃんと木のおうち', price: 2700},
	{no : 9, name : 'シルバニアファミリーSylvanian Families(赤い屋根の大きなお家)', price: 1490},
	{no : 10, name : '人生ゲーム（2016年ver.）', price: 2160},
	{no : 11, name : 'トミカハイパーシリーズ ドライブヘッド', price: 2160},
	{no : 12, name : 'ハピネスチャージプリキュア! キュアライン', price:  1900},
    {no : 13, name : 'ビニールプール', price: 1490},
    {no : 14, name : 'ベイブレードバースト　B-86 スターター', price: 2160},
    {no : 15, name : 'ミニ四駆スターターパック　MAパワータイプ（ブラストアロー）', price: 2160},
    {no : 16, name : 'ミニ四駆スターターパック　MAパワータイプ（ブラストアロー）', price: 2980},
    {no : 17, name : '野球盤 3Dエース', price: 2700},
    {no : 18, name : 'ラジコンバギー ボブキャットEX', price: 1490},
];

array.sort((a, b) => {
	


	$('#sort').on('change', function(){
		if($('#subject').val() == '1' && $('#sort').val() == '1'){
			array.sort((a, b) => a.no - b.no);
		} else if ($('#subject').val() == '1' && $('#sort').val() == '2'){
			array.sort((a, b) => b.no - a.no);
		} else if ($('#subject').val() == '2' && $('#sort').val() == '1'){
			array.sort((a, b) => a.price - b.price);
		} else if ($('#subject').val() == '2' && $('#sort').val() == '2'){
			array.sort((a, b) => b.price - a.price);
		}
	});
		
	});
	
});

