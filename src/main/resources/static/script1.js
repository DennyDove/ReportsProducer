//var uploadForm = document.getElementById("uploadForm");

let sndButton = document.getElementById("sndButton");
let fileInput = document.getElementById("uploadForm");
let responseTextArea = document.getElementById("responseTextArea");

/*
fileInput.addEventListener('change', (e) => {
  const fileInfo = e.target.files[0];
//  console.log("Файл: ${fileInfo.name}");
  responseTextArea.value = fileInfo.name;
});
*/

// Второй вариант создания асинхронной функции (функция без имени):
sndButton.addEventListener("click", async function() {
//  createUser(); это в случае первого варианта
    let selectedFile = fileInput.files[0]; // files[0] - возвращает объект "фаил" из элемента типа input
    responseTextArea.value = selectedFile.name;

    let fd = new FormData();
    fd.append('file', selectedFile); //  долго мучался с реализации загрузки файла на сервер!
    // ... и всеего-навсего нужно было правильно сформировать body для Post-запроса.
    // ключевой "затык" оказался в этом месте: fd.append('file', selectedFile);
    // нужно было в качестве первого параметра указать "file", чтобы совпадало с @RequestParam("file")
    // В Postman --> тоже нужено было прописать "file": body --> form-data --> key --> file

    let response = await fetch("http://localhost:8080/uploadoc", {
        method: 'POST',
/*
        headers: {
            "Content-Type": "multipart/form-data"
       },
*/
       body: fd
    });

    if (response.ok) {
        alert("Поздравляем! Пост запрос успешно прошел!");

        let json = await response.json();
        responseTextArea.value = JSON.stringify(json);

    } else {
        alert("Ошибка HTTP: " + response.status);
    }
});