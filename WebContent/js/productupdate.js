/**
 * 
 */
$(function() {
    $("#modify").on("click", function() {
        event.preventDefault();
        var res = productCheck();
        alert(res);
        $("form").submit();
    });
});