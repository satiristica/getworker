const loginForm = document.getElementById("loginForm");
const usernameInput = document.getElementById("username");
const passwordInput = document.getElementById("password");

loginForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    const username = usernameInput.value;
    const password = passwordInput.value;

    if (username === "") {
        console.error("Username is empty");
        return;
    }

    if (password === "") {
        console.error("Password is empty");
        return;
    }

    const loginData = {
        username: username,
        password: password
    };

    const response = await fetch("api/auth/login", 
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(loginData)
        }
    ).catch(function (error) {
        console.log("cant connect to backend: ", error);
        return null;
    });

