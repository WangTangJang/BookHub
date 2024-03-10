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
// 显示回复输入框
function showReplyInput(id) {
    console.log("showReplyInput");
    var replyInput = document.getElementById('replyInput' + id);
    replyInput.style.display = 'flex';
}

// 显示评论的功能
$(document).on('click', '.showComments', function (event) {
    event.preventDefault();
    $('#commentsModal').modal('show');
    let url = $(this).attr('href');
    $.ajax({
        url: url,
        type: 'GET',
        success: function (data) {
            $('#commentsSection').replaceWith(data);
        }
    })
})
