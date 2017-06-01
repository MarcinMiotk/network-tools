Feature: Finding best address

  Scenario: Best address is first from available addresses.
    Given Available addresses are
      | address       |
      | 10.99.99.109  |
      | 10.99.99.101  |
    Then Best address is "10.99.99.109"

  Scenario: Best address is chosen regarding the given regex expressions and its sequence.
    Given Available addresses are
      | address       |
      | 10.99.99.109  |
      | 10.99.99.101  |
    When Filter available addresses with expressions
      | 10\.99\.99\.101  |
    Then Best address is "10.99.99.101"

  Scenario: I can use complex regular expression
    Given Available addresses are
      | address       |
      | 10.20.5.4     |
      | 10.99.99.106  |
      | 10.99.99.102  |
    When Filter available addresses with expressions
      | 10\.20.*      |
    Then Best address is "10.20.5.4"


  Scenario: Choose first address when no expression matches to available addresses
    Given Available addresses are
      | address       |
      | 20.20.5.1     |
      | 10.99.99.106  |
      | 10.99.99.102  |
    When Filter available addresses with expressions
      | 10\.20.*      |
    Then Best address is "20.20.5.1"

  Scenario: Best address is first from available addresses.
    Given Available addresses are
      | address       |
    Then There is no best address.