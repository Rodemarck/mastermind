
var position=0;
var tab = new Array(11);
var pedras = new Array(11);
var escolhas = new Array(4);
for (var i = 0, max = 4; i < max; i++) {
    escolhas[i] = "white";
}
const vectorCores =["white","red","green","blue","yellow","pink","turquoise","brown","purple"];
function inicialize(){
    let letras = ['a','b','c','d','e','f','g','h','i','j','k','l','m'];
    for (let index = 0; index < 11; index++) {
        tab[index] = new Array(4);
        pedras[index] = new Array(4);
    }
     
    for (let y = 0; y < 11; y++) {
        for(let x = 0; x < 4 ; x++){
            tab[x][y]=letras[y]+x;
        }
    }
}


$(function() { 
    let classe ;

    $('.classe').on('click',function(){
        if($(this).parent().data('index') == position){
            classe=$(this).attr('class');
            $(this).removeClass(classe).addClass("classe "+vectorCores[(vectorCores.indexOf(getClasse(classe))+1) % 9]);
        }
    });

    $('.button').on('click',function(){
        if($(this).parent().data('index') == position){
            if(verificarEmBranco($(this).parent().data('index'))){
                verificaEscolhas(position);
                position--;
            }
        }
    });
    $('.btn').click(function () {
        fazEscolhas();
    })
});

function fazEscolhas() {
    a = $('#escolhaPadrao');
    for(let x = 0 ; x < 4 ; x++){
        escolhas[x] = getClasse($(a).find('#'+x).attr('class'));        
        //$(a).find('#'+x).removeClass(escolhas[x]).addClass("classe white");
    }
    let dados = {
        e1:escolhas[0],
        e2:escolhas[1],
        e3:escolhas[2],
        e4:escolhas[3],
    };
    console.log(escolhas);
    $.get('/fazEscolha',dados)
            .done(function (){
                mataButton("botao"+position);
                position=10;
    })
            .fail(function (){
                console.log("error no sistema");
    });
    console.log("deveria ter ido...");
}

function salvar(el){
    let a = $(el).parent();
    
    
    for(let x = 0 ; x < 4 ; x++){
        escolhas[x] = getClasse($(a).find('#'+x).attr('class'));        
        $(a).find('#'+x).removeClass(escolhas[x]).addClass("classe white");
    }
}

function getLinha( lo){
    let resp = null;
    $('#linhas').children().each(function(i) {
        if($(this).data('index') == lo){
            resp = this;
            return this;
        }
    });
    
    return resp;
}

function verificarEmBranco(index){
    let cont=0;
    let a = getLinha(index-1);
    for(let x = 0 ; x < 4 ; x++){        
        if($(a).find('#'+x).attr('class') == "classe white")
            cont+=1;
    }
    return cont == 0;
}

function getClasse(string){
    let inicio=false;
    let nomeDaClasse="";    
    let vector = string.split('');
    for(let x = 0 ; x < vector.length ; x++){
        if(inicio)
            nomeDaClasse+= vector[x];
        else if(vector[x] == " ")
            inicio=true;
    }
    return nomeDaClasse;
}

function verificaEscolhas(index){
    let pretas=0,brancas=0;
    let aux1,aux2;
    let a = getLinha(index-1);
    for(let x=0;x<4;x++){
        if(escolhas[x] == getClasse ($(a).find('#'+x).attr('class')))
            pretas++;
    }
    for(let x=0;x<11;x++){
        aux1=0;
        aux2=0;
        for(let y=0;y<4;y++){
            if(vectorCores[x] == escolhas[y])
                aux1++;
            if(vectorCores[x] == getClasse($(a).find('#'+x).attr('class'))  )
                aux2++;
        }
        if(aux2>aux1)
            aux2=aux1;
        brancas+=aux2;
    }
    brancas-=pretas;
}

function mataButton(nomeDoButton){
	z=document.getElementById(nomeDoButton);
	z.className = "Resposta None";
    z.innerHTML="";
    
}