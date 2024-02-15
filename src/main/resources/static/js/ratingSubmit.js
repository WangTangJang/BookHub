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
        success: function(data) {
            // 处理成功响应
            console.log(data)
            $('#bookDetails').replaceWith(data);
            // 关闭模态框
            $('#ratingPopover').modal('hide');
        },
        error: function(error) {
            // 处理错误
            console.error('Error saving rating:', error);
        }
    });
});
