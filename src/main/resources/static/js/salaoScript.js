const vectorCores =["white","red","green","blue","yellow","pink","turquoise","brown","purple"];
var cores = new Array(4);
$(function (){
    $('.classe').on('click',function(){
            classe=$(this).attr('class');
            $(this).removeClass(classe).addClass("classe "+vectorCores[(vectorCores.indexOf(getClasse(classe))+1) % 9]);
    });
    $('tr').click(function (){
        let id = $(this).data('url');
        window.location.href="/?id="+id;
    });
    $('td').click(function (){
        let id = $(this).parent().data('url');
        window.location.href="/?id="+id;
    })
    $('#nJogo').click(function () {
        if(verificarEmBranco()){
            let dados = {
                c1:cores[0],
                c2:cores[1],
                c3:cores[2],
                c4:cores[3]
            };
            $.post("/criarJogo",dados).done(function (xhr, status, error) {
                alert("jogo criado, sala:" + window.location.host + "/?id=" + xhr.responseText);
            }).fail(function (xhr, status, error) {
                alert(xhr.responseText);
            })
        }
    })
});

function verificarEmBranco(){
    let cont=0;
    for(let x = 1 ; x < 5 ; x++){
        cores[x-1] = vectorCores.indexOf(getClasse($('#0'+x).attr('class')));
        if($('#0'+x).attr('class') === "classe white")
            cont += 1;

    }
    return cont === 0;
}

function getClasse(string){
    let inicio=false;
    let nomeDaClasse="";
    let vector = string.split('');
    for(let x = 0 ; x < vector.length ; x++){
        if(inicio)
            nomeDaClasse+= vector[x];
        else if(vector[x] === " ")
            inicio=true;
    }
    return nomeDaClasse;
}