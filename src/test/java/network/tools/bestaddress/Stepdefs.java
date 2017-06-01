package network.tools.bestaddress;

import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.net.InetAddress;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class Stepdefs {


    AddressFinder addressFinder;

    InetAddress convertAddress(CucumberAddress from) {
        return from.getInetAddress();
    }

    @Given("^Available addresses are$")
    public void available_addresses_are(List<CucumberAddress> available) throws Throwable {
        Iterator<InetAddress> iterator = available.stream().map(this::convertAddress).collect(Collectors.toList()).iterator();

        BestAddress address = new BestAddress();
        addressFinder = address.build(iterator);
    }


    @When("^Filter available addresses with expressions$")
    public void filter_available_addresses_with_expressions(List<String> regExpressions) throws Throwable {
        addressFinder.filter(regExpressions);
    }

    @Then("^Best address is \"(.*?)\"$")
    public void best_address_is(@Transform(InetAddressTransformer.class) InetAddress expectedAddress) throws Throwable {
        assertEquals(expectedAddress, addressFinder.findBestAddress().get());
    }

    @Then("^There is no best address\\.$")
    public void there_is_no_best_address() throws Throwable {
        assertFalse("There should be no address", addressFinder.findBestAddress().isPresent());
    }

}
