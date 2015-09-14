package org.league.hire.config;

public class ChatConfig {
    //5 minutes to expire user session
    public static long expireLoginAfter=1000*60*5;
    //user can't open more than 5 chat windows
    public static int maxChatWindows=5;
    //the time between each 2 refresh calls to get the messages
    public static int refreshRate=15000;
    //the frequency to get the user contact list statuses.
    //the actual time = refreshRate*refreshRateForContacts
    public static int refreshRateForContacts=4;
    //support user can't open more than 15 chat windows
    public static int maxChatWindowsForSupport=15;
    //use string with symbol separator instead of xml
    public static int responseType=1;
    //response types
    public static int XML=0;
    public static int TEXT=1;
    //max retrun chat messages per single Ajax call
    public static int RECIEVE_MAX_MESSAGE_PER_CALL=10;
}

