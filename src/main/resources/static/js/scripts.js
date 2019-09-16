
var tab = new Array(11);
var pedras = new Array(11);
var escolhas = new Array(4);

const vectorCores =["white","red","green","blue","yellow","pink","turquoise","brown","purple"];


$(function() { 
    let classe;

    $('.classe').on('click',function(){
        if($(this).parent().data('index') === position){
            classe=$(this).attr('class');
            $(this).removeClass(classe).addClass("classe "+vectorCores[(vectorCores.indexOf(getClasse(classe))+1) % 9]);
        }
    });

    $('.button').on('click',function(){
        if($(this).parent().data('index') === position){
            if(verificarEmBranco($(this).parent().data('index'))){
                fazEscolhas();
            }
        }
    });
});

function fazEscolhas() {
    a = getLinha(position);
    for(let x = 0 ; x < 4 ; x++){
        escolhas[x] = getClasse($(a).find('#'+x).attr('class'));        
        //$(a).find('#'+x).removeClass(escolhas[x]).addClass("classe white");
    }
    let dados = {
        id:TabuleiroId,
        index:position,
        e1:escolhas[0],
        e2:escolhas[1],
        e3:escolhas[2],
        e4:escolhas[3]
    };
    console.log(dados);
    $.get('/fazEscolha',dados)
            .done(function (){
                mataButton("botao"+position);
                position--;
                
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

function getLinha(lo){
    let resp = null;
    $('#linhas').children().each(function(i) {
        if($(this).data('index') === lo){
            resp = this;
            return this;
        }
    });
    
    return resp;
}

function verificarEmBranco(index){
    let cont=0;
    let a = getLinha(index);
    for(let x = 0 ; x < 4 ; x++){        
        if($(a).find('#'+x).attr('class') === "classe white")
            cont+=1;
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

function mataButton(nomeDoButton){
	z=document.getElementById(nomeDoButton);
	z.className = "Resposta None";
    z.innerHTML="";
    
}