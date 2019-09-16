$(function (){
    $('tr').click(function (){
        let id = $(this).data('url');
        window.location.href="/?id="+id;
    });
    $('td').click(function (){
        let id = $(this).parent().data('url');
        window.location.href="/?id="+id;
    })
});