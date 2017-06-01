package network.tools.bestaddress;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class AvailableAddressesProvider {

    public Iterator<InetAddress> get() {
        try {
            List<InetAddress> result = new ArrayList<>();
            Enumeration<NetworkInterface> interfaces =  NetworkInterface.getNetworkInterfaces();
            while(interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while(addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    if(address instanceof Inet4Address) {   // TODO: what about IP6 ?
                        result.add(address);
                    }
                }
            }
            return result.iterator();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
