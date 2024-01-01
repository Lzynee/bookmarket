/* 장바구니 등록 메서드 */
function addToCart(action) {
    document.addForm.action = action;
    document.addForm.submit();

    alert("도서가 장바구니에 추가되었습니다!");
}

/* 장바구니에 등록된 도서 항목을 삭제하는 메서드 */
function removeFromCart(action) {

    $.ajax({
        url: action,
        type: 'PUT',
        success: function (result) {
            window.location.reload();
        }
    })
}

/* 장바구니에 저장된 모든 도서 항목을 삭제한다. */
function clearCart() {

    var cartId = $('#cartId').val(); // 'cartIdElement'는 cartId 값을 가진 HTML 요소의 id입니다.
    var url = '/BookMarket/cart/' + cartId;

    $.ajax({
        url: url,
        type: 'DELETE',
        success: function(result) {
            window.location.reload();
        },
        error: function(error) {
            console.log('Error in clearCart', error);
        }
    });
    /*document.clearForm.submit();
    window.location.reload();*/
}
