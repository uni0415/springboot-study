/**
 * 
 */

const usernameText = document.querySelector(".username-text");
const textInputs = document.querySelectorAll(".text-inputs");
const fileInput = document.querySelector(".file-input");
const profileImgUrl = document.querySelector(".profile-img-url");
const submitBtn = document.querySelector("button");

async function imgSubmit() {
	let formData = new FormData(document.querySelector("form"));

	const url = "/api/v1/user/account/profile/img";
	const option = {
		method: "PUT",
		headers: {},
		body: formData
	};

	const response = await fetch(url, option)
	if (response.ok) {
		alert("프로필 이미지 변경이 되었습니다.");
		return response.json();
	} else {
		throw new Error("Failed to upload img");
	}
}


fileInput.onchange = () => {
	let reader = new FileReader();

	reader.onload = (e) => {
		profileImgUrl.src = e.target.result;
		if (confirm("이미지를 변경하시겠습니까?")) {
			const result = imgSubmit();
			console.log(JSON.stringify(result));
		}

	}

	reader.readAsDataURL(fileInput.files[0]);
}

getAuthenticationReq()
	.then(result => {
		let principal = result.data.user;
		console.log(JSON.stringify(result.data.user));
		usernameText.textContent = principal.username;
		textInputs[0].value = principal.username;
		textInputs[1].value = principal.email;
		textInputs[2].value = principal.name;
		profileImgUrl.src = "/image/profile/" + principal.profile_img_url;

	})
	.catch(error => {
		console.log(error);
	});

submitBtn.onclick = () => {
	let url = "/api/v1/user/account/profile";
	let option = {
		method: "PUT",
		headers: {
			"Content-Type" : "application/json"
		},
		body: JSON.stringify({
			username: textInputs[0].value,
			email: textInputs[1].value,
			name: textInputs[2].value
		})
	};
	
	fetch(url, option)
	.then(response => {
		if(response.ok){
			location.reload();
		}else {
			throw new Error("에러");
		}
	})
	.catch(error => console.log(error));
}


