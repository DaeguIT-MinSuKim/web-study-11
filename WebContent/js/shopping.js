/**
 * 
 */

function productCheck() {
    if ($('#name').val().length == 0) {
        alert("상품명을 써주세요.");
        $('#name').focus();
        return false;
    }
    if ($('#price').val().length == 0) {
        alert("가격을 써주세요");
        $('#price').focus();
        return false;
    }
    if (isNaN($('#price').val())) {
        alert("숫자를 입력해야 합니다");
        $('#price').focus();
        return false;
    }
    return true;
}