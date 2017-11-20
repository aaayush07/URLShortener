var copy1 = document.getElementById('copy1');
var avail = document.getElementById('avail');
var optional = document.getElementById('optional');
var open1 = document.getElementById('open1');
var finalLink = document.getElementById('finalLink');
var copyOpen = document.getElementById('copy-open');
var form2 = document.getElementById('form2');

avail.style.display = 'none';
copy1.style.display = 'none';
open1.style.display = 'none';
finalLink.style.display = 'none';
//form2.style.display = 'none';



function bringBack(){    
   var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = process;
    xmlHttp.open('POST','Short.jsp',true);
    xmlHttp.send();
}

function process(){
    if(xmlHttp.readyState == 4){
        if(xmlHttp.status == 200){
            avail.style.display = 'block';
            copy1.style.display = 'block';
            open1.style.display = 'block';
            avail.innerHtml = xmlHttp.responseText;
        }
    }
}

function final(){    
   var xmlHttp = new XMLHttpRequest();
   var longURL = document.getElementById("longURL").value;
   xmlHttp.onreadystatechange = processFinal;
   var url="Short?val="+longURL;  
    xmlHttp.open('GET',url,true);
    xmlHttp.send();
}

function processFinal(){
    if(xmlHttp.readyState == 4){
        if(xmlHttp.status == 200){
            finalLink.style.display = 'block';
            var shortUrl = document.getElementById('shortUrl');
            shortUrl = xmlHttp.responseText;
        }
    }
}