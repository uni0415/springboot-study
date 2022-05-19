const boardListTable = document.querySelector(".board-list-table");
const submitBtn = document.querySelector(".submit-btn");
const inputItems = document.querySelectorAll(".input-items");
const inputTitle = inputItems[0];
const inputWriter = inputItems[1];
const textareaItem = document.querySelector(".textarea-item");

/*
	Promise
*/

function test(data) {
	return new Promise((resolve, reject) => {
		if (data > 100) {
			resolve(data);
		} else {
			throw reject(new Error("data가 100보다 작거나 같습니다."));
		}
	});
}

test(500)
	.then(testData => testData + 100)
	//.then(testData2 => alert(testData2))
	.catch(error => { console.log(error) });


submitBtn.onclick = () => {
	submit();
}

/*function submit() {
	$.ajax({
		type: "post",
		url: "/board",
		contentType: "application/json",
		data: JSON.stringify({
			"title": inputTitle.value,
			"content": textareaItem.value,
			"usercode": inputWriter.value
		}),
		dataType: "text",
		success: data => {
			let dataObj = JSON.parse(data);
			alert(dataObj.msg);
			location.href = "/board/dtl/" + dataObj.data;
		},
		error: () => {
			alert("비동기 처리 오류");
		}
	});
}*/

function submit() {
	let url = "/api/board";

	let option = {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			title: inputTitle.value,
			content: textareaItem.value,
			usercode: inputWriter.value
		})
	};

	fetch(url, option)
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				throw new Error("정상적인 데이터를 응답받지 못했습니다.");
			}
		})
		.then(data => { location.replace("/board-info/"+data.data)})
		.catch(error => console.log(error));
}




