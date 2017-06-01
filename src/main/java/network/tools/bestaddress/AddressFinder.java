package network.tools.bestaddress;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class AddressFinder {

    private final List<InetAddress> available = new ArrayList<>();
    private List<String> regExpressions = new ArrayList<>();

    protected AddressFinder(Iterator<InetAddress> iterator) {
        iterator.forEachRemaining((a) -> {
            available.add(a);
        });
    }

    public AddressFinder filter(List<String> regExpressions) {
        this.regExpressions = regExpressions;
        return this;
    }

    public Optional<InetAddress> findBestAddress() {
        Optional<String> bestRegExpression = findBestRegularExpression();
        if(bestRegExpression.isPresent()) {
            InetAddress bestAddress = available.stream().filter(candidate -> candidate.getHostAddress().matches(bestRegExpression.get())).findFirst().get();
            return Optional.ofNullable(bestAddress);
        } else {
            return available.stream().findFirst();
        }
    }

    private Optional<String> findBestRegularExpression() {
        return regExpressions.stream().filter(candidateExpression -> {
            for(InetAddress candidateAvailable : available) {
                if(candidateAvailable.getHostAddress().matches(candidateExpression)) {
                    return true;
                }
            }
            return false;
        }).findFirst();
    }
}
