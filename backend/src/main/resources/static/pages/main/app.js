fetch("/api/directory")
    .then(response => response.json())
    .then(employees => {

        const tableBody = document.getElementById("employeeLines");

        employees.forEach(employee => {

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
    });