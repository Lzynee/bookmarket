/* 장바구니 등록 메서드 */
function addToCart(action) {
    document.addForm.action = action;
    document.addForm.submit();

    alert("도서가 장바구니에 추가되었습니다!");
}

/* 장바구니에 등록된 도서 항목을 삭제하는 메서드 */
function removeFromCart(action) {
    document.removeForm.action = action;
    document.removeForm.submit();

    window.location.reload();
}
