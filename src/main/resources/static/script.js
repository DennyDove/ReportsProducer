let name = document.getElementById("name");
let number = document.getElementById("number");
let sendBtn = document.getElementById("sendBtn");
let responseTextArea = document.getElementById("responseTextArea");

/*
// Первый вариант создания асинхронной функции: отдельно создаем "async function createUser()"
async function createUser() {

    let obj = {
        name: name.value,
        age: age.value
        };

    let response = await fetch("http://localhost:8080/users", {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
       },
       body: JSON.stringify(obj)
    });

    if (response.ok) {
        let json = await response.json();
        responseTextArea.value = JSON.stringify(json);
    } else {
        alert("Ошибка HTTP: " + response.status);
    }
}
*/

// Второй вариант создания асинхронной функции (функция без имени):
sendBtn.addEventListener("click", async function() {
//  createUser(); это в случае первого варианта
    let obj = {
        name: name.value,
        age: age.value
        };

    let response = await fetch("http://localhost:8080/users", {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
       },
       body: JSON.stringify(obj)
    });

    if (response.ok) {
        let json = await response.json();
        responseTextArea.value = JSON.stringify(json);
    } else {
        alert("Ошибка HTTP: " + response.status);
    }
});