const loginForm = document.getElementById("loginForm");
const usernameInput = document.getElementById("username");
const passwordInput = document.getElementById("password");

loginForm.addEventListener("submit", function (event) {
    event.preventDefault();

    const userData = {
        username: usernameInput.value,
        password: passwordInput.value
    };

    loginUser(userData);
});


async function loginUser(userData) {
    try {
        const response = await fetch("/api/auth/login", {
            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(userData)
        });

        if (!response.ok) {
            throw new Error(`HTTP error: ${response.status}`);
        }

        const result = await response.json();

        console.log("Backend response:", result);

    } catch (error) {
        console.error("Login error:", error);
    }
}