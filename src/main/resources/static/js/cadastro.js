
$(function () {
    $('#formCadastro').submit(function () {

        var v = $('#formCadastro').serialize();
        $.post('/cadastrar',v).fail(function (xhr, status, error) {
            alert(xhr.responseText);
        })
        /*var ajax = new XMLHttpRequest();
        ajax.open("POST", "/cadastrar", true);


        var dados = {
            login : "rode",
            password: "123"
        }
        ajax.send(dados);
        ajax.onreadystatechange = function() {

            console.log(ajax.status);
        }*/
        return false;
    })
})