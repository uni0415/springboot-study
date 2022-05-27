const signupBtn = document.querySelector(".signup-btn");


signupBtn.onclick = () => {
    const inputs = document.querySelectorAll("input");


    let url = "/todo/signup";
    let option = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: inputs[0].value,
            username: inputs[1].value,
            password: inputs[2].value
        })
    };

    fetch(url, option)
        .then(response => {
            if (response.ok) {
                location.replace("/signin")
            } else {
                throw new Error("회원가입 실패");
            }
        })
        .catch(error => console.log(error));
}