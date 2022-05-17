const boardListTable = document.querySelector(".board-list-table");
const boardListPage = document.querySelector(".board-list-page");
let pageData = { "page": 1 };

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

	/*if (page < 6) {
		for (let i = 1; i < page + 1; i++) {
			const a = document.createElement('a');
			a.href = `/board?page=${i}`;
			a.innerHTML = `${i}`;
			boardListPage.appendChild(a);
		}
	} else {
		for (let i = 1; i < 6; i++) {
			const a = document.createElement('a');
			a.href = `/board?page=${i}`;
			a.innerHTML = `${i}`;
			boardListPage.appendChild(a);
		}

		const a = document.createElement('a');
		a.href = `/board?page=${page}`;
		a.className = 'next';
		a.innerText = ">";
		boardListPage.appendChild(a);
		const next = document.querySelector(".next");
*/
	for (let i = 0; i < parseInt(page / 5) + 1; i++) {
		let index = 1;
		for (let j = page; j < data; page++) {
				const a = document.createElement('a');
				a.href = `/board?page=${page}`;
				a.innerHTML = `${page}`;
				boardListPage.appendChild(a);
				index++;
				if (index > 4) break;
			}
		next.onclick = () => {
			boardListPage.removeChild(a);
			for (let j = page; j < data; page++) {
				const a = document.createElement('a');
				a.href = `/board?page=${page}`;
				a.innerHTML = `${page}`;
				boardListPage.appendChild(a);
				index++;
				if (index > 4) break;
			}
		}
	}
}

