const loginForm = document.getElementById("loginForm");
const usernameInput = document.getElementById("username");
const passwordInput = document.getElementById("password");
const submitButton = document.getElementById("myButton");

const statusText = document.querySelector(".statusText");
const statusTitle = document.getElementById("myH1");
const statusMessage = document.getElementById("myP1");

const defaultButtonText = submitButton.textContent;

let statusTimer;

loginForm.addEventListener("submit", loginUser);


async function loginUser(event) {
    event.preventDefault();

    const username = usernameInput.value.trim();
    const password = passwordInput.value;

    if (username === "") {
        showStatus("USERNAME REQUIRED", "Enter your username", "error");
        usernameInput.focus();
        return;
    }

    if (password === "") {
        showStatus("PASSWORD REQUIRED", "Enter your password", "error");
        passwordInput.focus();
        return;
    }

    setLoading(true);
    showStatus("PLEASE WAIT", "Checking your credentials", "normal");

    try {
        const response = await fetch("/api/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        });

        if (!response.ok) {
            throw new Error(`HTTP error: ${response.status}`);
        }

        const result = await response.json();

        if (!result.success) {
            showStatus(
                "ACCESS DENIED",
                "Incorrect username or password",
                "error"
            );

            passwordInput.value = "";
            passwordInput.focus();
            setLoading(false);
            return;
        }

        showStatus(
            "ACCESS GRANTED",
            "Opening employee directory",
            "success"
        );

        window.setTimeout(function () {
            window.location.href = "/pages/main/index.html";
        }, 700);

    } catch (error) {
        console.error("Login error:", error);

        showStatus(
            "CONNECTION ERROR",
            "The server is unavailable. Try again later",
            "error"
        );

        setLoading(false);
    }
}


function setLoading(loading) {
    submitButton.disabled = loading;
    submitButton.textContent = loading
        ? "CHECKING..."
        : defaultButtonText;
}


function showStatus(title, message, type) {
    window.clearTimeout(statusTimer);
    statusText.classList.add("is-changing");

    statusTimer = window.setTimeout(function () {
        statusTitle.textContent = title;
        statusMessage.textContent = message;

        statusText.classList.remove("is-error", "is-success");

        if (type === "error") {
            statusText.classList.add("is-error");
        }

        if (type === "success") {
            statusText.classList.add("is-success");
        }

        statusText.classList.remove("is-changing");
    }, 200);
}
