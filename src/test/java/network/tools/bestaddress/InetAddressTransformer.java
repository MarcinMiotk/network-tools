package network.tools.bestaddress;

import cucumber.api.Transformer;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTransformer extends Transformer<InetAddress> {

    @Override
    public InetAddress transform(String s) {
        try {
            return InetAddress.getByName(s);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
