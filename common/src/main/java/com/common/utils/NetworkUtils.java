package com.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * author miekoz on 2016/3/16.
 * email  meikoz@126.com
 */
public class NetworkUtils {
    public static final int NETWORK_TYPE_NO_CONNECTION = -1231545315;

    public NetworkUtils() {
    }

    public static NetworkInfo.State getCurrentNetworkState(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
        return networkInfo != null?networkInfo.getState():null;
    }

    public static boolean isConnectedByState(Context context) {
        return getCurrentNetworkState(context) == NetworkInfo.State.CONNECTED;
    }

    public static String getIpAddress() {
        try {
            Enumeration en = NetworkInterface.getNetworkInterfaces();

            while(en.hasMoreElements()) {
                NetworkInterface ex = (NetworkInterface)en.nextElement();
                Enumeration enumIpAddr = ex.getInetAddresses();

                while(enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress)enumIpAddr.nextElement();
                    if(!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }

            return null;
        } catch (SocketException var4) {
            var4.printStackTrace();
            return null;
        }
    }
}
