const boardListTable = document.querySelector(".board-list-table");
const boardListPage = document.querySelector(".board-list-page");
const next = document.querySelector(".next");
const prev = document.querySelector(".prev");
let pageData = { "page": 1 };
let index = 0;
let buttonCount = 1;
let pageIndex = 0;

load();
function load() {
	let params = location.search;
	pageData = params.split("=")[1];

	$.ajax({
		type: "get",
		url: "/board/list/count",
		dataType: "text",
		success: function(data) {
			pageList(data);
		},
		error : function() {
			console.log("비동기 처리 오류");
		}
	})


	$.ajax({
		type: "get",
		url: "/board/list",
		data: {
			"page": pageData
		},
		dataType: "text",
		success: function(data) {
			let boardList = JSON.parse(data)
			loadTable(boardList.data);
		},
		error: function() {
			console.log("비동기 처리 오류");
		}
	})
}

function loadTable(data) {
	let table = '';
	for (let i = 0; i < data.length; i++) {
		for (let j = 0; j < 2; j++) {
			let tableStr = `
				<td>${data[i].boardCode}</td>
				<td>${data[i].title}</td>
				<td>${data[i].usercode}</td>
				<td>${data[i].boardCount}</td>
			`;
			const tr = document.createElement('tr');
			tr.innerHTML = tableStr;
			table = tr;
		}

		boardListTable.appendChild(table);
	}
}


function pageList(data) {

	if (data % 5 == 0) {
		pageIndex = parseInt(data / 5);
	} else {
		pageIndex = parseInt(data / 5) + 1;
	}

	loadPage();
}

next.onclick = () => {
	buttonCount++;
	loadPage();
}

prev.onclick = () => {
	buttonCount--;
	loadPrevPage();
}


function loadPage() {
	while (boardListPage.hasChildNodes()) {
		boardListPage.removeChild(boardListPage.firstChild);
	}
	
	for (let i = (index + 1); i < (buttonCount * 5) + 1; i++) {
		if ((i + index) == pageIndex) {
			break;
		} else {
			const a = document.createElement('a');
			a.href = `/board?page=${i + index}`;
			a.innerHTML = `${i + index}`;
			boardListPage.appendChild(a);
		}
	}
	
	index += 5;
}

function loadPrevPage() {
	while (boardListPage.hasChildNodes()) {
		boardListPage.removeChild(boardListPage.firstChild);
	}
	for (let i = (index - 5) + 1; i < (buttonCount * 5) + 1; i++) {
		if ((i + index) == pageIndex) {
			break;
		} else {
			const a = document.createElement('a');
			a.href = `/board?page=${i - index}`;
			a.innerHTML = `${i - index}`;
			boardListPage.appendChild(a);
		}
		index -= 5;
	}

}








