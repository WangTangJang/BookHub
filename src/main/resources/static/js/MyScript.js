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

// 登录
$('#loginForm').submit(function (event) {
    event.preventDefault();
    let form = $(this);
    let url = form.attr('action');
    let username = form.find('input[name="username"]').val();
    let password = form.find('input[name="password"]').val();
    $.ajax({
        type: 'POST',
        url: url,
        data: JSON.stringify({username:username, password:password}),
        contentType: 'application/json',
        success: function(response) {
            // 处理登录成功的情况
            location.reload();
            // 关闭登录框
            $('#loginModal').modal('hide');
        },
        error: function(error) {
            // 处理登录失败的情况
            console.error('Login failed:', error);
            // 可以在页面上显示错误信息，根据后端返回的信息进行处理
        }
    })
})
function updateNavbar(){
    // 检查当前session中是否有session.setAttribute("user",user);


}


// 当模态框显示时
$('#ratingPopover').on('show.bs.modal', function () {
    // 绑定提交按钮的点击事件
    $('#ratingSubmit').on('click', function () {

        let button = $(this); // 触发提交的按钮
        // 从按钮中获取书籍ID
        let bookId = button.data('book-id');

        let saveUrl = button.data('save-url');
        // 获取选择的评分
        let selectedRating = parseInt($('#ratingSelect').val(), 10);

        // 发送评分到后端保存，使用Ajax
        $.ajax({
            type: 'POST',
            url: saveUrl, // 替换为你的后端保存评分的接口
            contentType: 'application/json',
            data: JSON.stringify({ bookId: bookId, rating: selectedRating }),
            success: function(AverageRating) {
                // 处理成功响应
                $('#bookRating').text(AverageRating);
                // 关闭模态框
                $('#ratingPopover').modal('hide');
            },
            error: function(error) {
                // 处理错误
                console.error('Error saving rating:', error);
            }
        });
    });
});
