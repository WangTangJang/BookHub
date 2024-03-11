//   这是一个诗词API
var xhr = new XMLHttpRequest();
xhr.open('get', 'https://v1.jinrishici.com/all.json');
xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      var data = JSON.parse(xhr.responseText);
      var gushici = document.getElementById('gushici');
      gushici.innerText = data.content;
    }
  };
xhr.send();


