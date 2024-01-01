/* 장바구니 등록 메서드 */
function addToCart(action) {
    document.addForm.action = action;
    document.addForm.submit();

    alert("도서가 장바구니에 추가되었습니다!");
}
