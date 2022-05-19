const boardListTable = document.querySelector(".board-list-table");
const updateBtn = document.querySelector(".update-btn");
const deleteBtn = document.querySelector(".delete-btn");
let path = window.location.pathname;
let boardCode = path.substring(path.lastIndexOf("/") + 1);

load();

function load() {
	let url = `/api/board/${boardCode}`;
	fetch(url)
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				throw new Error("정상적인 데이터를 응답받지 못했습니다.");
			}
		})
		.then(data => {
			getBoardDtl(data.data);

		})
		.catch(error => console.log(error));

	/*$.ajax({
		type: "get",
		url: `/api/board/${boardCode}`,
		dataType: "text",
		success: function(data) {
			let boardObject = JSON.parse(data);
			getBoardDtl(boardObject.data);
		},
		error: function() {
			alert("비동기 처리 오류");
		}
	});*/
}

updateBtn.onclick = () => {
	location.href = `/board/${boardCode}`;
}

deleteBtn.onclick = () => {
	let flag = confirm("정말 게시글을 삭제하시겠습니까?");
	if (flag == true) {
		let url = `/api/board/${boardCode}`;
		let option = {
			method: "DELETE"
		};
		fetch(url, option)
			.then(response => {
				if (response.ok) {
					return response.json();
				} else {
					throw new Error("비동기 처리 오류");
				}
			})
			.then(result => {
				location.replace("/board/list");
			})
			.catch(error => console.log(error));
	}

}

function getBoardDtl(data) {
	boardListTable.innerHTML = `
	<tr>
		<th>제목</th>
		<td>${data.title}</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${data.username}</td>
	</tr>
	<tr>
		<th>조회수</th>
		<td>${data.boardCount}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td><pre>${data.content}</pre></td>
	</tr>
	`;

}

