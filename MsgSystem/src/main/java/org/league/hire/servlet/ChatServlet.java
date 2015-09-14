package org.league.hire.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.league.hire.beans.ChatMessage;
import org.league.hire.beans.ChatUser;
import org.league.hire.helper.ChatHelper;
import org.league.hire.config.ChatConfig;

/**
 * ChatServlet Class that work as the gateway for all possible interaction with the chat backend
 * It contain 5 main functions:
 *  1.Login (and load initial contact list)
 *  2.Logout
 *  3.recieve message
 *  4.request messages
 *  5.refresh contact list.
 */
public class ChatServlet extends HttpServlet {
    private static String LOGIN="1";
    private static String RECIVE_MESSAGE="2";
    private static String REQUEST_MESSAGE="3";
    private static String REFRESH_CONTACT_LIST="4";
    private static String LOGOFF="5";
    private static String STEP="step";
    private static String USERNAME="username";
    private static String PASSWORD="password";
    private static String ERROR="ERROR";
    private static String USER="USER";
    private static String USER_ID="userId";
    private static String FROM="from_user";
    private static String FROM_NAME="from_user_name";
    private static String TO="to_user";
    private static String MESSAGE="message";
    private static String MESSAGE_COUNT="message_count";
    private static String CONTACT_COUNT="contact_count";
    private static String CONTACT_LIST="contacts";
    private static ChatHelper chatHelper=ChatHelper.getChatHelper();
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String stepId=(String)request.getParameter(STEP);
        if(stepId==null || stepId.equals("")){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try{
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Error!</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1><font color=red>Access Denied</font></h1>");
                out.println("</body>");
                out.println("</html>");
            }finally{
                out.close();
            }
        }else if(stepId.equals(LOGIN)){
            //TODO: Replace this code by authentication logic...
            //will do some dummy authentications...
            String username=request.getParameter(USERNAME);
            String password=request.getParameter(PASSWORD);
            if(username==null || password==null){
                request.setAttribute(ERROR, "True");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }else{
                ChatUser loginUser=chatHelper.authenticateUser(username, password);
                if(loginUser==null){
                    //System.out.println("Login failed!");
                    request.setAttribute(ERROR, "True");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }else{
                    chatHelper.loginUser(loginUser.getId());
                    request.getSession().setAttribute(USER, loginUser);
                    request.getSession().setAttribute(CONTACT_LIST, chatHelper.getContactList(loginUser.getId()));
                    request.getRequestDispatcher("/chatboard.jsp").forward(request, response);
                }
            }
        }else if(stepId.equals(RECIVE_MESSAGE)){
            //will retrieve message attributes...
            String from=request.getParameter(FROM);
            String from_name=request.getParameter(FROM_NAME);
            String to=request.getParameter(TO);
            String message=request.getParameter(MESSAGE);
            //System.out.println("from_user="+from+" from_user_name="+from_name+" to_user="+to+" message=["+message+"]");
            ChatMessage chatMessage=new ChatMessage();
            chatMessage.setFrom_user(Integer.parseInt(from));
            chatMessage.setTo_user(Integer.parseInt(to));
            chatMessage.setFrom_user_name(from_name);
            chatMessage.setMessage(message);
            Calendar cal=Calendar.getInstance();
            chatMessage.setMessageTime(cal.get(Calendar.HOUR)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND));
            chatMessage.setMessageType(ChatMessage.NEW_MESSAGE_TYPE);
            chatHelper.loginUser(chatMessage.getFrom_user());
            chatHelper.recievedMessage(chatMessage);
        }else if(stepId.equals(REQUEST_MESSAGE)){
            String userId=request.getParameter(USER_ID);
            //System.out.println("Request messages for the user="+userId);
            chatHelper.loginUser(Integer.parseInt(userId));
            ChatMessage[] messages=chatHelper.getUserMessages(Integer.parseInt(userId), ChatConfig.RECIEVE_MAX_MESSAGE_PER_CALL);
            if(messages!=null && messages.length>0){
                response.setContentType("text/html;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                response.setHeader(MESSAGE_COUNT, ""+messages.length);
                PrintWriter out = response.getWriter();
                try{
                    //System.out.println("Number of existing messages="+messages.length);
                    if(ChatConfig.responseType==ChatConfig.XML){
                        out.println("<chat_messages>");
                        for(int i=0;i<messages.length;i++){
                            String messageAsXML=messages[i].toXML();
                            //System.out.println(messageAsXML);
                            out.println(messageAsXML);
                        }
                        out.println("</chat_messages>");
                    }else{
                        for(int i=0;i<messages.length;i++){
                            String messageAsString=messages[i].toMultipleValues();
                            //System.out.println(messageAsString);
                            out.print(messageAsString);
                        }
                    }
                }finally{
                    out.flush();
                    out.close();
                }
            }else{
               //do nothing;
                return;
            }
        }else if(stepId.equals(LOGOFF)){
            String userId=request.getParameter(USER_ID);
            //System.out.println("Logoff user="+userId);
            chatHelper.logOffUser(Integer.parseInt(userId));
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }else if(stepId.equals(REFRESH_CONTACT_LIST)){
            String statusList="";
            ChatUser[] contactList=(ChatUser[])request.getSession().getAttribute(CONTACT_LIST);
            if(contactList!=null && contactList.length>0){
                for(int i=0;i<contactList.length;i++){
                    int status=chatHelper.getUserStatus(contactList[i].getId());
                    contactList[i].setStatus(status);
                    statusList+=contactList[i].getId()+"ø"+contactList[i].getName()+"ø"+contactList[i].getType()+"ø"+status+"ø";
                }
                //store latest contact list status
                request.getSession().setAttribute(CONTACT_LIST, contactList);
                //send statuls list output
                response.setContentType("text/html;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                response.setHeader(CONTACT_COUNT, ""+contactList.length);
                try{
                    //System.out.println("will send back the following status list="+statusList);
                    out.print(statusList);
                }finally{
                    out.flush();
                    out.close();
                }
            }
        }
        
    }
        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "ChatServlet Servlet the handle all interaction with chat backend.";
    }// </editor-fold>

}
