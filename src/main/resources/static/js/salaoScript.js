const vectorCores =["white","red","green","blue","yellow","pink","turquoise","brown","purple"];

$(function (){
    $('.classe').on('click',function(){
        if($(this).parent().data('index') === position){
            classe=$(this).attr('class');
            $(this).removeClass(classe).addClass("classe "+vectorCores[(vectorCores.indexOf(getClasse(classe))+1) % 9]);
        }
    });
    $('tr').click(function (){
        let id = $(this).data('url');
        window.location.href="/?id="+id;
    });
    $('td').click(function (){
        let id = $(this).parent().data('url');
        window.location.href="/?id="+id;
    })
    $('#nJogo')
});