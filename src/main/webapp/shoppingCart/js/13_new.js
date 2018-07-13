var storage = sessionStorage;
function doFirst(){
	var itemString = storage.getItem('addItemList');
	var items = itemString.substr(0,itemString.length-2).split(', ');

	newSection = document.createElement('section');
	newTable = document.createElement('table');

	total = 0;
	// 每購買一個品項，新增一個tr
	for(var key in items){
		var itemInfo = storage.getItem(items[key]);
		//              id         value
		creatCartList(items[key],itemInfo);	

		total += parseInt(itemInfo.split('|')[2]);
	}
	document.getElementById('total').innerText = total;
	//最後將table放進section，再將section放進cartList	
	newSection.appendChild(newTable);
	document.getElementById('cartList').appendChild(newSection);
}
function creatCartList(itemKey,itemValue){
		// alert(itemKey+" : "+itemValue);
		var itemTitle = itemValue.split('|')[0];
		var itemImage = 'imgs/'+itemValue.split('|')[1];
		var itemPrice = parseInt(itemValue.split('|')[2]);
			
		//建立每個品項的清單區域--tr
		var trItemList = document.createElement('tr');
		trItemList.className = 'item';		//trItemList.setAttribute('class','item');

		newTable.appendChild(trItemList);


		//建立商品圖片--第一個td
		var tdImage = document.createElement('td');
		tdImage.style.width = '200px';

		var image = document.createElement('img');
		image.src = itemImage;
		image.width = 100;

		tdImage.appendChild(image);
		trItemList.appendChild(tdImage);


		//建立商品名稱和刪除按鈕--第二個td
		var tdTitle = document.createElement('td');
		tdTitle.style.width = '200px';
		tdTitle.id = itemKey;

		var pTitle = document.createElement('p');
		pTitle.innerText = itemTitle;

		var button = document.createElement('button');
		button.innerText = 'Delete';
		button.addEventListener('click', deleteItem);

		tdTitle.appendChild(pTitle);
		tdTitle.appendChild(button);

		trItemList.appendChild(tdTitle);


		//建立商品價格--第三個td

		var tdPrice = document.createElement('td');
		tdPrice.style.width = '170px';
		tdPrice.innerText = itemPrice;
		tdPrice.value = itemPrice;

		trItemList.appendChild(tdPrice);

		//建立商品總價錢--第四個td

		var subTotal = document.createElement('td');
		subTotal.style.width = '170px';
		subTotal.innerText = itemPrice;
		subTotal.value = 1;

		trItemList.appendChild(subTotal);
		//建立商品數量--第五個td

		var tdItemCount = document.createElement('td');
		tdItemCount.style.width = '60px';

		var itemCount = document.createElement('input');
		itemCount.type = 'number';
		itemCount.value = 1;
		itemCount.min = 0;
		
		itemCount.addEventListener('input', changeItemCount);

		tdItemCount.appendChild(itemCount);
		trItemList.appendChild(tdItemCount);
}
function deleteItem(){
	var itemId = this.parentNode.getAttribute('id');
	// alert(itemId);

	//刪除該筆資料之前，先將金額扣除。
	var itemValue = storage.getItem(itemId);
	total -= parseInt(itemValue.split('|')[2]);
	document.getElementById('total').innerText = total;

	//清除storage的資料。
	storage.removeItem(itemId);
	storage['addItemList'] = storage['addItemList'].replace(itemId+', ','');

	//再將該筆tr移除
	//找到老爸的老爸的老爸在刪掉老爸的老爸。
	this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);


}
function changeItemCount(){
	
	this.parentNode.previousSibling.innerText = 
			this.value * this.parentNode.previousSibling.previousSibling.value;

	total -= this.parentNode.previousSibling.value * this.parentNode.previousSibling.previousSibling.value;
	this.parentNode.previousSibling.value = this.value;
	total += this.parentNode.previousSibling.value * this.parentNode.previousSibling.previousSibling.value;
	document.getElementById('total').innerText = total;
				
}
window.addEventListener('load', doFirst, false);












