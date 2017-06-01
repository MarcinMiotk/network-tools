package network.tools.bestaddress;

import java.net.InetAddress;

class CucumberAddress {

    private String address;


    public String getAddress() {
        return address;
    }

    public InetAddress getInetAddress() {
        return new InetAddressTransformer().transform(address);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return address.toString();
    }
}
