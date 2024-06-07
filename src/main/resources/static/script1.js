//var uploadForm = document.getElementById("uploadForm");

let sndButton = document.getElementById("sndButton");
let fileInput = document.getElementById("uploadForm");
//let updButton = document.getElementById("updButton");
let pricesFiles = document.getElementById("pricesFiles");

let responseTextArea = document.getElementById("responseTextArea");
let responseTextArea1 = document.getElementById("responseTextArea1");

//(e) => {
pricesFiles.addEventListener("change", async function() {

    let fd = new FormData();
    let pricesList = pricesFiles.files;
    for(let i=0; i< pricesList.length; i++) {
        let file = pricesList[i].name;
        fd.append('file', pricesList[i]);
        responseTextArea1.value += file;
    }

    let response = await fetch("http://localhost:8080/updprices", {
      method: 'POST',
      body: fd
    });

    if (response.ok) {
              alert("Good! Пост запрос успешно прошел!");
    //        let json = await response.json();
              let text = await response.text();
    //        responseTextArea1.value = JSON.stringify(json);
              responseTextArea1.value = text;
    } else {
        alert("Ошибка HTTP: " + response.status);
    }

});

//
sndButton.addEventListener("click", async function() {

    let fd = new FormData();
    let fileList = fileInput.files;
    for(let i=0; i< fileList.length; i++) {
        let file = fileList[i].name;
        fd.append('file', fileList[i]);
        responseTextArea.value += file;
    }


    //     let fd = new FormData();
    //    fd.append('file', selectedFile); //  долго мучался с реализации загрузки файла на сервер!
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
    //        alert("Good! Пост запрос успешно прошел!");
    //        let json = await response.json();
              let text = await response.text();
    //        responseTextArea1.value = JSON.stringify(json);
              responseTextArea1.value = text;
    } else {
        alert("Ошибка HTTP: " + response.status);
    }
});
