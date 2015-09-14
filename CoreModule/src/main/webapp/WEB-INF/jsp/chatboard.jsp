

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="osa.ora.config.ChatConfig;" %>
<%@page import="osa.ora.beans.*;" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<% ChatUser user=(ChatUser)session.getAttribute("USER");
   if(user==null){
       request.setAttribute("ERROR_SESS", "True");
       request.getRequestDispatcher("/index.jsp").forward(request, response);
   }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chat Board - Welcome <%= user.getName()%></title>
        <!link rel="stylesheet" href="/WebChatApp/css/chat.css" type="text/css">
        <script charset="UTF-8" language="JavaScript" src="/scripts/chat.js"></script>
        <script charset="UTF-8">
            maxChatWindows=<%= ChatConfig.maxChatWindows %>;
            refreshRate=<%= ChatConfig.refreshRate %>;
            refreshRateForContacts=<%= ChatConfig.refreshRateForContacts %>;
            <%if(user.getType()==ChatUser.SUPPORT) {%>
                maxChatWindows=<%= ChatConfig.maxChatWindowsForSupport %>;
            <%} %>
            currentChatWindows=0;
            refresh=0;
            mute=0;
            var nsstyle='display:""'
            if (document.layers)
                var scrolldoc=document.scroll1.document.scroll2
            function up(){
                if (!document.layers) return
                if (scrolldoc.top<0)
                    scrolldoc.top+=10
                temp2=setTimeout("up()",50)
            }
            function down(){
                if (!document.layers) return
                if (scrolldoc.top-150>=scrolldoc.document.height*-1)
                    scrolldoc.top-=10
                temp=setTimeout("down()",50)
            }

            function clearup(){
                if (window.temp2)
                    clearInterval(temp2)
            }

            function cleardown(){
                if (window.temp)
                    clearInterval(temp)
            }

            function scroll_down(user_no){
                var objDiv = document.getElementById('scroll3_'+user_no);
                objDiv.scrollTop = objDiv.scrollHeight;
            }
            function clear_text(user_no){
                var objDiv = document.getElementById('scroll3_'+user_no);
                objDiv.innerHTML='';
            }
            function close_chat(user_no){
                var objDiv = document.getElementById('global_div');
                var oldDiv = document.getElementById(user_no);
                objDiv.removeChild(oldDiv);
                currentChatWindows--;
            }

            function add_message(user_no){
                var textValue=document.getElementById('input_'+user_no).value;
                if(textValue=='') return;
                //call ajax to send the (nameValue+' says : '+textValue);
                sendMessage(<%= user.getId()%>,'<%= user.getName()%>',user_no,textValue);
                //render emoticons
                textValue=replaceEmoticons(textValue);
                //post the message
                var objDiv = document.getElementById('scroll3_'+user_no);
                var nameValue='<%= user.getName()%>';
                objDiv.innerHTML=objDiv.innerHTML+'<br><font color=black>'+nameValue+' says : '+textValue+'</font>';
                document.getElementById('input_'+user_no).value='';
                scroll_down(user_no);
                document.getElementById('state_'+user_no).src='/CoreModule/images/empty.png';
                document.getElementById('state_'+user_no).width=1;
                document.getElementById('state_'+user_no).hight=1;
            }
            //this function is called by Ajax to post user message
            function receive_message(user_no,name,message,type,time){
                var objDiv = document.getElementById('scroll3_'+user_no);
                if(!document.getElementById('scroll3_'+user_no)) {
                    openNewChat(user_no,name);
                    objDiv = document.getElementById('scroll3_'+user_no);
                }
                //render emoticons
                message=replaceEmoticons(message);
                if(type==0){
                    objDiv.innerHTML=objDiv.innerHTML+'<br><font color=blue>'+name+' says : '+message+'</font>';
                }else{
                    objDiv.innerHTML=objDiv.innerHTML+'<br><font color=red> Failed to deliver : '+message+'</font>';
                }
                scroll_down(user_no);
                document.getElementById('state_'+user_no).src='/CoreModule/images/report.gif';
                document.getElementById('state_'+user_no).width=15;
                document.getElementById('state_'+user_no).hight=15;
                document.getElementById('display_'+user_no).value=' Last message recieved at '+time;
            }
            function replaceEmoticons(message){
                //skip html tags
                message=message.replace(/</gi, "&lt;");
                //render the emoticons
                message=message.replace(/:\)/gi, "<img src=/CoreModule/images/smile.png>");
                message=message.replace(/:\(/gi, "<img src=/CoreModule/images/sad.png>");
                message=message.replace(/:\|/gi, "<img src=/CoreModule/images/shocked.png>");
                message=message.replace(/:s/gi, "<img src=/CoreModule/images/confused.png>");
                message=message.replace(new RegExp( ":D", "gi" ), "<img src=/CoreModule/images/bigSmile.png>");
                message=message.replace(/;\)/gi, "<img src=/CoreModule/images/wink.png>");                
                return message;

            }
            function enableEnterKey(e,user_no){
                var key;
                if(window.event)
                    key = window.event.keyCode;     //IE
                else
                    key = e.which;     //firefox
                if(key == 13){
                    return add_message(user_no);
                } else{
                    return true;
                }
            }
            function openNewChat(user_no,name){
                var oldDiv = document.getElementById(user_no);
                //check if it is already exist
                if(oldDiv!=null) return;
                if(currentChatWindows>=maxChatWindows){
                    alert('Sorry,You have exceed the max number of open chat dialogs per user');
                    return;
                }
                currentChatWindows++;
                //get the global div
                var objDiv = document.getElementById('global_div');
                //create a new div for this user
                var mytext='<div id='+user_no+'><div id="chat_'+user_no+'" class="chatwindow" width=400><div class="titlebar" align=left><table width=400><tr><td width=360><font color=white><b> Chat to '+name+'</b></font></td><td align=right width=20><img id=state_'+user_no+' src=/WebChatApp/images/empty.png border=0 width=1 hight=1></td><td align=right width=20><img src=/WebChatApp/images/close.jpg border=0 title="Close Chat" onclick="close_chat('+user_no+');" style="cursor:pointer"></a></td></tr></table></div><div><br><ilayer name="scroll1" width=400 height=180 clip="0,0,170,150"><layer name="scroll2" width=400 height=180><div id=scroll3_'+user_no+' style="width:400px;height:180px;background-color:white;overflow:scroll;" align=left></div></layer></ilayer><input id=display_'+user_no+' name=display_'+user_no+' disabled style="width:400px;height:15px;border:0;background-color:#eeefff;"><input id=input_'+user_no+' name=input_'+user_no+' style="width:310px;height:30px;background-color:white;" onkeydown="return enableEnterKey(event,'+user_no+')"/>&nbsp;<image src=/WebChatApp/images/submit.png width=30 hight=30 border=1 title="Send" onclick="add_message('+user_no+')" style="vertical-align: middle;cursor:pointer">&nbsp;<image src=/WebChatApp/images/trash.png width=30 hight=30 border=1 title="Clear Chat" onclick="clear_text('+user_no+');" style="vertical-align: middle;cursor:pointer"></div></div></div></div>';
                objDiv.innerHTML=objDiv.innerHTML+mytext;
            }
            function update(){
                //call ajax to get messages from user queue
                getMessages(<%= user.getId()%>);
                refresh++;
                //each 4 = 1 minute (15 seconds * 4 =60)
                if(refresh>=refreshRateForContacts) {
                    refresh=0;
                    refreshContactList();
                }
                //re-set timeout
                setTimeout("update()",refreshRate);
            }
            function updateUserStatus(user_no,user_name,type,newStatus){
                //alert('No='+user_no+' Name='+user_name+' Type='+type+' Status='+newStatus);
                document.getElementById('user_'+user_no).src='/CoreModule/images/'+type+newStatus+'.png';
                if(newStatus==<%=ChatUser.ONLINE%>){
                    //document.getElementById('user_'+user_no).style="vertical-align: middle;cursor:pointer";
                    document.getElementById('user_'+user_no).onclick=function(){ openNewChat(user_no,user_name)}
                }else{
                    //document.getElementById('user_'+user_no).style="vertical-align: middle;";
                    document.getElementById('user_'+user_no).onclick="";
                }
            }
            function PlaySound(soundObj) {
              var sound = document.getElementById(soundObj);
              sound.Play();
            }
            function muteSound(){
                if(mute==0) {
                    mute=1;
                    document.getElementById('sound_icon').src='/CoreModule/images/mute_sound.gif';
                } else{
                    mute=0;
                    document.getElementById('sound_icon').src='/CoreModule/images/sound.gif';
                }
            }
            </script>
    </head>
    <body onload='setTimeout("update()",refreshRate);'>
        <center>
             <img src="/CoreModule/images/company.png"/>
            <table>
                <tr valign="top" align="left">
                    <td width="160pt">
                        <table width="160pt" border="1" cellpadding="4" cellspacing="0">
                            <tr width="160pt"><td bgcolor="#ffffff" width="160pt"><font color="red"><center><b>Contact List</b></center></font></td>
                            </tr>
                        </table>
                        <% ChatUser[] contactList=(ChatUser[])session.getAttribute("contacts");
                           if(contactList==null || contactList.length==0){ %>
                           No User(s)
                        <% }else{ %>                           
                            <% for(int i=0;i<contactList.length;i++){%>
                                <% if(contactList[i].getStatus()==ChatUser.OFFLINE) {%>
                                        <img id=user_<%= contactList[i].getId()%> name=user_<%= contactList[i].getId()%> src="/WebChatApp/images/<%= contactList[i].getType()%><%= contactList[i].getStatus()%>.png" style="vertical-align: middle;cursor:pointer"/> <%= contactList[i].getName()%>
                                <%} else { %>
                                        <img id=user_<%= contactList[i].getId()%> name=user_<%= contactList[i].getId()%> src="/WebChatApp/images/<%= contactList[i].getType()%><%= contactList[i].getStatus()%>.png" onclick="openNewChat(<%= contactList[i].getId()%>,'<%= contactList[i].getName()%>');" style="vertical-align: middle;cursor:pointer"/> <%= contactList[i].getName()%>
                                <% } %>
                                <% if(contactList[i].getEmail()!=null) {%>
                                    <a href="mailto:<%= contactList[i].getEmail() %>"><img src="/CoreModule/images/email.jpg" width="12" height="9" style="border: 0;vertical-align: middle;cursor:pointer" title="<%= contactList[i].getEmail() %>"/></a>
                                <% } %>
                                    <br>
                            <% } %>
                        <% } %>
                    </td>
                    <td>
                        <div id=global_div>
                            <table>
                                <tr>
                                    <td width="350pt" align="left">
                                        <font color="red"><b>Chat Dialogs</b></font>
                                    </td>
                                    <td align="right">
                                        <img id="sound_icon" name="sound_icon" width="32" height="32" src="/CoreModule/images/sound.gif" onclick="muteSound();" style="vertical-align: middle;cursor:pointer;" title="Control Sound"/>&nbsp;
                                        <a href="/CoreModule/ChatServlet?step=5&userId=<%= user.getId()%>">
                                            <img src="/CoreModule/images/logoff.gif" style="vertical-align: middle;cursor:pointer;border:0;" title="Logoff"/>
                                        </a>
                                    </td>
                                </tr>
                            </table>
                            <hr width="450pt">
                        </div>
                    </td>
                </tr>
            </table>
            <span style="display:none" style=&{nsstyle};><a href="#" onMousedown="up()"
                                                            onMouseup="clearup()" onClick="return false" onMouseout="clearup()">Up</a> | <a href="#"
                                                            onMousedown="down()" onMouseup="cleardown()" onClick="return false"
                                                            onMouseout="cleardown()">Down</a> | <a href="#" onClick="if (document.layers) scrolldoc.top=0;return false">Top</a> | <a href="#" onClick="if (document.layers) scrolldoc.top=scrolldoc.document.height*(-1)+150;return false">Bottom</a></span>
        </center>
        <embed src="/CoreModule/sound/new_message.wav" autostart="false" width="0" height="0" id="sound1" name="sound1" enablejavascript="true"/>
    </body>
</html>
