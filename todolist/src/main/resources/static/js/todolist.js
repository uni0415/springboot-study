const add = document.querySelector(".fa-square-plus");
const addTodo = document.querySelector("input");
const listTable = document.querySelector(".list-table");
const logoutBtn = document.querySelector(".logout-btn");
const title = document.querySelector(".title");

logoutBtn.onclick = () => {
	location.replace("/logout");
}

load();
add.onclick = () => {
	if (addTodo.value.length < 1) {
		alert("할 일을 입력해주세요");
	} else {
		let url = "/api/v1/todo";
		let option = {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify({

				content: addTodo.value
			})
		};

		fetch(url, option)
			.then(response => {
				if (response.ok) {
					return response;
				} else {
					throw new Error("에러");
				}
			})
			.then(() => {
				location.reload();
			})
			.catch(error => console.log(error));
	}
}

addTodo.onkeydown = (e) => {
	if (e.keyCode == 13) {
		add.onclick();
	}
}


function load() {
	getName();
	getListFetch();
}

function getName() {
	let url = "todo/name";
	fetch(url)
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				throw new Error("name 가져오기 실패");
			}
		})
		.then(data => {
			title.innerText = `${data.name}의 To Do List`;
		})
		.catch(error => console.log(error));
}

function getListFetch() {
	let url = "api/v1/user/todo/list";

	fetch(url)
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				throw new Error("리스트 불러오기 실패");
			}
		})
		.then(data => {
			getList(data.data);
		})
		.catch(error => console.log(error));
}

function getList(data) {
	for (let i = 0; i < data.length; i++) {
		listTable.innerHTML += `
            <li class="to-do-list">
                <input type="checkbox" class="checkbox">
                <span class="text">${data[i].content}</span>
                <div class="action-box">
                    <i class="fa-solid fa-pencil"></i>
                    <i class="fa-solid fa-trash-can"></i>
                </div>
            </li>
            <li class="update-list-input-box">
                <input type="text" class="list-input">
                <div class="action-box2">
                    <button class="submit-btn">확인</button>
			        <button class="cancel-btn">취소</button>
                </div>
            </li>
            `
	}
	buttonActions(data);
}

function buttonActions(data) {
	const toDoList = document.querySelectorAll(".to-do-list");
	const actionBox = document.querySelectorAll(".action-box");
	const updateListInput = document.querySelectorAll(".update-list-input-box");
	const listInput = document.querySelectorAll(".list-input");
	const updateBtn = document.querySelectorAll(".fa-pencil");
	const deleteBtn = document.querySelectorAll(".fa-trash-can");
	const submitBtn = document.querySelectorAll(".submit-btn");
	const cancelBtn = document.querySelectorAll(".cancel-btn");

	mouseevent();
	showUpdate();
	hideUpdate();
	listUpdate();
	listDelete();

	function mouseevent() {
		for (let i = 0; i < toDoList.length; i++) {
			toDoList[i].onmouseover = () => {
				actionBox[i].classList.add("action");
			}
			toDoList[i].onmouseleave = () => {
				actionBox[i].classList.remove("action");
			}
		}
	}

	function showUpdate() {
		for (let i = 0; i < toDoList.length; i++) {
			updateBtn[i].onclick = () => {
				toDoList[i].classList.add("noneaction");
				updateListInput[i].classList.add("action");
				listInput[i].focus();
				const text = toDoList[i].querySelector(".text").innerText;
				listInput[i].value = text;
			}
		}
	}

	function hideUpdate() {
		for (let i = 0; i < toDoList.length; i++) {
			cancelBtn[i].onclick = () => {
				toDoList[i].classList.remove("noneaction");
				updateListInput[i].classList.remove("action");
			}
		}
	}

	function listUpdate() {
		for (let i = 0; i < toDoList.length; i++) {
			submitBtn[i].onclick = () => {
				let url = `/api/v1/todo/${data[i].id}`;
				let option = {
					method: "PUT",
					headers: {
						"Content-Type": "application/json"
					},
					body: JSON.stringify({
						id: data[i].id,
						content: listInput[i].value
					})
				};

				fetch(url, option)
					.then(response => {
						if (response.ok) {
							location.reload();
						} else {
							throw new Error("에러");
						}
					})
					.catch(error => console.log(error));
			}

			listInput[i].onkeydown = (e) => {
				if (e.keyCode == 13) {
					submitBtn[i].onclick();
				}
			}
		}
	}

	function listDelete() {
		for (let i = 0; i < toDoList.length; i++) {
			deleteBtn[i].onclick = () => {
				let url = `/api/v1/todo/${data[i].id}`;
				let option = {
					method: "DELETE"
				}
				fetch(url, option)
					.then(response => {
						if (response.ok) {
							location.reload();
						} else {
							throw new Error("삭제 실패");
						}
					})
					.catch(error => console.log(error));
			}

		}
	}
}






