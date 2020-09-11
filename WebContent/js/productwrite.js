/**
 * 
 */
$(function() {
    $("#reg").on("click", function() {
        event.preventDefault();
        if (productCheck())
            $("form").submit();
    });
});