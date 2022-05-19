const boardListTable = document.querySelector(".board-list-table");
const submitBtn = document.querySelector(".submit-btn");
const inputItems = document.querySelectorAll(".input-items");
const inputTitle = inputItems[0];
const inputWriter = inputItems[1];
const textareaItem = document.querySelector(".textarea-item");


let path = window.location.pathname;
let boardCode = path.substring(path.lastIndexOf("/") + 1);
let url = `/api/board/${boardCode}`;
load();
submitBtn.onclick = () => {
	submit();
}

function load() {
	
	fetch(url)
	.then(response => {
		if(response.ok) {
			return response.json();
		} else {
			throw new Error("정상적인 데이터를 응답받지 못했습니다.");
		}
	})
	.then(data => {
		getBoardDtl(data.data);
		
	})
	.catch(error => console.log(error));
	
}

function getBoardDtl(data) {
	inputItems[0].value=`${data.title}`;
	inputItems[1].value=`${data.username}`;
	textareaItem.value=`${data.content}`;
}


function submit() {

	let option = {
		method: "PUT",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			title: inputTitle.value,
			content: textareaItem.value
		})
	};

	fetch(url, option)
		.then(response => {
			console.log(response);
			if (response.ok) {
				return response.json();
			} else {
				throw new Error("정상적인 데이터를 응답받지 못했습니다.");
			}
		})
		.then(data => { location.replace("/board-info/"+data.data) })
		.catch(error => console.log(error));
}




