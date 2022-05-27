const signinBtn = document.querySelector(".signin-btn");
const signupBtn = document.querySelector(".signup-btn");

signupBtn.onclick = () => {
    location.replace("/signup")
}

/*signinBtn.onclick = () => {
    const inputs = document.querySelectorAll("input");


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