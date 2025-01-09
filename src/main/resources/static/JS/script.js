let your_current_mode=getTheam();
function changeTheam(){
   
        document.getElementById("theamChange").removeAttribute("class");
        document.getElementById("theamChange").setAttribute("class",your_current_mode);
}
changeTheam();
document.querySelector("button span").addEventListener('click',function(){
    let rmode=getTheam();
    if(rmode=="Light"){
        setTheam("dark");
    }else{
        setTheam("Light");
    }
    let thm1= getTheam();
    document.getElementById("theamChange").removeAttribute("class");
    document.getElementById("theamChange").setAttribute("class",thm1);
})

function setTheam(theam){
    localStorage.setItem("theam",theam);
}

function getTheam(){
    let theam=localStorage.getItem("theam");
    return theam ? theam:"Light";
}

