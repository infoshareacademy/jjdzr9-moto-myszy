package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.dto.Wallet;

public interface BrokerCreator {
    Broker createBroker();
    Broker createBrokerWithWallet(Wallet wallet);
}
