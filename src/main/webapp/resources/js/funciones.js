

function seleccionarFila() {
    
    btnRemove.enable();
}
function deseleccionarFila() {
    
    btnRemove.disable();
}

function clearCheckboxes(widgetVar)
 {
            widgetVar.toggleCheckAll();
            
            if(widgetVar.getSelectedRowsCount() > 0)
            {
                widgetVar.toggleCheckAll();
            }
 }
 
 function hideBtn(widgetVar1,widgetVar2){
     
    widgetVar1.hide();
    widgetVar2.hide();
     
     
 }
        
        
        

