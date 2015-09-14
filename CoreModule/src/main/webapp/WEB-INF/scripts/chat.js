var sendXmlHttp = null ;
var getXmlHttp = null ;
var contactXmlHttp = null ;
function sendMessage(from_user,name,to_user,message){
    createSendXmlHttpRequest();
    sendXmlHttp.open("POST",'/WebChatApp/ChatServlet?step=2&from_user='+from_user+'&from_user_name='+name+'&to_user='+to_user+'&message='+message,true);
    sendXmlHttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
    sendXmlHttp.send("<?XML version=\"1.0\" encoding=\"UTF-8\"?>");
}
function getMessages(user_id){
    createGetXmlHttpRequest();
    getXmlHttp.onreadystatechange = handleRequestMessages;
    getXmlHttp.open("POST",'/WebChatApp/ChatServlet?step=3&userId='+user_id,true);
    getXmlHttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
    getXmlHttp.send("<?XML version=\"1.0\" encoding=\"UTF-8\"?>");
}
function refreshContactList(){
    createContactXmlHttpRequest();
    contactXmlHttp.onreadystatechange = handleRequestContacts;
    contactXmlHttp.open("POST",'/WebChatApp/ChatServlet?step=4',true);
    contactXmlHttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
    contactXmlHttp.send("<?XML version=\"1.0\" encoding=\"UTF-8\"?>");
}
function createSendXmlHttpRequest(){
    if(window.ActiveXObject){
        sendXmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if(window.XMLHttpRequest){
        sendXmlHttp = new XMLHttpRequest();
    }
}
function createGetXmlHttpRequest(){
    if(window.ActiveXObject){
        getXmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if(window.XMLHttpRequest){
        getXmlHttp = new XMLHttpRequest();
    }
}
function createContactXmlHttpRequest(){
    if(window.ActiveXObject){
        contactXmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if(window.XMLHttpRequest){
        contactXmlHttp = new XMLHttpRequest();
    }
}

function handleRequestMessages(){
    if(getXmlHttp.readyState == 4){
        no_of_messages=getXmlHttp.getResponseHeader("message_count");
        xmlvalue=getXmlHttp.responseText;
        if(xmlvalue == null){
            xmlvalue = getXmlHttp.responseXML;
        }
        if(no_of_messages>0 && xmlvalue!=null && xmlvalue!=''){
            tokens=xmlvalue.split('ø');
            var d = new Date();
            var time =d.getHours()+':'+d.getMinutes()+':'+d.getSeconds();
            for(i=0;i<no_of_messages;i++){
                n=i*4;
                var from_user = tokens[n];
                var from_user_name = tokens[n+1];
                var messageText = tokens[n+2];
                var type = tokens[n+3];
                //will use client time instead
                receive_message(from_user,from_user_name,messageText,type,time);
            }
            if(mute==0) PlaySound("sound1");
        }
    }
}
function handleRequestContacts(){
    if(contactXmlHttp.readyState == 4){
        no_of_contacts=contactXmlHttp.getResponseHeader("contact_count");
        xmlvalue=contactXmlHttp.responseText;
        if(xmlvalue == null){
            xmlvalue = contactXmlHttp.responseXML;
        }
        if(no_of_contacts>0 && xmlvalue!=null && xmlvalue!=''){
            tokens=xmlvalue.split('ø');
            for(i=0;i<no_of_contacts;i++){
                n=i*4;
                var user_no = tokens[n];
                var user_name = tokens[n+1];
                var type = tokens[n+2];
                var newStatus = tokens[n+3];
                updateUserStatus(user_no,user_name,type,newStatus);
            }
        }
    }
}