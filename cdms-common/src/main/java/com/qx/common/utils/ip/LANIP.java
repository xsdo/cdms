package com.qx.common.utils.ip;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.*;


/**
 * 提取局域网IP
 *
 */
public class LANIP extends Thread {
    public static List<String> getIPs()
    {
        List<String> list = new ArrayList<String>();
        boolean flag = false;
        int count=0;
        Runtime r = Runtime.getRuntime();
        Process p;
        try {
            p = r.exec("arp -a");
            BufferedReader br = new BufferedReader(new InputStreamReader(p
                    .getInputStream()));
            String inline;
            while ((inline = br.readLine()) != null) {
                /*if(inline.indexOf("接口") > -1){
                    flag = !flag;
                    if(!flag){
                        //碰到下一个"接口"退出循环
                        break;
                    }
                }
                if(flag){
                    count++;
                    if(count > 2){
                        //有效IP
                        String[] str=inline.split(" {4}");
                        list.add(str[0]);
                    }
                }*/
                if (inline.contains("192.168.1.")) {
                    String[] str=inline.split(" {4}");
                    list.add(str[0]);
                }
            }
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    public static Map<String,String> getHostnames(List<String> ips){

        Map<String,String> map = new HashMap<String,String>();
        System.out.println("正在提取hostname...");
        for(String ip : ips){
            String command = "ping -a " + ip;
            Runtime r = Runtime.getRuntime();
            Process p;
            try {
                p = r.exec(command);
                BufferedReader br = new BufferedReader(new InputStreamReader(p
                        .getInputStream()));
                String inline;
                while ((inline = br.readLine()) != null) {
                    if(inline.indexOf("[") > -1){
                        int start = inline.indexOf("Ping ");
                        int end = inline.indexOf("[");
                        String hostname = inline.substring(start+"Ping ".length(),end-1);
                        System.out.println(hostname);
                        map.put(ip,hostname);
                    }
                }
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("提取结束！");
        return map;
    }
    // 正确的IP拿法，即优先拿site-local地址
    @SuppressWarnings("rawtypes")
    public static String getLocalHostLANAddress() throws UnknownHostException {
        try {
            InetAddress candidateAddress = null;
            // 遍历所有的网络接口
            for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements();) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                // 在所有的接口下再遍历IP
                for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements();) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
                        if (inetAddr.isSiteLocalAddress()) {
                            // 如果是site-local地址，就是它了
                            return inetAddr.getHostAddress();
                        } else if (candidateAddress == null) {
                            // site-local类型的地址未被发现，先记录候选地址
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress.getHostAddress();
            }
            // 如果没有发现 non-loopback地址.只能用最次选的方案
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            if (jdkSuppliedAddress == null) {
                throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
            }

            return jdkSuppliedAddress.getHostAddress();
        } catch (Exception e) {
            UnknownHostException unknownHostException = new UnknownHostException(
                    "Failed to determine LAN address: " + e);
            unknownHostException.initCause(e);
            throw unknownHostException;
        }
    }

}




