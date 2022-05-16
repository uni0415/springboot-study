const boradListTable = document.querySelector(".board-list-table");
const boardListPage = document.querySelector(".board-list-page");
const boardItems = document.querySelector(".board-items");
const boardItemsNumber = document.querySelectorAll(".board-items td");
let pageData = { "page": 1 };

load();


function load() {
	let params = location.search;
	params = params.replace("?", "");
	pageData["" + params.split("=")[0]] = params.split("=")[1];
	
	$.ajax({
		type: "get",
		url: "/board/list/count",
		dataType: "text",
		success: function(data) {
			let boardListCount = JSON.parse(data);
			pageNumber(boardListCount);
		},
		error: function() {
			console.log("비동기 처리 오류");
		}
	});
	
	$.ajax({
		type: "get",
		url: "/board/list",
		data: pageData,
		dataType: "text",
		success: function(data) {
			let boardList = JSON.parse(data);
			console.log(boardList.data);
			dataArray(boardList);
		},
		error: function() {
			alert("비동기 처리 오류");
		}
	});
}

function dataArray(boardList) {
	for (let i = 0; i < boardList.data.length; i++) {
		let tableRow = boradListTable.insertRow();
		let tableData1 = tableRow.insertCell(0);
		let tableData2 = tableRow.insertCell(1);
		let tableData3 = tableRow.insertCell(2);
		let tableData4 = tableRow.insertCell(3);
		
		tableData1.innerText = boardList.data[i].boardCode;
		tableData2.innerText = boardList.data[i].title;
		tableData3.innerText = boardList.data[i].username;
		tableData4.innerText = boardList.data[i].boardCount;
	}
}


function pageNumber(boardListCount) {
	let page = 1;
	if(boardListCount % 5 == 0) {
		page = Math.floor(boardListCount / 5);
	} else {
		page = Math.floor(boardListCount / 5) + 1;
	}
		/*if(boardListCount%5 < 5){
			page = (parseInt(boardListCount/5)) + 1
			console.log(page);
		}else if(boardListCount%5 == 0){
			page = boardListCount/5;
			console.log(page);
		}
	*/
	console.log(page);
	for(let i = 1; i < page+1; i++) {
		const pageNum = document.createElement("a");
		pageNum.href=`/board?page=${i}`;
		pageNum.innerHTML = `${i}`;
		boardListPage.appendChild(pageNum);
	}
}
