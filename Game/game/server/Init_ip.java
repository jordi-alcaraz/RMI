package game.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;

public class Init_ip {
	private HashMap<String,String> ip_map = new HashMap<String,String>();
	public Init_ip() {}
	
    private void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        System.out.printf("Interface: %s  ", netint.getName());
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
        	if(Inet4Address.class == inetAddress.getClass() )
        	{
            	System.out.printf("IP: %s\n\n", inetAddress.getHostAddress());
            	ip_map.put(netint.getName(), inetAddress.getHostAddress());
        	}
        }
     }
	
	
	public String getIP(){
		try {
			Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
	        for (NetworkInterface netint : Collections.list(nets))
	            displayInterfaceInformation(netint);	
		}catch (Exception ex) {
	        System.err.println("Server exception: " + ex.toString());
	        ex.printStackTrace();
	    }
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inter_name = "";
		do{
	        System.out.print("Enter interface name");
			try {
				inter_name = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(ip_map.containsKey(inter_name) );
		}while(!ip_map.containsKey(inter_name));
		String ipAddress = ip_map.get(inter_name);
		System.out.println(ipAddress+" "+ ip_map.containsKey(inter_name) );
		return ipAddress;
	}
	

}
