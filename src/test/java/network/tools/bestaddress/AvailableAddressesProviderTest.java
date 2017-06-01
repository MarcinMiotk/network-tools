package network.tools.bestaddress;

import org.junit.Test;

import java.net.InetAddress;
import java.util.Iterator;

import static org.junit.Assert.*;

public class AvailableAddressesProviderTest {

    AvailableAddressesProvider provider = new AvailableAddressesProvider();

    @Test
    public void computerHasAtLeastOneAvailableAddressIP4() {
        Iterator<InetAddress> available = provider.get();
        assertTrue(available.hasNext());
    }

    @Test
    public void printComputerAddresses() {
        Iterator<InetAddress> available = provider.get();
        while(available.hasNext()) {
            System.out.println("Address of this computer: "+available.next());
        }
    }
}