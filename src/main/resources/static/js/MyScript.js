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
        success: function(newNavbarHtml) {
            // 处理登录成功的情况
            $('#userCenter').replaceWith(newNavbarHtml);
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

// 退出
$('#logout').click(function(event) {
    // 拦截
    event.preventDefault();
    let url = $(this).attr('href');
    $.ajax({
        url : url,
        method: 'POST',
        success: function(newNavbarHtml) {
            // 处理退出成功的情况
            $('#logoutModal').modal('hide');
            $('#userCenter').replaceWith(newNavbarHtml);
        },
        error: function(error) {
            // 处理退出失败的情况
            console.error('Logout failed:', error);
        }
    });
});

<!--    当toBookInfo点击的时候-->
$(".toBookInfo").click(function (event) {
    //    拦截当前
    event.preventDefault();
    let url = $(this).attr('href');
    $.ajax({
        url:url,
        type: 'POST',
        success: function (bookDetailHtml) {
            // 弹出一个拟态框
            $('#bookDetailModal .modal-body').html(bookDetailHtml);
            $('#bookDetailModal').modal('show');
        }
    })

});
