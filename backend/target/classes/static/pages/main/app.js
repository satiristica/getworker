const searchInput = document.getElementById("employeeSearch");
const tableBody = document.getElementById("employeeLines");
const employeeCount = document.getElementById("employeeCount");

let searchTimer;

loadEmployees();

searchInput.addEventListener("input", function () {
    clearTimeout(searchTimer);
    searchTimer = setTimeout(loadEmployees, 300);
});

function loadEmployees() {
    const search = searchInput.value.trim();
    const url = `/api/directory?search=${encodeURIComponent(search)}`;

    fetch(url)
        .then(response => response.json())
        .then(employees => {
            showEmployees(employees);
        });
}

function showEmployees(employees) {
    tableBody.innerHTML = "";
    employeeCount.textContent = `${employees.length} EMPLOYEES`;

    employees.forEach(function (employee) {
        const row = document.createElement("tr");

        const nameCell = document.createElement("td");
        nameCell.textContent = employee.name;

        const surnameCell = document.createElement("td");
        surnameCell.textContent = employee.surname;

        const phoneCell = document.createElement("td");
        phoneCell.textContent = employee.phoneNumber;

        const emailCell = document.createElement("td");
        emailCell.textContent = employee.email;

        row.appendChild(nameCell);
        row.appendChild(surnameCell);
        row.appendChild(phoneCell);
        row.appendChild(emailCell);

        tableBody.appendChild(row);
    });
}
