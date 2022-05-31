const signinBtn = document.querySelector(".signin-btn");
const signupBtn = document.querySelector(".signup-btn");
const inputs = document.querySelectorAll("input");

signupBtn.onclick = () => {
	location.replace("/auth/signup");
}

signinBtn.onclick = () => {

		let url = "/todo/checkUsername";
		let option = {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body:JSON.stringify({
				username: inputs[0].value
			})
		};
		fetch(url, option)
			.then(response => {
				if (response.ok) {
					return response.json();
					
				} else {
					throw new Error("없는 아이디");
				}
			})
			.then(data => {
				if(data == false) {
					alert("존재하지 않는 아이디입니다.");
				}else {
					document.querySelector("form").submit();
				}
			})
			.catch(error => console.log(error));

}

/*signinBtn.onclick = () => {


	let url = "/todo/signin";
	let option = {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			username: inputs[0].value,
			password: inputs[1].value
		})
	};

	fetch(url, option)
		.then(response => {
			if (response.ok) {
				location.href = "/index";
			}
		})
}*/