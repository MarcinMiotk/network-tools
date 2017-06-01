package network.tools.bestaddress;

import java.net.InetAddress;
import java.util.Iterator;

public class BestAddress {

    public static AddressFinder build(Iterator<InetAddress> available) {
        return new AddressFinder(available);
    }
}
