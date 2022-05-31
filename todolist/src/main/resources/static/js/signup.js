const signupBtn = document.querySelector(".signup-btn");
const inputs = document.querySelectorAll("input");


signupBtn.onclick = () => {
	function checkUsername() {
		let url = "/todo/checkUsername";
		let option = {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify({
				username: inputs[2].value
			})
		};

		fetch(url, option)
			.then(response => {
				if (response.ok) {
					throw new Error("있는 유저");
				}
			})
			.catch(error => console.log(error));
	}

	if (checkUsername()) {
		alert("이미 존재하는 아이디입니다.");
	} else {
		let url = "/auth/todo/signup";
		let option = {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify({
				name: inputs[0].value,
				email: inputs[1].value,
				username: inputs[2].value,
				password: inputs[3].value
			})
		};

		fetch(url, option)
			.then(response => {
				if (response.ok) {
					location.replace("/auth/signin")
				} else {
					throw new Error("회원가입 실패");
				}
			})
			.catch(error => console.log(error));

	}



}
